package ua.training.cashregister.entity;


import java.util.Objects;

public class ReceiptEntry{
    private Goods goods;
    private Receipt receipt;
    private int amount;
    private int price;

    public ReceiptEntry(Goods goods, Receipt receipt, int amount, int price) {
        this.goods = goods;
        this.receipt = receipt;
        this.amount = amount;
        this.price = price;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReceiptEntry)) return false;
        ReceiptEntry that = (ReceiptEntry) o;
        return amount == that.amount &&
                price == that.price &&
                Objects.equals(goods, that.goods) &&
                Objects.equals(receipt, that.receipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goods, receipt, amount, price);
    }
}
