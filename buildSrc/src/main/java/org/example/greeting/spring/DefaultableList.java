package org.example.greeting.spring;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DefaultableList extends DefaultableValue {
  private List<SpringValue> values;

  @JsonProperty("values")
  public List<SpringValue> getValues() {
    return values;
  }

  @JsonProperty("values")
  public void setValues(List<SpringValue> values) {
    this.values = values;
  }

  @Override
  public String toString() {
    return super.toString() + "\n" +
        "Values: " + values;
  }
}
