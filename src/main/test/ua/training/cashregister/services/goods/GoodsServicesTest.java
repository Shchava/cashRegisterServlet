package ua.training.cashregister.services.goods;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.entity.GoodsApiece;
import ua.training.cashregister.service.goods.GoodsService;

import static org.junit.Assert.*;

public class GoodsServicesTest {
    private String name1 = "testApiece";
    private String name2 = "testByWeight";
    private int count = 13;
    private int apiece_price = 400;
    private int weight = 10004;
    private int weightPrice = 10;
    private GoodsService service;

    @Before
    public void init (){
        service = new GoodsService();
    }

    @Test
    public void testCreateGoodsApiece(){
        Goods createdGoods = service.createGoodsApiece(name1,apiece_price,count);
        Goods testGoods = new GoodsApiece(name1,apiece_price,count);
    }
}
