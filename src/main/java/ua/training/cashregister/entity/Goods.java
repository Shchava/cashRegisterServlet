package ua.training.cashregister.entity;

import ua.training.cashregister.entity.enums.GoodsTypes;

import java.util.Objects;

public abstract class Goods {
    private long id;
    private String name;

    public static Goods createGoods(String name, GoodsTypes type, int price, int amount){
        switch (type){
            case APIECE:{
                return new GoodsApiece(name,price,amount);
            }
            case BY_WEIGHT:{
                return new GoodsByWeight(name,price,amount);
            }
            default:{
                return null;
            }
        }
    }

    public abstract int getAmount();

    public abstract int getPrice();

    public abstract int getSum();

    public Goods(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Goods)) return false;
        Goods goods = (Goods) o;
        return id == goods.id &&
                name.equals(goods.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
