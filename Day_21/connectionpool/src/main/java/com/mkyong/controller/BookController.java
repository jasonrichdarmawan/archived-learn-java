package com.mkyong.controller;

import com.mkyong.model.BookModel;
import com.mkyong.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<BookModel> findAll() {
    return this.bookService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") Long id) {
    BookModel book = this.bookService.findById(id);
    if (book == null) {
      return new ResponseEntity<String>("NOT FOUND", HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<BookModel>(book, HttpStatus.OK);
    }
  }

  @PostMapping
  public ResponseEntity<String> save(@RequestBody BookModel book) {
    if (this.bookService.save(book) == 1) {
      return new ResponseEntity<String>("CREATED", HttpStatus.CREATED);
    } else {
      return new ResponseEntity<String>("NOT FOUND", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody BookModel book) {
    book.setId(id);
    if (this.bookService.update(book) == 1) {
      return new ResponseEntity<String>("UPDATED", HttpStatus.OK);
    } else {
      return new ResponseEntity<String>("NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteById(@PathVariable Long id) {
    if (this.bookService.deleteById(id) == 1) {
      return new ResponseEntity<String>("DELETED", HttpStatus.OK);
    } else {
      return new ResponseEntity<String>("NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }
}
