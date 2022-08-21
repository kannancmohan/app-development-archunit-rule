package com.kcm.msp.dev.app.development.archunit.rule.util;

import java.util.regex.Pattern;

public final class ProjectConstants {

  private ProjectConstants() {
    throw new UnsupportedOperationException();
  }

  public static final String BASE_PACKAGE = "com.kcm.msp.dev";
  public static final String MVN_GROUP_ID = BASE_PACKAGE;
  public static final Pattern MVN_GROUP_ID_PATTERN =
      Pattern.compile("^" + MVN_GROUP_ID + "(\\.[a-z][a-z\\d_]*)*$");
  public static final Pattern MVN_ARTIFACT_ID_PATTERN = Pattern.compile("^[a-z][a-z\\d_-]*$");
}
