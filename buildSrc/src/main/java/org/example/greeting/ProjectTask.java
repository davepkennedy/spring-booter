package org.example.greeting;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.TaskAction;

public class ProjectTask extends DefaultTask {
  private Project project;

  public void setProject (Project project) {
    this.project = project;
  }

  @TaskAction
  void performTask() {
    project.getConfigurations().stream().forEach(configuration ->{
      System.out.printf("-=[%s %n", configuration.getDescription());

      configuration.getAllDependencies().stream().forEach(dependency -> {
        System.out.printf("---=[ %s %s %s %s %n",
            dependency.getGroup(),
            dependency.getName(),
            dependency.getReason(),
            dependency.getVersion());
      });
    });
    project.getDependencies().add(JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME, "com.google.code.findbugs:jsr305");
  }
}
