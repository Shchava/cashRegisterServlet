package ua.training.cashregister.entity;

import java.util.Objects;

public class GoodsByWeight extends Goods {
    private int weight_price;

    private int weight;

    public GoodsByWeight(String name, int weight_price, int weight) {
        super(name);
        this.weight_price = weight_price;
        this.weight = weight;
    }

    @Override
    public int getAmount() {
        return weight;
    }

    @Override
    public int getPrice() {
        return weight_price;
    }

    @Override
    public int getSum() {
        return weight*weight_price;
    }

    public int getWeight_price() {
        return weight_price;
    }

    public void setWeight_price(int weight_price) {
        this.weight_price = weight_price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoodsByWeight)) return false;
        if (!super.equals(o)) return false;
        GoodsByWeight that = (GoodsByWeight) o;
        return weight_price == that.weight_price &&
                weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weight_price, weight);
    }
}
