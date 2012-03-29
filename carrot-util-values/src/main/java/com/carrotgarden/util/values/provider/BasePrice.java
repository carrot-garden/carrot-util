/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.provider;

import static com.carrotgarden.util.values.provider.ValueBuilder.*;
import static com.carrotgarden.util.values.provider.ValueConst.*;

import com.carrotgarden.util.anno.NotMutable;
import com.carrotgarden.util.values.api.DecimalValue;
import com.carrotgarden.util.values.api.PriceValue;
import com.carrotgarden.util.values.lang.ScaledDecimalValue;

@NotMutable
abstract class BasePrice extends ScaledDecimalValue<PriceValue, DecimalValue>
		implements PriceValue {

	@Override
	protected PriceValue result(final long mantissa, final int exponent) {
		return newPrice(mantissa, exponent);
	}

	@Override
	public final boolean isNull() {
		return this == NULL_PRICE;
	}

	@Override
	public final boolean equals(final Object thatValue) {
		if (thatValue instanceof PriceValue) {
			PriceValue that = (PriceValue) thatValue;
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
