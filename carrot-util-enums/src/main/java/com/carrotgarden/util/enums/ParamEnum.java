/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.enums;

public interface ParamEnum<V> extends EnumJDK, Sequence {

	@Override
	int sequence();

	V value();

	//

	boolean is(ParamEnum<?> that);

	boolean isIn(ParamEnum<?>... thatArray);

}
