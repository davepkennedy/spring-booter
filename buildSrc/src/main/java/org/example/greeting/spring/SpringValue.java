package org.example.greeting.spring;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpringValue {
  private String id;
  private String name;

  @JsonProperty("id")
  public String getId() {return id;}

  @JsonProperty("id")
  public void setId(String id) {this.id = id;}

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Id: " + getId() + " Name: " + getName();
  }
}
