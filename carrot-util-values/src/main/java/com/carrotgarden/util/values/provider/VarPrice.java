/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.provider;

import static com.carrotgarden.util.values.provider.ValueBuilder.*;

import com.carrotgarden.util.anno.Mutable;
import com.carrotgarden.util.anno.NotThreadSafe;
import com.carrotgarden.util.values.api.PriceValue;

// 24 bytes on 32 bit JVM
@Mutable
@NotThreadSafe
final class VarPrice extends BasePrice {

	private volatile long mantissa;
	private volatile int exponent;

	VarPrice(final long mantissa, final int exponent) {
		this.mantissa = mantissa;
		this.exponent = exponent;
	}

	@Override
	public final long mantissa() {
		return mantissa;
	}

	@Override
	public final int exponent() {
		return exponent;
	}

	@Override
	protected final PriceValue result(final long mantissa,
			final int exponent) {
		this.mantissa = mantissa;
		this.exponent = exponent;
		return this;
	}

	@Override
	public final PriceValue freeze() {
		return newPrice(mantissa, exponent);
	}

	@Override
	public final boolean isFrozen() {
		return false;
	}

}
