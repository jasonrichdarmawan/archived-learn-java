package com.mkyong.repository;

import com.mkyong.model.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

// this is example.

@Repository
public class NamedParameterJdbcBookRepository extends BookRepository {

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Override
  public int update(BookModel book) {
    return namedParameterJdbcTemplate.update(
            "update books set price = :price where id = :id",
            new BeanPropertySqlParameterSource(book));
  }

  @Override
  public BookModel findById(Long id) {
    return namedParameterJdbcTemplate.queryForObject(
            "select * from books where id = :id",
            new MapSqlParameterSource("id", id),
            (rs, rowNum) ->
                    new BookModel(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getBigDecimal("price")
                    )
    );
  }

  @Override
  public List<BookModel> findByNameAndPrice(String name, BigDecimal price) {

    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("name", "%" + name + "%");
    mapSqlParameterSource.addValue("price", price);

    return namedParameterJdbcTemplate.query(
            "select * from books where name like :name and price <= :price",
            mapSqlParameterSource,
            (rs, rowNum) ->
                    new BookModel(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getBigDecimal("price")
                    )
    );
  }

}
