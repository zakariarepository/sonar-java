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
package org.sonar.java.resolve.targets;

public class ClassWithConstants {

  final String nonStatic = "xxx";
  static String nonFinal = "xxx";
  static final String CONST1 = "CONST_VALUE";
  static final boolean BOOLEAN_TRUE = true;
  static final boolean BOOLEAN_FALSE = false;

  static final int INT = 42;
  static final short SHORT = 42;
  static final char CHAR = 42;
  static final byte BYTE = 42;

  static final float FLOAT = 42;
  static final long LONG = 42;
  static final double DOUBLE = 42;

}
