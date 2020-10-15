package com.company.dao;

import com.company.model.PersonModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDataAccessService implements PersonDao {

  private final Connection connection;

  public PersonDataAccessService(Connection connection) {
    this.connection = connection;
  }

  @Override
  public int insertPerson(String name, int age) {
    return 0;
  }

  @Override
  public List<PersonModel> selectAllPerson() {
    List<PersonModel> persons = new ArrayList<>();
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM emp");
      while (resultSet.next()) {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        int age = resultSet.getInt(3);
        PersonModel person = new PersonModel(id, name, age);
        persons.add(person);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return persons;
  }

  @Override
  public Optional<PersonModel> selectPersonById(int id) {
    PersonModel person = null;
    return Optional.ofNullable(person);
  }

  @Override
  public int deletePersonById(int id) {
    return 0;
  }

  @Override
  public int updatePersonById(int id, String name, int age) {
    return 0;
  }
}
