package ua.training.cashregister.dao.impl;

import ua.training.cashregister.dao.GoodsDao;
import ua.training.cashregister.dao.mapper.GoodsMapper;
import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.entity.GoodsApiece;
import ua.training.cashregister.entity.enums.GoodsTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCGoodsDao implements GoodsDao {
    private Connection connection;

    public JDBCGoodsDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Goods entity) {
        if(entity instanceof GoodsApiece){
            return createGoodsApiece((GoodsApiece) entity);
        }else{
            return createGoodsByWeight();
        }

    }

    @Override
    public List<Goods> findAll() {
        List<Goods> goodsList = new ArrayList<>();
        GoodsMapper mapper = new GoodsMapper();

        final String query = "SELECT * FROM goods";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Goods goods = mapper.extractFromResultSet(rs);
                goodsList.add(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return goodsList;
        }

        return goodsList;
    }

    @Override
    public Optional<Goods> findById(long id) {
        Goods found = null;
        GoodsMapper mapper = new GoodsMapper();

        final String query = "SELECT * FROM goods WHERE id_goods = " + id +";";
        try(Statement statement =  connection.createStatement()){
            ResultSet result = statement.executeQuery(query);
            if(result.next()){
                found = mapper.extractFromResultSet(result);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.ofNullable(found);
    }

    @Override
    public List<Goods> findByName(String name){
        List<Goods> goodsList = new ArrayList<>();
        GoodsMapper mapper = new GoodsMapper();

        final String query = "SELECT * FROM goods WHERE name like CONCAT('%',?,'%')";

        try(PreparedStatement statement =  connection.prepareStatement(query)){
            statement.setString(1,name);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Goods goods = mapper.extractFromResultSet(rs);
                goodsList.add(goods);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return goodsList;
    }

    @Override
    public boolean update(Goods entity) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        boolean affected = false;
        final String query = "DELETE FROM goods WHERE id_goods = " + id + ";";
        try(Statement statement =  connection.createStatement()){
            affected = statement.execute(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return affected;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private boolean createGoodsApiece(GoodsApiece goodsApiece){
        boolean created = false;
        final String query = "INSERT INTO goods(name, apiece_price,count, type) VALUES(?,?,?,?)";
        try(PreparedStatement statement =  connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,goodsApiece.getName());
            statement.setInt(2,goodsApiece.getApiece_price());
            statement.setInt(3,goodsApiece.getCount());
            statement.setString(4, GoodsTypes.APIECE.name());


            int affected = statement.executeUpdate();
            if(affected == 1){
                getId(goodsApiece,statement);
                created = true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return created;
    }

    private boolean createGoodsByWeight(){
        return false;
    }

    private void getId(Goods entity, Statement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            entity.setId(generatedKeys.getLong(1));
            System.out.println();
        }
    }
}
