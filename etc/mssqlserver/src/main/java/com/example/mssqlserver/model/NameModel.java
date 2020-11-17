package com.example.mssqlserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@SuppressWarnings("unused") // MyBatis does not need a defined constructor nor a setters.
@JsonInclude(JsonInclude.Include.NON_NULL) // filter: only non_null, alternative: spring.jackson.default-property-inclusion=NON_NULL in application.properties
public class NameModel {
  private Integer id;
  private String name;
  private String newid;

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getNewid() {
    return newid;
  }

  public boolean requestIsValid() {
    return !this.name.isEmpty();
  }
}
