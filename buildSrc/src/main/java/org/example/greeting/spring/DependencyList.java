package org.example.greeting.spring;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DependencyList {
  private final List<DependencyGroup> values;
  private final String type;

  public DependencyList(
      @JsonProperty ("type") String type,
      @JsonProperty ("values") List<DependencyGroup> values
  ) {
    this.type = type;
    this.values = values;
  }

  public String getType() {
    return type;
  }

  public List<DependencyGroup> getValues() {
    return values;
  }

  @Override
  public String toString() {
    return getValues().toString();
  }
}
