/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.provider;

import com.carrotgarden.util.anno.NotMutable;
import com.carrotgarden.util.values.api.SizeValue;

// 8 bytes on 32 bit JVM
@NotMutable
final class NulSize extends BaseSize {

	@Override
	public long mantissa() {
		return 0;
	}

	@Override
	public int exponent() {
		return 0;
	}

	@Override
	protected SizeValue result(final long mantissa, final int exponent) {
		return ValueBuilder.newSize(mantissa, exponent);
	}

}
