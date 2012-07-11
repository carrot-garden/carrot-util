/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.provider;

import com.carrotgarden.util.anno.NotMutable;
import com.carrotgarden.util.values.api.IntegerValue;

//16 bytes on 32 bit JVM
@NotMutable
final class DefInteger extends BaseInteger {

	private final long value;

	DefInteger(final long value) {
		this.value = value;
	}

	@Override
	public final long asLong() {
		return value;
	}

	@Override
	protected final IntegerValue returnSize(final long value) {
		return ValueBuilder.newInteger(value);
	}

}
