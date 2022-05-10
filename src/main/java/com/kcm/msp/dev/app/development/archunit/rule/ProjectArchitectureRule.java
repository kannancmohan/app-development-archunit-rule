package com.kcm.msp.dev.app.development.archunit.rule;

import com.kcm.msp.dev.app.development.archunit.rule.rules.ClassNamingRule;
import com.kcm.msp.dev.app.development.archunit.rule.rules.LayeredArchitectureRule;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(importOptions = {DoNotIncludeTests.class, DoNotIncludeJars.class})
public class ProjectArchitectureRule {

  @ArchTest
  static final ArchRule CONTROLLER_SHOULD_BE_SUFFIXED =
      ClassNamingRule.CONTROLLER_SHOULD_BE_SUFFIXED;

  @ArchTest
  static final ArchRule SERVICE_SHOULD_BE_SUFFIXED = ClassNamingRule.SERVICE_SHOULD_BE_SUFFIXED;

  @ArchTest
  static final ArchRule LAYER_DEPENDENCIES_ARE_RESPECTED =
      LayeredArchitectureRule.LAYER_DEPENDENCIES_ARE_RESPECTED;
}
