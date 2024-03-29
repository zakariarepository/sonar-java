/*
 * SonarQube Java
 * Copyright (C) 2013-2024 SonarSource SA
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
package org.sonar.samples.java;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionAnnotationLoader;

public class JavaExtensionRulesDefinition implements RulesDefinition {

  public static final String REPOSITORY_KEY = "java-extension";

  @Override
  public void define(Context context) {
    NewRepository repo = context.createRepository(REPOSITORY_KEY, "java");
    repo.setName(REPOSITORY_KEY);

    // We could use a XML or JSON file to load all rule metadata, but
    // we prefer use annotations in order to have all information in a
    // single place
    RulesDefinitionAnnotationLoader annotationLoader = new RulesDefinitionAnnotationLoader();
    // Load custom check classes
    annotationLoader.load(repo, JavaExtensionsCheckRegistrar.checkClasses());
    // Load custom test check classes
    annotationLoader.load(repo, JavaExtensionsCheckRegistrar.testCheckClasses());
    repo.done();
  }
}
