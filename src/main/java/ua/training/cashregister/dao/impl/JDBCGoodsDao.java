package ua.training.cashregister.dao.impl;

import ua.training.cashregister.dao.GoodsDao;
import ua.training.cashregister.dao.mapper.GoodsMapper;
import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.entity.GoodsApiece;
import ua.training.cashregister.entity.GoodsByWeight;
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
            return createGoodsByWeight((GoodsByWeight) entity);
        }
    }

    @Override
    public List<Goods> find(int start, int count) {
        final String query = " SELECT SQL_CALC_FOUND_ROWS * FROM goods LIMIT " + start +","+count;
        return selectAllFromQuery(query);
    }

    @Override
    public List<Goods> findAll() {
        final String query = "SELECT * FROM goods";
        return selectAllFromQuery(query);
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
    public int getNumberOfRows() {
        int count = 0;
        final String query = "SELECT COUNT(*)FROM goods";
        try(Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                count = rs.getInt("COUNT(*)");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return count;
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
        if(entity instanceof GoodsApiece){
            return updateGoodsApiece((GoodsApiece) entity);
        }else{
            return updateGoodsByWeight((GoodsByWeight) entity);
        }
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

    private boolean createGoodsByWeight(GoodsByWeight goodsByWeight){
        boolean created = false;
        final String query = "INSERT INTO goods(name, weight,weight_price, type) VALUES(?,?,?,?)";
        try(PreparedStatement statement =  connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,goodsByWeight.getName());
            statement.setInt(2,goodsByWeight.getWeight());
            statement.setInt(3,goodsByWeight.getWeight_price());
            statement.setString(4, GoodsTypes.BY_WEIGHT.name());


            int affected = statement.executeUpdate();
            if(affected == 1){
                getId(goodsByWeight,statement);
                created = true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return created;
    }

    private boolean updateGoodsApiece(GoodsApiece goodsApiece){
        boolean created = false;
        final String query = "UPDATE goods SET name = ?, count = ?,apiece_price = ? WHERE id_goods = ?";
        try(PreparedStatement statement =  connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,goodsApiece.getName());
            statement.setInt(2,goodsApiece.getCount());
            statement.setInt(3,goodsApiece.getApiece_price());
            statement.setLong(4,goodsApiece.getId());

            int affected = statement.executeUpdate();
            created = affected == 1;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return created;
    }

    private boolean updateGoodsByWeight(GoodsByWeight goodsByWeight){
        boolean created = false;
        final String query = "UPDATE goods SET name = ?, weight = ?,weight_price = ? WHERE id_goods = ?";
        try(PreparedStatement statement =  connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,goodsByWeight.getName());
            statement.setInt(2,goodsByWeight.getWeight());
            statement.setInt(3,goodsByWeight.getWeight_price());
            statement.setLong(4,goodsByWeight.getId());

            int affected = statement.executeUpdate();
            created = affected == 1;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return created;
    }

    private void getId(Goods entity, Statement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            entity.setId(generatedKeys.getLong(1));
            System.out.println();
        }
    }

    private List<Goods> selectAllFromQuery(String query){
        List<Goods> goodsList = new ArrayList<>();
        GoodsMapper mapper = new GoodsMapper();

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
}
