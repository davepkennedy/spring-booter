package org.example.greeting;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.greeting.spring.DefaultableList;
import org.example.greeting.spring.DependencyGroup;
import org.example.greeting.spring.DependencyList;
import org.example.greeting.spring.SpringModel;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SpringStarterTask extends DefaultTask {

  private final ObjectMapper mapper;

  public SpringStarterTask()
  {
    mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @TaskAction
  public void performTask() throws IOException {

    try (CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {

      fetchSpringModel(closeableHttpClient)
          .flatMap(sm -> Optional.of(processSpringModel(sm)))
          .ifPresent(descriptor -> fetchArchive (closeableHttpClient, descriptor));
    }
  }

  private void fetchArchive(HttpClient client, ProjectDescriptor descriptor) {
    HttpGet post = new HttpGet("https://start.spring.io/starter.zip?" + descriptor.postBody());
    try {
      client.execute(post, response -> {
        ZipInputStream zis = new ZipInputStream(response.getEntity().getContent());
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
          System.out.println(zipEntry.getName());
          zipEntry = zis.getNextEntry();
        }
        return null;
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Optional<SpringModel> fetchSpringModel (HttpClient client) throws IOException {
    HttpGet get = new HttpGet("https://start.spring.io");
    return client.execute(get, response -> {
      if (response.getStatusLine().getStatusCode() == 200) {
        return Optional.of(EntityUtils.toString(response.getEntity()));
      }
      return Optional.empty();
    }).flatMap(s -> {
      try {
        return Optional.of(mapper.readValue(((Object) s).toString(), SpringModel.class));
      } catch (IOException e) {
        return Optional.empty();
      }
    });
  }

  private ProjectDescriptor processSpringModel(SpringModel model) {
    return new ProjectDescriptor()
        .withName(obtainProjectName(model))
        .withGroupId(obtainGroupId(model))
        .withVersion(obtainVersion(model))
        .withDescription(obtainDescription(model))
        .withPackageName(obtainPackageName(model))
        .withLanguage(obtainLanguage(model))
        .withJavaVersion(obtainJavaVersion(model))
        .withBootVersion(obtainBootVersion(model))
        .withDependencies(obtainDependencies(model));
  }

  private String obtainBootVersion(SpringModel model) {

    DefaultableList bootVersion = model.getBootVersion();
    System.out.println("Language: [" + bootVersion.getDefaultValue() + "]");
    bootVersion.getValues().forEach(l -> {
      System.out.println(l.getName() + " " + l.getId());
    });
    return readOptionalLine().orElse(bootVersion.getDefaultValue());
  }

  private String obtainDependencies(SpringModel model) {
    DependencyList dependencies = model.getDependencies();
    Map<String,String> selection = new HashMap<>();
    Optional<String> line;
    do {
      displayDependencyGroups (dependencies);
      System.out.println("Group to explore, blank to continue");
      line = readOptionalLine();
      if (line.isPresent()) {
        final String group = line.get();
        exploreGroup(dependencies, group).ifPresent(s -> selection.put(group, s));

      }
    } while (line.isPresent());
    return String.join (",", selection.values());
  }

  private Optional<String> exploreGroup(DependencyList dependencies, String s) {
    for (DependencyGroup group : dependencies.getValues()) {
      if (group.getName().equalsIgnoreCase(s)) {
        group.getValues().forEach(g -> {
          System.out.println (g.getId() + ": " + g.getDescription());
        });
        return Optional.of(obtainValue("Dependencies from group, comma separated", ""));
      }
    }
    return Optional.empty();
  }

  private void displayDependencyGroups(DependencyList dependencies) {
    dependencies.getValues().forEach(group -> System.out.println(group.getName()));
  }

  private String obtainJavaVersion(SpringModel model) {
    DefaultableList version = model.getJavaVersion();
    System.out.println("Java Version: [" + version.getDefaultValue() + "]");
    version.getValues().forEach(l -> {
      System.out.println(l.getName() + " " + l.getId());
    });
    return readOptionalLine().orElse(version.getDefaultValue());
  }

  private String obtainLanguage(SpringModel model) {
    DefaultableList language = model.getLanguage();
    System.out.println("Language: [" + language.getDefaultValue() + "]");
    language.getValues().forEach(l -> {
      System.out.println(l.getName() + " " + l.getId());
    });
    return readOptionalLine().orElse(language.getDefaultValue());
  }

  private String obtainPackageName(SpringModel model) {
    return obtainValue("Package Name", model.getPackageName().getDefaultValue());
  }

  private String obtainDescription(SpringModel model) {
    return obtainValue("Description", model.getDescription().getDefaultValue());
  }

  private String obtainVersion(SpringModel model) {
    return obtainValue("Version", model.getVersion().getDefaultValue());
  }

  private String obtainGroupId(SpringModel model) {
    return obtainValue("Group Id", model.getGroupId().getDefaultValue());
  }

  private String obtainProjectName(SpringModel model) {
    return obtainValue("Project Name", model.getName().getDefaultValue());
  }

  private String obtainValue (String valueName, String defaultValue) {
    System.out.println(valueName + " :[" + defaultValue + "]");
    return readOptionalLine().orElse(defaultValue);
  }

  private Optional<String> readOptionalLine() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      String s = reader.readLine();
      if (s.isEmpty()) {
        return Optional.empty();
      }
      return Optional.of(s);
    } catch (IOException e) {
      return Optional.empty();
    }
  }
}
