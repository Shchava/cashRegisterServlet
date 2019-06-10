package ua.training.cashregister.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoodsApiece)) return false;
        if (!super.equals(o)) return false;
        GoodsApiece that = (GoodsApiece) o;
        return apiece_price == that.apiece_price &&
                count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), apiece_price, count);
    }
}
