package org.example.greeting.spring;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DefaultableValue {
  private String defaultValue;
  private String type;


  @JsonProperty("default")
  public String getDefaultValue() {
    return defaultValue;
  }

  @JsonProperty("default")
  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  @JsonProperty("type")
  public String getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Default: " + defaultValue;
  }
}
