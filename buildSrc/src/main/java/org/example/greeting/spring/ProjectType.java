package org.example.greeting.spring;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProjectType extends DefaultableValue {
  private List<BuildType> values;

  @JsonProperty("values")
  public List<BuildType> getValues() {
    return values;
  }

  @JsonProperty("values")
  public void setValues(List<BuildType> values) {
    this.values = values;
  }

  @Override
  public String toString() {
    return super.toString() + "\n" +
        "Values: " + values;
  }
}
