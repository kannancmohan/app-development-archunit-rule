package com.kcm.msp.dev.app.development.archunit.rule.rules;

import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.CONTROLLER_NAME_ENDS;
import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.CONTROLLER_PACKAGE;
import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.SERVICE_IMPL_NAME_ENDS;
import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.SERVICE_NAME_ENDS;
import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.SERVICE_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.lang.ArchRule;

public class ClassNamingRule {

  public static final ArchRule CONTROLLER_SHOULD_BE_SUFFIXED =
      classes()
          .that()
          .resideInAPackage(CONTROLLER_PACKAGE)
          .should()
          .haveSimpleNameEndingWith(CONTROLLER_NAME_ENDS);

  public static final ArchRule SERVICE_SHOULD_BE_SUFFIXED =
      classes()
          .that()
          .resideInAPackage(SERVICE_PACKAGE)
          .should()
          .haveSimpleNameEndingWith(SERVICE_NAME_ENDS)
          .orShould()
          .haveSimpleNameEndingWith(SERVICE_IMPL_NAME_ENDS);
}
