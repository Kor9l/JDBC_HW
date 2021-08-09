package by.ushau.jdbc.dao;

import by.ushau.jdbc.model.Brand;
import by.ushau.jdbc.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO extends AbstractDAO<Integer, Brand> {
    private final Connection connection;

    private final static String DELETE_BY_ID = "delete from Brand where id = ?";
    private final static String UPDATE_ENTITY = "update Book set brandName = ?, where id = ?";
    private final static String INSERT_ENTITY = "insert into Brand(brandName, id) values(?, ?)";
    private final static String SELECT_ALL = "select * from Brand";
    private final static String FIND_BY_ID = "select * from Brand where id = (?)";
    private final static String FIND_BRAND_ID = "select * from Brand where brandName = (?)";

    public BrandDAO(final Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean create(Brand entity) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ENTITY)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());
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
    public Brand update(Brand entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ENTITY)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());
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
    public List<Brand> findAll() {
        List<Brand> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Brand model = new Brand();
                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                result.add(model);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Brand findEntityById(Integer id) {
        Brand result = new Brand();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.setId(rs.getInt("id"));
                result.setName(rs.getString("title"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Brand findBrandId(String title) {
        Brand result = new Brand();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BRAND_ID)) {
            statement.setString(1, title);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.setId(rs.getInt("id"));
                result.setName(rs.getString("brandName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}

