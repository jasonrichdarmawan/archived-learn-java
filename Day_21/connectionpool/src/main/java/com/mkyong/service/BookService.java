package com.mkyong.service;

import com.mkyong.model.BookModel;
import com.mkyong.controller.BookInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookService implements BookInterface {
  private final BookInterface bookRepository;

  @Autowired
  public BookService(@Qualifier("bookRepository") BookInterface bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<BookModel> findAll() {
    return this.bookRepository.findAll();
  }

  @Override
  public List<BookModel> findByNameAndPrice(String name, BigDecimal price) {
    return null;
  }

  public BookModel findById(Long id) {
    return this.bookRepository.findById(id);
  }

  @Override
  public String getNameById(Long id) {
    return null;
  }

  @Override
  public int count() {
    return 0;
  }

  public int save(BookModel book) {
    return this.bookRepository.save(book);
  }

  public int update(BookModel book) {
    return this.bookRepository.update(book);
  }

  public int deleteById(Long id) {
    return this.bookRepository.deleteById(id);
  }
}
