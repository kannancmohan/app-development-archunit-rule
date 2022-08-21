package com.kcm.msp.dev.app.development.archunit.rule.regulations;

import static com.kcm.msp.dev.app.development.archunit.rule.regulations.common.CommonRules.SERVICE_IMPL_NAME_ENDS;
import static com.kcm.msp.dev.app.development.archunit.rule.regulations.common.CommonRules.SERVICE_IMPL_PACKAGE;
import static com.kcm.msp.dev.app.development.archunit.rule.regulations.common.CommonRules.SERVICE_NAME_ENDS;
import static com.kcm.msp.dev.app.development.archunit.rule.regulations.common.CommonRules.SERVICE_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;

public class ServiceClassRule {
  public static final ArchRule CLASS_SHOULD_BE_SUFFIXED =
      classes()
          .that()
          .resideInAPackage(SERVICE_PACKAGE)
          .should()
          .beInterfaces()
          .andShould()
          .haveSimpleNameEndingWith(SERVICE_NAME_ENDS);

  public static final ArchRule CLASS_IMPL_SHOULD_BE_SUFFIXED_AND_ANNOTATED =
      classes()
          .that()
          .resideInAPackage(SERVICE_IMPL_PACKAGE)
          .should()
          .haveSimpleNameEndingWith(SERVICE_IMPL_NAME_ENDS)
          .andShould()
          .beAnnotatedWith(Service.class);

  public static final ArchRule CLASS_SHOULD_RESIDE_IN_PACKAGE =
      classes()
          .that()
          .haveSimpleNameEndingWith(SERVICE_IMPL_NAME_ENDS)
          .or()
          .areAnnotatedWith(Service.class)
          .should()
          .resideInAnyPackage(SERVICE_IMPL_PACKAGE);
}
