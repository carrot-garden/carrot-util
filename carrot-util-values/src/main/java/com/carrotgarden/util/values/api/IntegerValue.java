/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.api;

import com.carrotgarden.util.anno.NotMutable;

/** whole integer up to 64 bit */
@NotMutable
public interface IntegerValue extends Value<IntegerValue>,
		Comparable<IntegerValue> {

	//

	// legacy compatibility
	@Deprecated
	int asInt();

	long asLong();

	//

	@Override
	int compareTo(IntegerValue thatSize);

	@Override
	boolean equals(Object thatSize);

	//

	IntegerValue add(IntegerValue thatSize) throws ArithmeticException;

	IntegerValue sub(IntegerValue thatSize) throws ArithmeticException;

	IntegerValue mult(long factor) throws ArithmeticException;

	IntegerValue div(long factor) throws ArithmeticException;

	long count(IntegerValue thatSize) throws ArithmeticException;

	boolean isZero();

}
