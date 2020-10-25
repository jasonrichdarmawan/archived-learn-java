package com.example.designpattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DesignpatternApplication implements CommandLineRunner {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public static void main(String[] args) {
    SpringApplication.run(DesignpatternApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    createTable();
  }

  private void createTable() {
    jdbcTemplate.execute(
            "CREATE TABLE IF NOT EXISTS `staff` (" +
                    "`id` INT NOT NULL AUTO_INCREMENT," +
                    "`nama` VARCHAR(45) NULL," +
                    "`gajiPokok` DECIMAL(15,3) NULL," +
                    "`absensi` INT NULL," +
                    "`izin` INT NULL," +
                    "PRIMARY KEY(`id`));"
    );
  }
}
