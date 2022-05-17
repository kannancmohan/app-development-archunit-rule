package com.kcm.msp.dev.app.development.archunit.rule.rules;

import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.UTIL_NAME_ENDS;
import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.UTIL_PACKAGE;
import static com.tngtech.archunit.core.domain.JavaModifier.FINAL;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.tngtech.archunit.lang.ArchRule;

public class UtilClassRule {

  public static final ArchRule CLASS_SHOULD_RESIDE_IN_PACKAGE =
      classes()
          .that()
          .haveSimpleNameEndingWith(UTIL_NAME_ENDS)
          .should()
          .resideInAnyPackage(UTIL_PACKAGE);

  public static final ArchRule CLASS_SHOULD_BE_SUFFIXED_AND_PRIVATE =
      classes()
          .that()
          .resideInAPackage(UTIL_PACKAGE)
          .should()
          .haveSimpleNameEndingWith(UTIL_NAME_ENDS)
          .andShould()
          .haveOnlyPrivateConstructors()
          .andShould()
          .haveModifier(FINAL);
  public static final ArchRule METHODS_SHOULD_BE_STATIC =
      methods()
          .that()
          .areDeclaredInClassesThat()
          .haveSimpleNameEndingWith(UTIL_NAME_ENDS)
          .should()
          .beStatic();
}
