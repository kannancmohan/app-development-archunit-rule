package com.kcm.msp.dev.app.development.archunit.rule.util;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.tngtech.archunit.lang.ArchRule;
import java.util.Arrays;

public final class CommonRule {

  public static final String CONTROLLER_PACKAGE = "..controller..";
  public static final String UTIL_PACKAGE = "..util..";
  public static final String SERVICE_PACKAGE = "..service";
  public static final String SERVICE_AND_SUB_PACKAGE = "..service..";
  public static final String SERVICE_IMPL_PACKAGE = "..service.impl..";
  public static final String REPOSITORY_PACKAGE = "..repository..";
  public static final String CONTROLLER_NAME_ENDS = "Controller";
  public static final String UTIL_NAME_ENDS = "Util";
  public static final String SERVICE_NAME_ENDS = "Service";
  public static final String SERVICE_IMPL_NAME_ENDS = "ServiceImpl";

  private CommonRule() {
    throw new UnsupportedOperationException();
  }

  public static ArchRule methodsShouldBePublic(String... packageNames) {
    return methods()
        .that()
        .areDeclaredInClassesThat()
        .resideInAnyPackage(packageNames)
        .should()
        .bePublic()
        .because("Public methods are only allowed in " + Arrays.toString(packageNames));
  }
}
