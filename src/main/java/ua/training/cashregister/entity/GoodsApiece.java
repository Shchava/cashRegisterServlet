package ua.training.cashregister.entity;

public class GoodsApiece extends Goods {
    private int apiece_price;

    private int count;

    public GoodsApiece(String name, int apiece_price, int count) {
        super(name);
        this.apiece_price = apiece_price;
        this.count = count;
    }

    public int getApiece_price() {
        return apiece_price;
    }

    public void setApiece_price(int apiece_price) {
        this.apiece_price = apiece_price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
