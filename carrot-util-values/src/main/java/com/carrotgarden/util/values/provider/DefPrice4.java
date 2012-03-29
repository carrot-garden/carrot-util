/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.provider;

import com.carrotgarden.util.anno.NotMutable;

// 16 bytes on 32 bit JVM
@NotMutable
final class DefPrice4 extends BasePrice {

	private final long mantissa;

	DefPrice4(final long mantissa) {
		this.mantissa = mantissa;
	}

	@Override
	public final long mantissa() {
		return mantissa;
	}

	@Override
	public final int exponent() {
		return -4;
	}

}
