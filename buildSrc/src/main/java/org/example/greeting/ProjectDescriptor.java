package org.example.greeting;

import java.util.List;

public class ProjectDescriptor {
  private String projectName;
  private String groupId;
  private String version;
  private String description;
  private String packageName;
  private String language;
  private String javaVersion;
  private String dependencies;
  private String bootVersion;

  public ProjectDescriptor withName(String projectName) {
    this.projectName = projectName;
    return this;
  }

  public ProjectDescriptor withGroupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

  public ProjectDescriptor withVersion(String version) {
    this.version = version;
    return this;
  }

  public ProjectDescriptor withDescription(String description) {
    this.description = description;
    return this;
  }

  public ProjectDescriptor withPackageName(String packageName) {
    this.packageName = packageName;
    return this;
  }

  public ProjectDescriptor withLanguage(String language) {
    this.language = language;
    return this;
  }

  public ProjectDescriptor withJavaVersion(String javaVersion) {
    this.javaVersion = javaVersion;
    return this;
  }

  public ProjectDescriptor withDependencies(String dependencies) {
    this.dependencies = dependencies;
    return this;
  }

  @Override
  public String toString() {
    return "ProjectDescriptor{" +
        "projectName='" + projectName + '\'' +
        ", groupId='" + groupId + '\'' +
        ", version='" + version + '\'' +
        ", description='" + description + '\'' +
        ", packageName='" + packageName + '\'' +
        ", language='" + language + '\'' +
        ", javaVersion='" + javaVersion + '\'' +
        ", bootVersion='" + bootVersion + '\'' +
        ", dependencies='" + dependencies + '\'' +
        '}';
  }

  /*
  applicationName | application name                         | DemoApplication              |
| artifactId      | project coordinates (infer archive name) | demo                         |
| baseDir         | base directory to create in the archive  | no base dir                  |
| bootVersion     | spring boot version                      | 2.0.5.RELEASE                |
| dependencies    | dependency identifiers (comma-separated) | none                         |
| description     | project description                      | Demo project for Spring Boot |
| groupId         | project coordinates                      | com.example                  |
| javaVersion     | language level                           | 1.8                          |
| language        | programming language                     | java                         |
| name            | project name (infer application name)    | demo                         |
| packageName     | root package                             | com.example.demo             |
| packaging       | project packaging                        | jar                          |
| type            | project type                             | maven-project                |
| version         | project version                          | 0.0.1-SNAPSHOT
   */
  public String postBody() {
    return "artifactId=" + this.projectName + "&" +
        "bootVersion=" +this.bootVersion + "&" +
        "dependencies=" + this.dependencies + "&" +
        //"description=" +this.description + "&" +
        "groupId=" + this.groupId + "&" +
        "javaVersion=" + this.javaVersion + "&" +
        "language=" + this.language + "&" +
        "name=" + this.projectName + "&" +
        "packageName=" + this.packageName + "&" +
        "type=gradle-project&" +
        "version=" + this.version;
  }

  public ProjectDescriptor withBootVersion(String bootVersion) {
    this.bootVersion = bootVersion;
    return this;
  }
}
