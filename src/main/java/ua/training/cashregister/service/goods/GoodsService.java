package ua.training.cashregister.service.goods;

import ua.training.cashregister.dao.DaoFactory;
import ua.training.cashregister.dao.GoodsDao;
import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.entity.GoodsApiece;

import java.util.List;
import java.util.Optional;

public class GoodsService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void removeFromWarehouse(Goods goods,int amount){
        //TODO implement
    };

    public boolean addGoods(Goods goods) {
        try (GoodsDao dao = daoFactory.createGoodsDao()) {
            return dao.create(goods);
        }
    }

    public Optional<Goods> findGoods(long id){
        try(GoodsDao dao = daoFactory.createGoodsDao()){
            return dao.findById(id);
        }
    }

    public List<Goods> findGoods(String name){
        try(GoodsDao dao = daoFactory.createGoodsDao()){
            return dao.findByName(name);
        }
    }

    public List<Goods> getAllGoods(){
        try(GoodsDao dao = daoFactory.createGoodsDao()) {
            return dao.findAll();
        }
    }



    public Optional<Goods> findFirst(String name){
        List<Goods> found = findGoods(name);
        if(found.size() > 0){
            return Optional.ofNullable(found.get(0));
        }else{
            return Optional.empty();
        }
    }

    public int getNumberOfRecords() {
        try(GoodsDao dao = daoFactory.createGoodsDao()) {
            return dao.getNumberOfRows();
        }
    }

    public List<Goods> getGoods(int start,int count) {
        try(GoodsDao dao = daoFactory.createGoodsDao()) {
            return dao.find(start,count);
        }
    }

    public boolean changeGoods(Goods changed) {
        try(GoodsDao dao = daoFactory.createGoodsDao()) {
            return dao.update(changed);
        }
    }
}
