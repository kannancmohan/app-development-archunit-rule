package com.kcm.msp.dev.app.development.archunit.rule.service;

import static com.kcm.msp.dev.app.development.archunit.rule.util.ProjectArchitectureRuleUtils.getBasedir;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.maven.model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
final class MavenServiceTest {
  private MavenService classUnderTest;

  @BeforeEach
  public void setUp() {
    classUnderTest = new MavenService();
  }

  @Test
  void getMavenPomShouldGiveMavenModel() {
    final Model mavenPom = classUnderTest.getMavenPom(getTestResourceDirectory(), "test-pom.xml");
    assertNotNull(mavenPom);
    assertEquals("test-artifactId", mavenPom.getArtifactId());
  }

  private String getTestResourceDirectory() {
    final String basedir = getBasedir();
    final String classNameAsPath = getClass().getName().replace('.', '/');
    return basedir + "/src/test/resources/" + classNameAsPath;
  }
}
