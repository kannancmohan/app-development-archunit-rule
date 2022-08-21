package com.kcm.msp.dev.app.development.archunit.rule.regulations.maven;

import static com.kcm.msp.dev.app.development.archunit.rule.util.ProjectArchitectureRuleUtils.getBasedir;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.kcm.msp.dev.app.development.archunit.rule.service.MavenService;
import com.tngtech.archunit.lang.ArchRule;
import java.util.Objects;
import org.apache.maven.model.Model;

public class MavenPomRule {

  private final Model pomModel;

  public MavenPomRule() {
    this(new MavenService());
  }

  public MavenPomRule(final MavenService service) {
    this.pomModel = Objects.requireNonNull(service.getMavenPom(getBasedir(), null));
  }

  public ArchRule allClassesShouldResideInProjectPackage() {
    final String groupId = MavenService.getGroupId(pomModel).orElse("");
    final String artifactIdBasedPackage = MavenService.determineArtifactId(pomModel).orElse("");
    return classes().should().resideInAPackage(groupId + "." + artifactIdBasedPackage + "..");
  }
}
