package com.kcm.msp.dev.app.development.archunit.rule.regulations;

import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.UTIL_NAME_ENDS;
import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.UTIL_PACKAGE;
import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.classWithSuffixShouldResideInPackage;
import static com.tngtech.archunit.core.domain.JavaModifier.FINAL;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.tngtech.archunit.lang.ArchRule;

public class UtilClassRule {

  public static final ArchRule CLASS_SHOULD_RESIDE_IN_PACKAGE =
      classWithSuffixShouldResideInPackage(UTIL_PACKAGE, UTIL_NAME_ENDS);

  public static final ArchRule CLASS_SHOULD_BE_SUFFIXED_AND_PRIVATE =
      classes()
          .that()
          .resideInAPackage(UTIL_PACKAGE)
          .should()
          .haveSimpleNameEndingWith(UTIL_NAME_ENDS)
          .andShould()
          .haveOnlyPrivateConstructors()
          .andShould()
          .haveModifier(FINAL)
          .because("Util class has to be final and constructor should be declared private");
  public static final ArchRule METHODS_SHOULD_BE_STATIC =
      methods()
          .that()
          .areDeclaredInClassesThat()
          .haveSimpleNameEndingWith(UTIL_NAME_ENDS)
          .should()
          .beStatic();
}
