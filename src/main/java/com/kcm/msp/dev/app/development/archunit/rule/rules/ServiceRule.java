package com.kcm.msp.dev.app.development.archunit.rule.rules;

import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.SERVICE_IMPL_NAME_ENDS;
import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.SERVICE_IMPL_PACKAGE;
import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.SERVICE_NAME_ENDS;
import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.SERVICE_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;

public class ServiceRule {
  public static final ArchRule SERVICE_SHOULD_BE_SUFFIXED =
      classes()
          .that()
          .resideInAPackage(SERVICE_PACKAGE)
          .should()
          .haveSimpleNameEndingWith(SERVICE_NAME_ENDS);

  public static final ArchRule SERVICE_IMPL_SHOULD_BE_SUFFIXED =
      classes()
          .that()
          .resideInAPackage(SERVICE_IMPL_PACKAGE)
          .should()
          .haveSimpleNameEndingWith(SERVICE_IMPL_NAME_ENDS)
          .andShould()
          .beAnnotatedWith(Service.class);
}
