package ua.training.cashregister.service.receipt;

import ua.training.cashregister.dao.DaoFactory;
import ua.training.cashregister.dao.ReceiptDao;
import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.entity.ReceiptEntry;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.goods.GoodsService;

import java.util.List;
import java.util.Optional;

public class ReceiptService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    public Receipt openReceipt(User opener){
        Receipt receipt = new Receipt();
        receipt.setCashier(opener);
        try(ReceiptDao dao = daoFactory.createReceiptDao()){
                dao.create(receipt);
        }
        return receipt;
    }

    public void addReceiptEntry(ReceiptEntry entry){
        GoodsService goodsService = new GoodsService();
        try(ReceiptDao dao = daoFactory.createReceiptDao()) {
            goodsService.removeFromWarehouse(entry.getGoods(),entry.getAmount());
            dao.createReceiptEntry(entry);
        }
    }

    public Receipt open(User cashier){
        Optional<Receipt> existing = findOpenReceipt(cashier.getId());
        return existing.orElseGet(()->{
           try(ReceiptDao dao = daoFactory.createReceiptDao()){
               Receipt receipt = new Receipt();
               receipt.setCashier(cashier);
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

    public List<Receipt> findReceiptByCashierId(long id){
        try(ReceiptDao dao = daoFactory.createReceiptDao()) {
            return dao.findByUserId(id);
        }
    }
}
