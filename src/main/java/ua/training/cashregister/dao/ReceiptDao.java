package ua.training.cashregister.dao;

import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.entity.ReceiptEntry;

import java.util.List;
import java.util.Optional;

public interface ReceiptDao extends GenericDao<Receipt>{
    List<Receipt> findByUserId(long userId);
    Optional<Receipt> findNotClosedByUserId(long userId);
    boolean createReceiptEntry(ReceiptEntry entry);
    boolean updateReceiptEntry(ReceiptEntry entry);
}
