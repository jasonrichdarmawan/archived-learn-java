package com.company;

import com.company.dao.PersonDataAccessService;
import com.company.datasource.MySQLDataSource;
import com.company.model.PersonModel;

import java.sql.SQLException;
import java.util.Iterator;

public class Main {

  public static void main(String[] args) {
    try {
      System.out.println(MySQLDataSource.connection.isClosed());
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    PersonDataAccessService personDataAccessService = new PersonDataAccessService(MySQLDataSource.connection);

    Iterator iterator = personDataAccessService.selectAllPerson().iterator();

    while (iterator.hasNext()) {
      PersonModel person = (PersonModel) iterator.next();
      System.out.println(person.getName());
    }
  }
}
