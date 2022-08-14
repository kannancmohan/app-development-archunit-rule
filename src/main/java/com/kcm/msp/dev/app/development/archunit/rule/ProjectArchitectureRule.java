package com.kcm.msp.dev.app.development.archunit.rule;

import static com.tngtech.archunit.base.DescribedPredicate.not;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.simpleName;
import static com.tngtech.archunit.lang.conditions.ArchPredicates.are;

import com.kcm.msp.dev.app.development.archunit.rule.regulations.ControllerClassRule;
import com.kcm.msp.dev.app.development.archunit.rule.regulations.LayeredArchitectureRule;
import com.kcm.msp.dev.app.development.archunit.rule.regulations.ServiceClassRule;
import com.kcm.msp.dev.app.development.archunit.rule.regulations.UtilClassRule;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.core.importer.ImportOption.Predefined;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(importOptions = {DoNotIncludeTests.class, DoNotIncludeJars.class})
public class ProjectArchitectureRule {

  private static final JavaClasses classes =
      new ClassFileImporter()
          .withImportOption(Predefined.DO_NOT_INCLUDE_TESTS)
          .withImportOption(Predefined.DO_NOT_INCLUDE_JARS)
          .importPath(".")
          .that(are(not(simpleName("ApiUtil"))));

  @ArchTest
  public static void controllerRule(JavaClasses classes) {
    ControllerClassRule.CLASS_SHOULD_BE_SUFFIXED_AND_ANNOTATED.check(classes);
    ControllerClassRule.CLASS_SHOULD_RESIDE_IN_PACKAGE.check(classes);
  }

  @ArchTest
  public static void serviceRule(JavaClasses classes) {
    ServiceClassRule.CLASS_IMPL_SHOULD_BE_SUFFIXED_AND_ANNOTATED.check(classes);
    ServiceClassRule.CLASS_SHOULD_BE_SUFFIXED.check(classes);
    ServiceClassRule.CLASS_SHOULD_RESIDE_IN_PACKAGE.check(classes);
  }

  @ArchTest
  public static void utilRule(JavaClasses classes) {
    UtilClassRule.CLASS_SHOULD_RESIDE_IN_PACKAGE.check(classes);
    UtilClassRule.CLASS_SHOULD_BE_SUFFIXED_AND_PRIVATE.check(classes);
    UtilClassRule.METHODS_SHOULD_BE_STATIC.check(classes);
  }

  @ArchTest
  static final ArchRule LAYER_DEPENDENCIES_ARE_RESPECTED =
      LayeredArchitectureRule.LAYER_DEPENDENCIES_ARE_RESPECTED;
}
