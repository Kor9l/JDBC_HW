package by.ushau.jdbc.dao;

import by.ushau.jdbc.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends AbstractDAO<Integer, Product> {
    private final Connection connection;


    private final static String DELETE_BY_ID = "delete from Product where id = ?";
    private final static String UPDATE_ENTITY = "update Product set name = ?,set cost = ?, set brand_id = ?, where id = ?";
    private final static String INSERT_ENTITY = "insert into Product(title, id, cost, brand_id) values(?, ?,?,null)";
    private final static String SELECT_ALL_BOOKS = "select * from Product";
    private final static String FIND_BY_ID = "select * from Product where id = (?)";
    private final static String FIND_BY_BRAND = "select * from Product where brand_id = (?)";
    private final static String FIND_COST_RANGE = "select * from Product where cost < ? and cost > ?";
    private final static String FIND_BRAND_EXIST = "select * from Product where brand_id >0";
    private final static String FIND_MAX_COST = "select * from Product where cost = (select max(cost) from Product)";


    public ProductDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Product entity) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ENTITY)) {
            statement.setString(1, entity.getTitle());
            statement.setInt(2, entity.getId());
            statement.setDouble(3, entity.getCost());
            statement.setInt(4, entity.getBrand_id());
            int rs = statement.executeUpdate();
            System.out.println("Completed successfully.");
            System.out.print(rs + " the number of columns affected by the query");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            int rs = statement.executeUpdate();
            System.out.println("Completed successfully.");
            System.out.print(rs + " the number of columns affected by the query");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Product update(Product entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ENTITY)) {
            statement.setString(1, entity.getTitle());
            statement.setInt(4, entity.getId());
            statement.setDouble(2, entity.getCost());
            statement.setInt(3, entity.getBrand_id());
            int rs = statement.executeUpdate();
            System.out.println("Completed successfully.");
            System.out.print(rs + " the number of columns affected by the query");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return entity;
    }

    @Override
    public List<Product> findAll() {
        List<Product> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BOOKS)) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product model = new Product();
                model.setId(rs.getInt("id"));
                model.setTitle(rs.getString("title"));
                model.setCost(rs.getDouble("cost"));
                model.setBrand_id(rs.getInt("brand_id"));
                result.add(model);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Product findEntityById(Integer id) {
        Product result = new Product();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.setId(rs.getInt("id"));
                result.setTitle(rs.getString("title"));
                result.setCost(rs.getDouble("cost"));
                result.setBrand_id(rs.getInt("brand_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Product> findMax() {
        List<Product> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_MAX_COST)) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product model = new Product();
                model.setId(rs.getInt("id"));
                model.setTitle(rs.getString("title"));
                model.setCost(rs.getDouble("cost"));
                model.setBrand_id(rs.getInt("brand_id"));
                result.add(model);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Product> findByBrand(int brand_id) throws SQLException {
        List<Product> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_BRAND)) {
            statement.setInt(1, brand_id);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product model = new Product();
                model.setId(rs.getInt("id"));
                model.setTitle(rs.getString("title"));
                model.setCost(rs.getDouble("cost"));
                model.setBrand_id(rs.getInt("brand_id"));
                result.add(model);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Product> findByCostRange(double min, double max) throws SQLException {
        List<Product> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_COST_RANGE)) {
            statement.setDouble(1, max);
            statement.setDouble(2, min);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product model = new Product();
                model.setId(rs.getInt("id"));
                model.setTitle(rs.getString("title"));
                model.setCost(rs.getDouble("cost"));
                model.setBrand_id(rs.getInt("brand_id"));
                result.add(model);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Product> findByBrandExist() throws SQLException {
        List<Product> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BRAND_EXIST)) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product model = new Product();
                model.setId(rs.getInt("id"));
                model.setTitle(rs.getString("title"));
                model.setCost(rs.getDouble("cost"));
                model.setBrand_id(rs.getInt("brand_id"));
                result.add(model);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
