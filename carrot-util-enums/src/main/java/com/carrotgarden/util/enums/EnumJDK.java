/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.enums;

/**
 * <a href="http://docs.oracle.com/javase/6/docs/api/java/lang/Enum.html">enum
 * api representation</a>
 */
public interface EnumJDK extends Name, Ordinal, Comparable<EnumJDK> {

	@Override
	String name();

	@Override
	int ordinal();

	//

	@Override
	int hashCode();

	@Override
	boolean equals(Object other);

	@Override
	String toString();

}
