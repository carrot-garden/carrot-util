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
import com.carrotgarden.util.values.api.SizeValue;

//16 bytes on 32 bit JVM
@Mutable
@NotThreadSafe
final class VarSize extends BaseSize {

	private volatile long value;

	VarSize(final long value) {
		this.value = value;
	}

	@Override
	public final long asLong() {
		return value;
	}

	@Override
	protected final SizeValue returnSize(final long value) {
		this.value = value;
		return this;
	}

	@Override
	public final SizeValue freeze() {
		return newSize(value);
	}

	@Override
	public final boolean isFrozen() {
		return false;
	}

}
