package com.example.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ReviewApplication implements CommandLineRunner {
  /**
   * For readibility, every configuration starts here.
   */
  @Autowired
  JdbcTemplate jdbcTemplate;

  public static void main(String[] args) {
    SpringApplication.run(ReviewApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("ReviewApplication...");
    runCreateTable();
  }

  void runCreateTable() {
//    jdbcTemplate.execute("DROP TABLE IF EXISTS student");
//    jdbcTemplate.execute("DROP TABLE IF EXISTS grades");
    jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `student` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `fullname` VARCHAR(45) NULL,\n" +
            "  `address` VARCHAR(45) NULL,\n" +
            "  `status` VARCHAR(45) NULL,\n" +
            "  `absensi` INT NULL,\n" +
            "  `physics` INT NULL,\n" +
            "  `calculus` INT NULL,\n" +
            "  `biologi` INT NULL,\n" +
            "  PRIMARY KEY (`id`));");
    // TODO: use 2 tables (student and grades). Obstacle: How to get student ID without creating multiple connection to the DB.
//    jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `grades` (\n" +
//						" `id` INT NOT NULL,\n" +
//						" `physics` INT NULL,\n" +
//						" `calculus` INT NULL,\n" +
//						" `biologi` INT NULL,\n" +
//						" PRIMARY KEY(`id`));");
  }
}
