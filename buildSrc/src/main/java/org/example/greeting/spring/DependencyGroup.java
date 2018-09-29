package org.example.greeting.spring;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DependencyGroup {
  private final String name;
  private final List<Dependency> values;

  public DependencyGroup (
      @JsonProperty("name") String name,
      @JsonProperty("values")List<Dependency> values
      ) {
    this.name = name;
    this.values = values;
  }

  public String getName() {
    return name;
  }

  public List<Dependency> getValues() {
    return values;
  }

  @Override
  public String toString() {
    return "Name: " + name + "\n" +
        "Values: " + values;
  }
}
