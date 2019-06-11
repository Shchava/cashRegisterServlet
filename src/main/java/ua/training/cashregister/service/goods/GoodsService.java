package ua.training.cashregister.service.goods;

import ua.training.cashregister.dao.DaoFactory;
import ua.training.cashregister.dao.GoodsDao;
import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.entity.GoodsApiece;

import java.util.List;
import java.util.Optional;

public class GoodsService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public boolean addGoods(Goods goods){
        try(GoodsDao dao = daoFactory.createGoodsDao()){
            return dao.create(goods);
        }
    }

    public Optional<Goods> findGoods(long id){
        try(GoodsDao dao = daoFactory.createGoodsDao()){
            return dao.findById(id);
        }
    }

    public List<Goods> getAllGoods(){
        try(GoodsDao dao = daoFactory.createGoodsDao()) {
            return dao.findAll();
        }
    }
}
