package com.company.api;

import com.company.model.PersonModel;
import com.company.service.PersonService;

import java.util.List;

public class PersonController {
  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  public List<PersonModel> getAllPerson() {
    return personService.getAllPerson();
  }
}
