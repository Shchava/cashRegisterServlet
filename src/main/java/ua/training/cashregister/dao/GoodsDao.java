package ua.training.cashregister.dao;

import ua.training.cashregister.entity.Goods;

import java.util.Optional;

public interface GoodsDao extends GenericDao<Goods>{
    Optional<Goods> findByName(String name);
}
