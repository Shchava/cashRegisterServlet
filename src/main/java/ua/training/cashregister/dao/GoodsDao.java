package ua.training.cashregister.dao;

import ua.training.cashregister.entity.Goods;

import java.util.List;
import java.util.Optional;

public interface GoodsDao extends GenericDao<Goods>{
    List<Goods> findByName(String name);
}
