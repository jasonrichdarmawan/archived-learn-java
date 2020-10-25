package com.mkyong.repository;

import com.mkyong.controller.BookInterface;
import com.mkyong.model.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("bookRepository")
public class BookRepository implements BookInterface {

  // Spring Boot will create and configure DataSource and JdbcTemplate
  // To use it, just @Autowired
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public int count() {
    return jdbcTemplate
            .queryForObject("select count(*) from books", Integer.class);
  }

  @Override
  public int save(BookModel book) {
    return jdbcTemplate.update(
            "insert into books (name, price) values(?,?)",
            book.getName(), book.getPrice());
  }

  @Override
  public int update(BookModel book) {
    return jdbcTemplate.update(
            "update books set name = ?, price = ? where id = ?",
            book.getName(), book.getPrice(), book.getId());
  }


  @Override
  public int deleteById(Long id) {
    return jdbcTemplate.update(
            "DELETE FROM books WHERE id = ?",
            id);
  }

  @Override
  public List<BookModel> findAll() {
    return jdbcTemplate.query(
            "select * from books",
            (rs, rowNum) ->
                    new BookModel(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getBigDecimal("price")
                    )
    );
  }

  // jdbcTemplate.queryForObject, populates a single object
  @Override
  public BookModel findById(Long id) {
    try {
      return jdbcTemplate.queryForObject(
              "select * from books where id = ?",
              new Object[]{id},
              (rs, rowNum) ->
                      new BookModel(
                              rs.getLong("id"),
                              rs.getString("name"),
                              rs.getBigDecimal("price")
                      )
      );
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public List<BookModel> findByNameAndPrice(String name, BigDecimal price) {
    return jdbcTemplate.query(
            "select * from books where name like ? and price <= ?",
            new Object[]{"%" + name + "%", price},
            (rs, rowNum) ->
                    new BookModel(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getBigDecimal("price")
                    )
    );
  }

  @Override
  public String getNameById(Long id) {
    return jdbcTemplate.queryForObject(
            "select name from books where id = ?",
            new Object[]{id},
            String.class
    );
  }

}
