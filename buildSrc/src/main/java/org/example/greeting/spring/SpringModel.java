package org.example.greeting.spring;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpringModel {
  private final ProjectType type;
  private final DefaultableValue version;
  private final DefaultableList packaging;
  private final DefaultableValue packageName;
  private final DefaultableValue name;
  private final DefaultableList language;
  private final DefaultableList javaVersion;
  private final DefaultableValue groupId;
  private final DefaultableValue description;
  private final DefaultableList bootVersion;
  private final DefaultableValue artifactId;
  private final DependencyList dependencies;

  public SpringModel (
      @JsonProperty("type") ProjectType type,
      @JsonProperty("version") DefaultableValue version,
      @JsonProperty("packaging") DefaultableList packaging,
      @JsonProperty("packageNAme") DefaultableValue packageName,
      @JsonProperty("name") DefaultableValue name,
      @JsonProperty("language") DefaultableList language,
      @JsonProperty("javaVersion") DefaultableList javaVersion,
      @JsonProperty("groupId") DefaultableValue groupId,
      @JsonProperty("description") DefaultableValue description,
      @JsonProperty("bootVersion") DefaultableList bootVersion,
      @JsonProperty("artifactId") DefaultableValue artifactId,
      @JsonProperty("dependencies") DependencyList dependencies
  ) {
    this.type = type;
    this.version = version;
    this.packaging = packaging;
    this.packageName = packageName;
    this.name = name;
    this.language = language;
    this.javaVersion = javaVersion;
    this.groupId = groupId;
    this.description = description;
    this.bootVersion = bootVersion;
    this.artifactId = artifactId;
    this.dependencies = dependencies;
  }

  public ProjectType getType() {
    return type;
  }

  public DefaultableValue getVersion() {
    return version;
  }

  @Override
  public String toString() {
    return "Project: " + getType() + "\n" +
        "Version: " + getVersion() + "\n" +
        "Packaging: " + getPackaging() + "\n" +
        "Package Name: " + getPackageName() + "\n" +
        "Name: " + getName() + "\n" +
        "Language: " + getLanguage() + "\n" +
        "JavaVersion: " + getJavaVersion() + "\n" +
        "Group Id: " + getGroupId() + "\n" +
        "Description: " + getDescription() + "\n" +
        "Boot Version: " + getBootVersion() + "\n" +
        "Artifact Id: " + getArtifactId() + "\n" +
        "Dependencies: " + getDependencies();
  }

  public DefaultableList getPackaging() {
    return packaging;
  }

  public DefaultableValue getPackageName() {
    return packageName;
  }

  public DefaultableValue getName() {
    return name;
  }

  public DefaultableList getLanguage() {
    return language;
  }

  public DefaultableList getJavaVersion() {
    return javaVersion;
  }

  public DefaultableValue getGroupId() {
    return groupId;
  }

  public DefaultableValue getDescription() {
    return description;
  }

  public DefaultableList getBootVersion() {
    return bootVersion;
  }

  public DefaultableValue getArtifactId() {
    return artifactId;
  }

  public DependencyList getDependencies() {
    return dependencies;
  }
}
