package com.company.dao;

import com.company.model.PersonModel;

import java.util.List;
import java.util.Optional;

public interface PersonDao {
  int insertPerson(String name, int age);

  List<PersonModel> selectAllPerson();

  Optional<PersonModel> selectPersonById(int id);

  int deletePersonById(int id);

  int updatePersonById(int id, String name, int age);
}
