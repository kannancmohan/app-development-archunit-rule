package com.kcm.msp.dev.app.development.archunit.rule;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;
import java.net.URI;

// Added as a sample to demonstrate the creation of custom ImportOption
public final class CustomImportOption {

  private CustomImportOption() {
    throw new UnsupportedOperationException();
  }

  public static final DoNotIncludeClassesInFolder DO_NOT_INCLUDE_CLASSES_IN_FOLDER =
      new DoNotIncludeClassesInFolder();

  public static final class DoNotIncludeClassesInFolder implements ImportOption {

    public boolean includes(final Location location) {
      // The pattern of folder which contains java class you want to exclude. eg /com/test/
      final String JAVA_FOLDER_TO_EXCLUDE = "<folder-where-java-class-is>";
      return location != null && !location.contains(toUri(JAVA_FOLDER_TO_EXCLUDE).toString());
    }
  }

  private static final String CLASS = ".class";

  private static URI toUri(final String excludePath) {
    boolean excludePathEndsWithDotClass = excludePath.endsWith(CLASS);
    String stringToConvert = excludePath;
    if (excludePathEndsWithDotClass) {
      // remove .class for easier replacements
      stringToConvert = excludePath.substring(0, excludePath.length() - CLASS.length());
    }
    // assuming a package based exclude path
    if (stringToConvert.contains(".")) {
      stringToConvert = stringToConvert.replace("\\.", "/");
    }
    // assuming a directory based exclude path
    else {
      // replace backslash by slash
      stringToConvert = stringToConvert.replace("\\\\", "/");
    }
    if (excludePathEndsWithDotClass) {
      // appends again .class that we removed initially
      stringToConvert = stringToConvert.concat(CLASS);
    }
    return URI.create(stringToConvert);
  }
}
