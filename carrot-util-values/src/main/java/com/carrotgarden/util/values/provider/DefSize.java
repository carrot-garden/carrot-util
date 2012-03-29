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

//16 bytes on 32 bit JVM
@NotMutable
final class DefSize extends BaseSize {

	private final long value;

	DefSize(long value) {
		this.value = value;
	}

	@Override
	public final long asLong() {
		return value;
	}

	@Override
	protected final SizeValue returnSize(long value) {
		return ValueBuilder.newSize(value);
	}

}
