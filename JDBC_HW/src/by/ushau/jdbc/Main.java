package by.ushau.jdbc;

import by.ushau.jdbc.dao.BrandDAO;
import by.ushau.jdbc.dao.ProductDAO;
import by.ushau.jdbc.model.Brand;
import by.ushau.jdbc.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionString = "jdbc:mysql://localhost:3306/JDBC?serverTimezone=UTC";

        try (Connection connection = DriverManager.getConnection(
                connectionString,
                System.getenv("DB_USERNAME"),
                System.getenv("DB_PASSWORD"))) {

            ProductDAO productDAO = new ProductDAO(connection);
            BrandDAO brandDAO = new BrandDAO(connection);


            //productDAO.create(new Product("Шоколад",5,103.3));
            //brandDAO.create(new Brand("Комбинат №1",1));
            //
            System.out.println("Show all products:");
          for (var item: productDAO.findAll()) {
               System.out.println(item);
           }
            System.out.println("Find by cost range 100...200:");
            System.out.println(productDAO.findByCostRange(100,200));
            System.out.println("Find by brand exist:");
            System.out.println(productDAO.findByBrandExist());
            System.out.println("Show product with max cost:");
            System.out.println(productDAO.findMax());
            System.out.println("Show product with brand 'Комбинат №1':");
            System.out.println(productDAO.findByBrand("Комбинат №1"));

        }
    }
}

