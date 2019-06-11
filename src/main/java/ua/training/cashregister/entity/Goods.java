package ua.training.cashregister.entity;

import java.util.Objects;

public abstract class Goods {
    private long id;
    private String name;

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
