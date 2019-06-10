package ua.training.cashregister.dao.mapper;

import org.omg.CORBA.Object;
import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.entity.GoodsApiece;
import ua.training.cashregister.entity.GoodsTypes;
import ua.training.cashregister.entity.Roles;
import ua.training.cashregister.service.goods.GoodsService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsMapper implements ObjectMapper<Goods> {

    public Goods extractFromResultSet(ResultSet rs) throws SQLException{
        Goods extracted;
        GoodsTypes type = GoodsTypes.valueOf(rs.getString("type"));
        if(type == GoodsTypes.APIECE){
            extracted = extractApiece(rs);
        }else{
            extracted = extractByWeight(rs);
        }
        return extracted;
    };

    private Goods extractApiece(ResultSet rs) throws SQLException {
        GoodsService service = new GoodsService();
        String name = rs.getString("name");
        int apiece_price = rs.getInt("apiece_price");
        int count = rs.getInt("count");
        Goods extracted =  service.createGoodsApiece(name,apiece_price,count);
        extracted.setId(rs.getLong("id_goods"));
        return extracted;
    }

    private Goods extractByWeight(ResultSet rs) throws SQLException {
        GoodsService service = new GoodsService();
        String name = rs.getString("name");
        int weightPrice = rs.getInt("weight_price");
        int weight = rs.getInt("weight");
        Goods extracted = service.createGoodsByWeight(name,weightPrice,weight);
        extracted.setId(rs.getLong("id_goods"));
        return extracted;
    }
}
