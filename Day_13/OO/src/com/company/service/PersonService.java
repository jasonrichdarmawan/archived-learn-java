package com.company.service;

import com.company.dao.PersonDao;
import com.company.model.PersonModel;

import java.util.List;

public class PersonService {
  private final PersonDao personDao;

  public PersonService(PersonDao personDao) {
    this.personDao = personDao;
  }

  public List<PersonModel> getAllPerson() {
    return personDao.selectAllPerson();
  }
}
