package ua.training.cashregister.dao;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.MethodSorters;
import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.goods.GoodsService;

import static org.junit.Assert.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GoodsDaoTest {
    private String nameApiece = "testApiece";
    private String nameByWeight = "nameByWeight";
    private int count = 13;
    private int apiece_price = 400;
    private int weight = 10004;
    private int weightPrice = 10;
    private GoodsService service;
    private GoodsDao dao;
    private static long idApiece;
    private static long idByWeight;
    private static Goods goodsApiece;
    private static Goods goodsByWeight;

    @Before
    public void init(){
        service = new GoodsService();
        dao = DaoFactory.getInstance().createGoodsDao();
    }
//
//    @Test
//    @Order(1)
//    public void atestCreateApiceGoodsAndFindByID(){
//        Goods goodsApiece = service.createGoodsApiece(nameApiece,apiece_price,count);
//        assertTrue(dao.create(goodsApiece));
//        Goods dbGoods = dao.findById(goodsApiece.getId()).get();
//        assertEquals(goodsApiece,dbGoods);
//        idApiece = dbGoods.getId();
//    }
//
//    @Test
//    @Order(2)
//    public void atestCreateByWeightGoodsAndFindByID(){
//        Goods goodsByWeight = service.createGoodsByWeight(nameByWeight,weightPrice,weight);
//        assertTrue(dao.create(goodsByWeight));
//        Goods dbGoods = dao.findById(goodsByWeight.getId()).get();
//        assertEquals(goodsByWeight,dbGoods);
//        idByWeight = dbGoods.getId();
//    }
//
//    @Test
//    @Order(5)
//    public void wtestDelete(){
//        assertTrue(dao.delete(idApiece));
//        assertFalse(dao.findById(idApiece).isPresent());
//        assertTrue(dao.delete(idByWeight));
//        assertFalse(dao.findById(idByWeight).isPresent());
//    }

//    @Test
//    @Order(6)
//    public void xtestClose() {
//        Goods goodsApiece = service.createGoodsApiece(nameApiece,apiece_price,count);
//        Goods goodsByWeight = service.createGoodsByWeight(nameByWeight,weightPrice,weight);
//        dao.close();
//        assertFalse(dao.create(goodsApiece));
//        assertFalse(dao.delete(goodsApiece.getId()));
//        assertFalse(dao.create(goodsByWeight));
//        assertFalse(dao.delete(goodsByWeight.getId()));
//    }
}
