package com.kcm.msp.dev.app.development.archunit.rule.regulations;

import static com.kcm.msp.dev.app.development.archunit.rule.regulations.common.CommonRules.CONTROLLER_NAME_ENDS;
import static com.kcm.msp.dev.app.development.archunit.rule.regulations.common.CommonRules.CONTROLLER_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.lang.ArchRule;
import org.springframework.web.bind.annotation.RestController;

public class ControllerClassRule {

  public static final ArchRule CLASS_SHOULD_BE_SUFFIXED_AND_ANNOTATED =
      classes()
          .that()
          .resideInAPackage(CONTROLLER_PACKAGE)
          .should()
          .beAnnotatedWith(RestController.class)
          .andShould()
          .haveSimpleNameEndingWith(CONTROLLER_NAME_ENDS);
  public static final ArchRule CLASS_SHOULD_RESIDE_IN_PACKAGE =
      classes()
          .that()
          .haveSimpleNameEndingWith(CONTROLLER_NAME_ENDS)
          .or()
          .areAnnotatedWith(RestController.class)
          .should()
          .resideInAnyPackage(CONTROLLER_PACKAGE);
}
