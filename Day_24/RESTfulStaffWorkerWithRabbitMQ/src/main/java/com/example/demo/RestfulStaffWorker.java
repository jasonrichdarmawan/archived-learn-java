package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication(scanBasePackages = {"com.example.demo"})
public class RestfulStaffWorker implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(RestfulStaffWorker.class, args);
  }

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void run(String... args) throws Exception {
    createTable();
  }

  public void createTable() {
    this.jdbcTemplate.execute(
            "CREATE TABLE IF NOT EXISTS `day24`.`staff` (\n" +
                    "  `IDKaryawan` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Nama` VARCHAR(45) NULL,\n" +
                    "  `TunjanganPulsa` DECIMAL(15,3) NULL,\n" +
                    "  `GajiPokok` DECIMAL(15,3) NULL,\n" +
                    "  `AbsensiHari` INT NULL,\n" +
                    "  `TunjanganMakan` DECIMAL(15,3) NULL,\n" +
                    "  `Email` JSON NULL,\n" +
                    "  PRIMARY KEY (`IDKaryawan`));\n"
    );
  }


}

