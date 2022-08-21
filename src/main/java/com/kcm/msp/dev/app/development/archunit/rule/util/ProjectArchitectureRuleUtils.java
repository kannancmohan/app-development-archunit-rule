package com.kcm.msp.dev.app.development.archunit.rule.util;

import org.apache.commons.lang3.StringUtils;

public final class ProjectArchitectureRuleUtils {

  private ProjectArchitectureRuleUtils() {
    throw new UnsupportedOperationException();
  }

  public static String getBasedir() {
    String basedirString = System.getProperty("basedir");
    if (StringUtils.isBlank(basedirString)) {
      basedirString = System.getProperty("user.dir");
    }
    return basedirString;
  }
}
