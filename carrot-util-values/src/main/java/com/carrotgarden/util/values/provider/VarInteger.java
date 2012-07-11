/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.provider;

import com.carrotgarden.util.anno.Mutable;
import com.carrotgarden.util.anno.NotThreadSafe;
import com.carrotgarden.util.values.api.IntegerValue;

//16 bytes on 32 bit JVM
@Mutable
@NotThreadSafe
final class VarInteger extends BaseInteger {

	private volatile long value;

	VarInteger(final long value) {
		this.value = value;
	}

	@Override
	public final long asLong() {
		return value;
	}

	@Override
	protected final IntegerValue returnSize(final long value) {
		this.value = value;
		return this;
	}

	@Override
	public final IntegerValue freeze() {
		return ValueBuilder.newInteger(value);
	}

	@Override
	public final boolean isFrozen() {
		return false;
	}

}
