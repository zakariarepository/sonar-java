/*
 * SonarQube Java
 * Copyright (C) 2012-2024 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.java.checks;

import org.sonar.check.Rule;
import org.sonar.java.checks.methods.AbstractMethodDetection;
import org.sonar.java.model.LiteralUtils;
import org.sonar.plugins.java.api.semantic.MethodMatchers;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.NewClassTree;

@Rule(key = "S2122")
public class ScheduledThreadPoolExecutorZeroCheck extends AbstractMethodDetection {

  private static final String MESSAGE = "Increase the \"corePoolSize\".";

  @Override
  protected MethodMatchers getMethodInvocationMatchers() {
    return MethodMatchers.or(
      MethodMatchers.create().ofSubTypes("java.util.concurrent.ThreadPoolExecutor").names("setCorePoolSize").addParametersMatcher("int").build(),
      MethodMatchers.create().ofTypes("java.util.concurrent.ScheduledThreadPoolExecutor").constructor().addParametersMatcher("int").build()
    );
  }

  @Override
  protected void onMethodInvocationFound(MethodInvocationTree mit) {
    ExpressionTree arg = mit.arguments().get(0);
    if (LiteralUtils.isZero(arg)) {
      reportIssue(arg, MESSAGE);
    }
  }

  @Override
  protected void onConstructorFound(NewClassTree newClassTree) {
    ExpressionTree arg = newClassTree.arguments().get(0);
    if (LiteralUtils.isZero(arg)) {
      reportIssue(arg, MESSAGE);
    }
  }

}
