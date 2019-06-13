package ua.training.cashregister.service.receipt;

import ua.training.cashregister.dao.DaoFactory;
import ua.training.cashregister.dao.ReceiptDao;
import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.entity.ReceiptEntry;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.goods.GoodsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReceiptService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void closeLastReceipt(long cashierId){
        Optional<Receipt> last = findOpenReceipt(cashierId);
        last.ifPresent(this::closeReceipt);
    };

    public void closeReceipt(Receipt receipt){
        if(receipt.getEntries().isEmpty()){
            deleteReceipt(receipt);
        }else{
            receipt.setCreated(LocalDateTime.now());
            updateReceipt(receipt);
        }
    }

    public void updateReceipt(Receipt receipt){
        try(ReceiptDao dao = daoFactory.createReceiptDao()){
            dao.update(receipt);
        }
    }

    public void deleteReceipt(Receipt receipt){
        try(ReceiptDao dao = daoFactory.createReceiptDao()){
            dao.delete(receipt.getId());
        }
    }

    public void saveReceiptEntry(ReceiptEntry entry){
        GoodsService goodsService = new GoodsService();
        try(ReceiptDao dao = daoFactory.createReceiptDao()) {
            dao.createReceiptEntry(entry);
        }
    }

    public void updateReceiptEntry(ReceiptEntry entry){
        GoodsService goodsService = new GoodsService();
        try(ReceiptDao dao = daoFactory.createReceiptDao()) {
            dao.updateReceiptEntry(entry);
        }
    }

    public Receipt getNotClosedOrOpenNew(User cashier){
        Optional<Receipt> existing = findOpenReceipt(cashier.getId());
        return existing.orElseGet(()->{
           try(ReceiptDao dao = daoFactory.createReceiptDao()){
               Receipt receipt = new Receipt();
               receipt.setCashier(cashier);
               receipt.setEntries(new ArrayList<>());
               dao.create(receipt);
               return receipt;
           }
        });
    }

    public Optional<Receipt> findOpenReceipt(long cashierId){
        try(ReceiptDao dao = daoFactory.createReceiptDao()) {
            return dao.findNotClosedByUserId(cashierId);
        }
    }

    public void addReceiptEntry(Receipt receipt,ReceiptEntry receiptEntry){
        GoodsService goodsService = new GoodsService();

        Optional<ReceiptEntry> existing =  receipt.getEntries()
                                                    .stream()
                                                    .filter (en->en.getGoods().getId()==receiptEntry.getGoods().getId())
                                                    .findFirst();

        existing.ifPresent(ex->{ goodsService.removeFromWarehouse(ex.getGoods(),receiptEntry.getAmount() - ex.getAmount());
                                    ex.setAmount(ex.getAmount() + receiptEntry.getAmount());});

        ReceiptEntry adding = existing.orElseGet(()->{receipt.getEntries().add(receiptEntry);
                                                        goodsService.removeFromWarehouse(receiptEntry.getGoods(),receiptEntry.getAmount());
                                                        return receiptEntry;});
        calculateAndSetPrice(adding);

        if(existing.isPresent()) {
            updateReceiptEntry(adding);
        }else{
            saveReceiptEntry(adding);
        }
    }

    public void calculateAndSetPrice(ReceiptEntry entry){
        entry.setPrice(entry.getAmount() * entry.getGoods().getPrice()); //TODO make calculation dependent on goods type
    }

    public List<Receipt> findReceiptsByCashierId(long id){
        try(ReceiptDao dao = daoFactory.createReceiptDao()) {
            return dao.findByUserId(id);
        }
    }

    public List<Receipt> findReceiptsByCashierId(long id, int offset, int recordsPerPage) {
        try(ReceiptDao dao = daoFactory.createReceiptDao()) {
            return dao.findByUserId(id,offset,recordsPerPage);
        }
    }

    public Optional<Receipt> findReceipt(long id) {
        try(ReceiptDao dao = daoFactory.createReceiptDao()) {
            return dao.findById(id);
        }
    }

    public int countReceiptsByCashierId(long id) {
        try(ReceiptDao dao = daoFactory.createReceiptDao()) {
            return dao.getNumberOfReceiptsByUserId(id);
        }
    }


}
