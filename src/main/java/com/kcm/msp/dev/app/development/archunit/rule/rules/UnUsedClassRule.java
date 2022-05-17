package com.kcm.msp.dev.app.development.archunit.rule.rules;

import static com.tngtech.archunit.base.DescribedPredicate.describe;
import static com.tngtech.archunit.base.DescribedPredicate.not;
import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.metaAnnotatedWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

public class UnUsedClassRule {

  private static DescribedPredicate<JavaClass> classHasMethodWithAnnotationThatEndsWith(
      String suffix) {
    return describe(
        String.format("has method with annotation that ends with '%s'", suffix),
        clazz ->
            clazz.getMethods().stream()
                .flatMap(method -> method.getAnnotations().stream())
                .anyMatch(annotation -> annotation.getRawType().getFullName().endsWith(suffix)));
  }

  private static final ArchCondition<JavaClass> shouldBeReferencedClass =
      new ArchCondition<>("not be unreferenced") {
        @Override
        public void check(JavaClass javaClass, ConditionEvents events) {
          final Set<JavaAccess<?>> accesses = new HashSet<>(javaClass.getAccessesToSelf());
          accesses.removeAll(javaClass.getAccessesFromSelf());
          if (accesses.isEmpty() && javaClass.getDirectDependenciesToSelf().isEmpty()) {
            events.add(
                new SimpleConditionEvent(
                    javaClass,
                    false,
                    String.format(
                        "%s is unreferenced in %s",
                        javaClass.getDescription(), javaClass.getSourceCodeLocation())));
          }
        }
      };

  public static ArchRule CLASS_SHOULD_NOT_BE_UNUSED =
      classes()
          .that()
          .areNotMetaAnnotatedWith(org.springframework.context.annotation.Configuration.class)
          .and()
          .areNotMetaAnnotatedWith(org.springframework.stereotype.Controller.class)
          .and()
          .areNotMetaAnnotatedWith(org.springframework.web.bind.annotation.RestController.class)
          .and()
          .areNotMetaAnnotatedWith(
              org.springframework.web.bind.annotation.RestControllerAdvice.class)
          .and(
              not(
                  classHasMethodWithAnnotationThatEndsWith("Handler")
                      .or(classHasMethodWithAnnotationThatEndsWith("Listener"))
                      .or(classHasMethodWithAnnotationThatEndsWith("Scheduled"))
                      .or(
                          describe(
                              "implements interface",
                              clazz -> !clazz.getAllRawInterfaces().isEmpty()))
                      .or(
                          describe(
                              "extends class", clazz -> 1 < clazz.getAllRawSuperclasses().size()))
                      .and(metaAnnotatedWith(Component.class))))
          .should(shouldBeReferencedClass)
          .as("should use all classes")
          .because("unused classes should be removed");
}
