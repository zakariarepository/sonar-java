/*
 * Copyright (C) 2012-2024 SonarSource SA - mailto:info AT sonarsource DOT com
 * This code is released under [MIT No Attribution](https://opensource.org/licenses/MIT-0) license.
 */
package org.sonar.samples.java;

import org.junit.jupiter.api.Test;
import org.sonar.api.rule.RuleKey;
import org.sonar.java.checks.verifier.TestCheckRegistrarContext;
import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonar.samples.java.checks.NoIfStatementInTestsRule;

import static org.assertj.core.api.Assertions.assertThat;

class MyJavaFileCheckRegistrarTest {

  @Test
  void checkRegisteredRulesKeysAndClasses() {
    TestCheckRegistrarContext context = new TestCheckRegistrarContext();

    MyJavaFileCheckRegistrar registrar = new MyJavaFileCheckRegistrar();
    registrar.register(context);

    assertThat(context.mainRuleKeys).extracting(RuleKey::toString).containsExactly(
      "fellowship-inc:SpringControllerRequestMappingEntity",
      "fellowship-inc:AvoidAnnotation",
      "fellowship-inc:AvoidBrandInMethodNames",
      "fellowship-inc:AvoidMethodDeclaration",
      "fellowship-inc:AvoidSuperClass",
      "fellowship-inc:AvoidTreeList",
      "fellowship-inc:AvoidMethodWithSameTypeInArgument",
      "fellowship-inc:SecurityAnnotationMandatory",
      "fellowship-inc:MyFirstCustomRule");

    assertThat(context.mainCheckClasses).extracting(Class::getSimpleName).containsExactly(
      "SpringControllerRequestMappingEntityRule",
      "AvoidAnnotationRule",
      "AvoidBrandInMethodNamesRule",
      "AvoidMethodDeclarationRule",
      "AvoidSuperClassRule",
      "AvoidTreeListRule",
      "MyCustomSubscriptionRule",
      "SecurityAnnotationMandatoryRule",
      "MyFirstCustomCheck");

    assertThat(context.testRuleKeys).extracting(RuleKey::toString).containsExactly(
      "fellowship-inc:NoIfStatementInTests");

    assertThat(context.testCheckClasses).extracting(Class::getSimpleName).containsExactly(
      "NoIfStatementInTestsRule");
  }


}
