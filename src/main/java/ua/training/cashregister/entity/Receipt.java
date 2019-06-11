package ua.training.cashregister.entity;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Receipt {
    private long id_receipt;
    private LocalDateTime created;
    private User cashier;
    private User seniorCashier;
    private List<ReceiptEntry> entries;

    public Receipt(LocalDateTime created, User cashier, User seniorCashier) {
        this.created = created;
        this.cashier = cashier;
        this.seniorCashier = seniorCashier;
    }

    public List<ReceiptEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ReceiptEntry> entries) {
        this.entries = entries;
    }

    public long getId_receipt() {
        return id_receipt;
    }

    public void setId_receipt(long id_receipt) {
        this.id_receipt = id_receipt;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public User getCashier() {
        return cashier;
    }

    public void setCashier(User cashier) {
        this.cashier = cashier;
    }

    public User getSeniorCashier() {
        return seniorCashier;
    }

    public void setSeniorCashier(User seniorCashier) {
        this.seniorCashier = seniorCashier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receipt)) return false;
        Receipt receipt = (Receipt) o;
        return id_receipt == receipt.id_receipt &&
                Objects.equals(created, receipt.created) &&
                Objects.equals(cashier, receipt.cashier) &&
                Objects.equals(seniorCashier, receipt.seniorCashier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_receipt, created, cashier, seniorCashier);
    }
}
