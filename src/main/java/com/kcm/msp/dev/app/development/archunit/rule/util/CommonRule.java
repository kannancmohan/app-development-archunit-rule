package com.kcm.msp.dev.app.development.archunit.rule.util;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchRule;
import java.util.Arrays;
import java.util.List;

public final class CommonRule {

  public static final String MVN_GROUP_ID_PREFIX = "com.kcm.msp";
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

  public static ArchRule appShouldAccessOnlyClassInGivenPackages(final String appPackage) {
    return classes()
        .that()
        .areNotRecords()
        .and()
        .areNotEnums()
        .and()
        .resideInAPackage(appPackage + "..")
        .should()
        .onlyAccessClassesThat()
        .resideInAnyPackage(
            appPackage + "..",
            ".." + appPackage + "..enums",
            "java..",
            "javax..",
            "org.slf4j..",
            "lombok..",
            "org.apache.commons..",
            "org.apache.kafka..",
            "org.apache.http..",
            "org.apache.logging.log4j..",
            "org.springframework..",
            "com.fasterxml.jackson..",
            "com.amazonaws..",
            "software.amazon.awssdk..",
            "okhttp3..",
            "reactor.core.publisher..",
            "io.swagger.v3..");
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

  public static ArchRule classWithSuffixShouldResideInPackage(
      final String packageName, final String... classNameSuffix) {
    final List<String> classNameSuffixList = Arrays.stream(classNameSuffix).toList();
    final String classNameSuffixString = String.join("/", classNameSuffixList);
    final DescribedPredicate<JavaClass> nameEndsWithPredicate =
        nameEndsWithPredicate(classNameSuffixList, classNameSuffixString);
    return classes()
        .that(nameEndsWithPredicate)
        .should()
        .resideInAnyPackage(packageName) // eg: packageName:"..services.impl.."
        .because(
            "class name ending with ["
                + classNameSuffixString
                + "] should reside in package services package")
        .allowEmptyShould(true);
  }

  private static DescribedPredicate<JavaClass> nameEndsWithPredicate(
      final List<String> classNameSuffixList, final String description) {
    return new DescribedPredicate<>("has name ending with " + description) {
      @Override
      public boolean apply(final JavaClass input) {
        final String className = input.getName();
        return classNameSuffixList.stream().anyMatch(className::endsWith);
      }
    };
  }
}
