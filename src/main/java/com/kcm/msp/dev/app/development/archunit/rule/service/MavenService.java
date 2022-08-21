package com.kcm.msp.dev.app.development.archunit.rule.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

@Slf4j
public class MavenService {

  private static final String DEFAULT_POM_FILE = "pom.xml";

  public Model getMavenPom(final String pomFileDirectory, final String pomFile) {
    final String fileName = StringUtils.isNotBlank(pomFile) ? pomFile : DEFAULT_POM_FILE;
    final String filePath = pomFileDirectory + "/" + fileName;
    try {
      final File file = new File(filePath);
      if (!file.exists() || !file.isFile()) {
        final String errorMessage = String.format("Maven pom file not found in path: %s", filePath);
        log.error(errorMessage);
        // TODO throw custom error
        throw new RuntimeException(errorMessage);
      }
      final MavenXpp3Reader reader = new MavenXpp3Reader();
      final Model model = reader.read(new FileReader(filePath, StandardCharsets.UTF_8));
      log.debug("Successfully loaded pom.xml");
      return model;
    } catch (final XmlPullParserException | IOException e) {
      // TODO throw custom error
      log.error("Failed to loading pom file from:{}", filePath, e);
      throw new RuntimeException("could not load the pom file", e);
    }
  }

  public static Optional<String> getGroupId(final Model pomModel) {
    if (pomModel == null) {
      return Optional.empty();
    }
    final String groupId =
        pomModel.getGroupId() != null ? pomModel.getGroupId() : pomModel.getParent().getGroupId();
    return Optional.ofNullable(groupId);
  }

  public static Optional<String> determineArtifactId(final Model pomModel) {
    if (pomModel == null) {
      return Optional.empty();
    }
    return determineArtifactId(pomModel.getArtifactId());
  }

  public static Optional<String> determineArtifactId(final String artifactId) {
    if (artifactId != null) {
      return Optional.of(artifactId.replace("-", "."));
    }
    return Optional.empty();
  }
}
