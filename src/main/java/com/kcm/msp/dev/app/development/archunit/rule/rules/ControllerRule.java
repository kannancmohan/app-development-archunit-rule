package com.kcm.msp.dev.app.development.archunit.rule.rules;

import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.CONTROLLER_NAME_ENDS;
import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.CONTROLLER_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.lang.ArchRule;
import org.springframework.web.bind.annotation.RestController;

public class ControllerRule {

  public static final ArchRule NAME_SHOULD_BE_SUFFIXED =
      classes()
          .that()
          .resideInAPackage(CONTROLLER_PACKAGE)
          .should()
          .beAnnotatedWith(RestController.class)
          .andShould()
          .haveSimpleNameEndingWith(CONTROLLER_NAME_ENDS);
}
