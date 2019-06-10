package ua.training.cashregister.service.goods;

import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.entity.GoodsApiece;

public class GoodsService {

    public Goods createGoodsApiece(String name, int apiece_price, int count) {
        return new GoodsApiece(name,apiece_price,count);
    }

    public Goods createGoodsByWeight(String name, int weight_price, int weight) {
        return new GoodsApiece(name,weight_price,weight);
    }
}
