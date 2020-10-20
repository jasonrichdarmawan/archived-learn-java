package com.codeflex.springboot.service;

import com.codeflex.springboot.datasource.ProductDataSource;
import com.codeflex.springboot.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
  public static ProductDataSource productDataSource = new ProductDataSource("jdbc", "mysql", "localhost", 3306, "day18", "root", "password");

  public List<Product> findAllProducts() {
    List<Product> products = new ArrayList<>();

    ResultSet resultSet = productDataSource.executeQuery("SELECT * FROM product");
    while (true) {
      try {
        if (!resultSet.next()) {
          resultSet.close();
          break;
        }
        ;
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }

      try {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        int categoryId = resultSet.getInt("categoryId");
        double price = resultSet.getDouble("price");
        products.add(new Product(id, name, categoryId, price));

      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }

    return products;
  }

  public Product findById(long id) {
    Product product = null;
    String sql = String.format("SELECT * FROM product WHERE id=%d LIMIT 1", id);
    ResultSet resultSet = productDataSource.executeQuery(sql);
    while (true) {
      try {
        if (!resultSet.next()) {
          resultSet.close();
          break;
        }
        ;
        String name = resultSet.getString("name");
        int categoryId = resultSet.getInt("categoryId");
        double price = resultSet.getDouble("price");

        product = new Product(id, name, categoryId, price);
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    return product;
  }

  public Product findByName(String name) {
    Product product = null;
    String sql = String.format("SELECT * FROM product WHERE name='%s' LIMIT 1", name);
    ResultSet resultSet = productDataSource.executeQuery(sql);
    while (true) {
      try {
        if (!resultSet.next()) {
          resultSet.close();
          break;
        }
        ;
        long id = resultSet.getLong("id");
        int categoryId = resultSet.getInt("categoryId");
        double price = resultSet.getDouble("price");

        product = new Product(id, name, categoryId, price);
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    return product;
  }

  public void saveProduct(Product product) {
    String name = product.getName();
    int categoryId = product.getCategoryId();
    double price = product.getPrice();
    String sql = String.format("INSERT INTO product (name, categoryId, price) VALUES ('%s', '%d', '%.3f')", name, categoryId, price);
    productDataSource.executeUpdate(sql);
  }

  public void updateProduct(Product product) {
    long id = product.getId();
    String name = product.getName();
    int categoryId = product.getCategoryId();
    double price = product.getPrice();
    String sql = String.format("UPDATE product SET name='%s', categoryId=%d, price=%.3f WHERE id=%d", name, categoryId, price, id);
    productDataSource.executeUpdate(sql);
  }

  public void deleteProductById(long id) {
    String sql = String.format("DELETE FROM product WHERE id=%d", id);
    productDataSource.executeUpdate(sql);
  }

  public boolean isProductExist(Product product) {
    return findByName(product.getName()) != null;
  }

  public void deleteAllProducts() {
    productDataSource.executeUpdate("DELETE FROM product");
  }

}
