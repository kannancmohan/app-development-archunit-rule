package com.kcm.msp.dev.app.development.archunit.rule.regulations.maven;

import static com.kcm.msp.dev.app.development.archunit.rule.service.MavenService.determineArtifactId;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.kcm.msp.dev.app.development.archunit.rule.service.MavenService;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption.Predefined;
import com.tngtech.archunit.lang.EvaluationResult;
import org.apache.maven.model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("UnitTest")
@ExtendWith(MockitoExtension.class)
final class MavenPomRuleTest {
  private final JavaClasses CLASSES =
      new ClassFileImporter()
          .withImportOption(Predefined.DO_NOT_INCLUDE_TESTS)
          .withImportOption(Predefined.DO_NOT_INCLUDE_JARS)
          .importPath(".");
  private MavenPomRule classUnderTest;
  @Mock private MavenService mavenService;
  @Mock private Model mavenModel;

  @BeforeEach
  void setUp() {
    when(mavenModel.getGroupId()).thenReturn("com.kcm.msp.dev");
    when(mavenModel.getArtifactId())
        .thenReturn(determineArtifactId("app-development-archunit-rule").orElse(""));
    when(mavenService.getMavenPom(anyString(), any())).thenReturn(mavenModel);
    classUnderTest = new MavenPomRule(mavenService);
  }

  @Test
  void allClassesShouldResideInPackage() {
    final EvaluationResult evaluationResult =
        classUnderTest.allClassesShouldResideInProjectPackage().evaluate(CLASSES);
    assertNotNull(evaluationResult);
    assertFalse(evaluationResult.hasViolation());
  }
}
