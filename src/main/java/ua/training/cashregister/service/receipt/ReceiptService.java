package ua.training.cashregister.service.receipt;

import com.sun.istack.internal.NotNull;
import ua.training.cashregister.dao.DaoFactory;
import ua.training.cashregister.dao.ReceiptDao;
import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.entity.ReceiptEntry;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.goods.GoodsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReceiptService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void saveReceiptEntry(ReceiptEntry entry){
        GoodsService goodsService = new GoodsService();
        try(ReceiptDao dao = daoFactory.createReceiptDao()) {
            dao.createReceiptEntry(entry);
        }
    }

    public void updateReceiptEntry(ReceiptEntry entry){
        GoodsService goodsService = new GoodsService();
        try(ReceiptDao dao = daoFactory.createReceiptDao()) {
            goodsService.removeFromWarehouse(entry.getGoods(),entry.getAmount());
            dao.createReceiptEntry(entry);
        }
    }

    public Receipt getNotClosedOrOpen(User cashier){
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

    public List<Receipt> findReceiptByCashierId(long id){
        try(ReceiptDao dao = daoFactory.createReceiptDao()) {
            return dao.findByUserId(id);
        }
    }
}
