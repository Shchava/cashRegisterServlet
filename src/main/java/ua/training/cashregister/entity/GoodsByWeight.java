package ua.training.cashregister.entity;

public class GoodsByWeight extends Goods {
    private int weight_price;

    private int weight;

    public GoodsByWeight(String name, int weight_price, int weight) {
        super(name);
        this.weight_price = weight_price;
        this.weight = weight;
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
}
