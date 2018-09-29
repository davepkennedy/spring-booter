package org.example.greeting.spring;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class BuildType {
  private final String action;
  private final String description;
  private final String id;
  private final String name;
  private final Map<String, String> tags;

  public BuildType(
      @JsonProperty ("action")
      String action,
      @JsonProperty("description")
      String description,
      @JsonProperty("id")
      String id,
      @JsonProperty("name")
      String name,
      @JsonProperty("tags")
      Map<String,String>tags
  ) {
    this.action = action;
    this.description = description;
    this.id = id;
    this.name = name;
    this.tags = tags;
  }

  public String getAction() {
    return action;
  }

  public String getDescription() {
    return description;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Map<String, String> getTags() {
    return tags;
  }

  @Override
  public String toString() {
    return "BuildType: " + description;
  }
}
