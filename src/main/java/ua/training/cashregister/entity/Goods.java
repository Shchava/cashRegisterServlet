package ua.training.cashregister.entity;

import java.util.Objects;

public abstract class Goods {
    private long id_goods;
    private String name;

    public Goods(String name) {
        this.name = name;
    }

    public long getId_goods() {
        return id_goods;
    }

    public void setId_goods(long id_goods) {
        this.id_goods = id_goods;
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
        return id_goods == goods.id_goods &&
                name.equals(goods.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_goods, name);
    }
}
