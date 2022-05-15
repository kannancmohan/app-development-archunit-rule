package com.kcm.msp.dev.app.development.archunit.rule.rules;

import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.CONTROLLER_PACKAGE;
import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.REPOSITORY_PACKAGE;
import static com.kcm.msp.dev.app.development.archunit.rule.util.CommonRule.SERVICE_AND_SUB_PACKAGE;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.lang.ArchRule;

public class LayeredArchitectureRule {
  private static final String CONTROLLER_LAYER = "Controller";
  private static final String REPOSITORY_LAYER = "Repository";
  private static final String SERVICE_LAYER = "Service";

  public static final ArchRule LAYER_DEPENDENCIES_ARE_RESPECTED =
      layeredArchitecture()
          .withOptionalLayers(true)
          .layer(CONTROLLER_LAYER)
          .definedBy(CONTROLLER_PACKAGE)
          .layer(SERVICE_LAYER)
          .definedBy(SERVICE_AND_SUB_PACKAGE)
          .layer(REPOSITORY_LAYER)
          .definedBy(REPOSITORY_PACKAGE)
          .whereLayer(CONTROLLER_LAYER)
          .mayNotBeAccessedByAnyLayer()
          .whereLayer(SERVICE_LAYER)
          .mayOnlyBeAccessedByLayers(CONTROLLER_LAYER)
          .whereLayer(REPOSITORY_LAYER)
          .mayOnlyBeAccessedByLayers(SERVICE_LAYER);
}
