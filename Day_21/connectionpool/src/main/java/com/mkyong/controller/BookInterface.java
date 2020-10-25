package com.mkyong.controller;

import com.mkyong.model.BookModel;

import java.math.BigDecimal;
import java.util.List;

public interface BookInterface {
  int count();

  int save(BookModel book);

  int update(BookModel book);

  int deleteById(Long id);

  List<BookModel> findAll();

  List<BookModel> findByNameAndPrice(String name, BigDecimal price);

  BookModel findById(Long id);

  String getNameById(Long id);
}
