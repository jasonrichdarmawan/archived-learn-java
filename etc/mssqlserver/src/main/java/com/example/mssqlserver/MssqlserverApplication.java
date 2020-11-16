package com.example.mssqlserver;

import com.example.mssqlserver.model.NameModel;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MappedTypes(NameModel.class)
@MapperScan(basePackages = "com.example.mssqlserver.mapper")
@SpringBootApplication
public class MssqlserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MssqlserverApplication.class, args);
	}

}
