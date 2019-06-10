package ua.training.cashregister.entity;

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


}
