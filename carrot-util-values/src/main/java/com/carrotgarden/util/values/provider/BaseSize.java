/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.provider;

import static com.carrotgarden.util.values.provider.ValueConst.*;

import com.carrotgarden.util.anno.NotMutable;
import com.carrotgarden.util.values.api.DecimalValue;
import com.carrotgarden.util.values.api.SizeValue;
import com.carrotgarden.util.values.lang.ScaledDecimalValue;

@NotMutable
abstract class BaseSize extends ScaledDecimalValue<SizeValue, DecimalValue>
		implements SizeValue {

	@Override
	protected SizeValue result(final long mantissa, final int exponent) {
		return ValueBuilder.newSize(mantissa, exponent);
	}

	@Override
	public final boolean isNull() {
		return this == NULL_SIZE;
	}

	@Override
	public final boolean equals(final Object thatValue) {
		if (thatValue instanceof SizeValue) {
			final SizeValue that = (SizeValue) thatValue;
			return this.compareTo(that) == 0;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("Price > %9d %3d", //
				mantissa(), exponent());
	}

}
