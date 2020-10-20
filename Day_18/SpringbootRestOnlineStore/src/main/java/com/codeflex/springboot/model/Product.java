package com.codeflex.springboot.model;

import com.codeflex.springboot.datasource.ProductDataSource;
import com.codeflex.springboot.service.ProductServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

public class Product {
  private long id;
  private String name;
  private int categoryId;
  private double price;

  public Product(long id, String name, int categoryId, double price) {
    if (id == 0) {
      ResultSet resultSet = null;
      try {
        resultSet = ProductServiceImpl.productDataSource.executeQuery("SELECT id FROM product ORDER BY id DESC LIMIT 1");
        resultSet.next();
        this.id = resultSet.getLong("id") + 1;
      } catch (SQLException throwables) {
        throwables.printStackTrace();
        this.id = 1;
      } finally {
        try {
          resultSet.close();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
      }
    } else {
      this.id = id;
    }

    this.name = name;
    this.categoryId = categoryId;
    this.price = price;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Product other = (Product) obj;
    if (id != other.id)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Product [id=" + id + ", name=" + name + ", categoryId=" + categoryId
            + ", price=" + price + "]";
  }


}
