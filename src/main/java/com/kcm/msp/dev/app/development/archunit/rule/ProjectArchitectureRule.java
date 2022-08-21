package com.kcm.msp.dev.app.development.archunit.rule;

import static com.kcm.msp.dev.app.development.archunit.rule.regulations.common.CommonRules.appShouldAccessOnlyClassInGivenPackages;
import static com.kcm.msp.dev.app.development.archunit.rule.util.ProjectConstants.BASE_PACKAGE;

import com.kcm.msp.dev.app.development.archunit.rule.regulations.ControllerClassRule;
import com.kcm.msp.dev.app.development.archunit.rule.regulations.LayeredArchitectureRule;
import com.kcm.msp.dev.app.development.archunit.rule.regulations.ServiceClassRule;
import com.kcm.msp.dev.app.development.archunit.rule.regulations.UtilClassRule;
import com.kcm.msp.dev.app.development.archunit.rule.regulations.maven.MavenPomRule;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(importOptions = {DoNotIncludeTests.class, DoNotIncludeJars.class})
public final class ProjectArchitectureRule {

  @ArchTest
  public static void appRule(final JavaClasses classes) {
    appShouldAccessOnlyClassInGivenPackages(BASE_PACKAGE).check(classes);
  }

  @ArchTest
  public static void mavenPomRule(final JavaClasses classes) {
    final MavenPomRule mavenPomRule = new MavenPomRule();
    mavenPomRule.allClassesShouldResideInProjectPackage().check(classes);
  }

  @ArchTest
  public static void controllerRule(final JavaClasses classes) {
    ControllerClassRule.CLASS_SHOULD_BE_SUFFIXED_AND_ANNOTATED.check(classes);
    ControllerClassRule.CLASS_SHOULD_RESIDE_IN_PACKAGE.check(classes);
  }

  @ArchTest
  public static void serviceRule(final JavaClasses classes) {
    ServiceClassRule.CLASS_IMPL_SHOULD_BE_SUFFIXED_AND_ANNOTATED.check(classes);
    ServiceClassRule.CLASS_SHOULD_BE_SUFFIXED.check(classes);
    ServiceClassRule.CLASS_SHOULD_RESIDE_IN_PACKAGE.check(classes);
  }

  @ArchTest
  public static void utilRule(final JavaClasses classes) {
    UtilClassRule.CLASS_SHOULD_RESIDE_IN_PACKAGE.check(classes);
    UtilClassRule.CLASS_SHOULD_BE_SUFFIXED_AND_PRIVATE.check(classes);
    UtilClassRule.METHODS_SHOULD_BE_STATIC.check(classes);
  }

  @ArchTest
  static final ArchRule LAYER_DEPENDENCIES_ARE_RESPECTED =
      LayeredArchitectureRule.LAYER_DEPENDENCIES_ARE_RESPECTED;
}
