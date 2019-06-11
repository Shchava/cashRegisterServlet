package ua.training.cashregister.service.goods;

import ua.training.cashregister.dao.DaoFactory;
import ua.training.cashregister.dao.GoodsDao;
import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.entity.GoodsApiece;

import java.util.List;

public class GoodsService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public Goods createGoodsApiece(String name, int apiece_price, int count) {
        return new GoodsApiece(name,apiece_price,count);
    }

    public Goods createGoodsByWeight(String name, int weight_price, int weight) {
        return new GoodsApiece(name,weight_price,weight);
    }

    public List<Goods> getAllGoods(){
        try(GoodsDao dao = daoFactory.createGoodsDao()) {
            return dao.findAll();
        }
    }
}
