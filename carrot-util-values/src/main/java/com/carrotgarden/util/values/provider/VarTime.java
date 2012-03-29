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
import com.carrotgarden.util.values.api.TimeValue;

//16 bytes on 32 bit JVM
@Mutable
@NotThreadSafe
final class VarTime extends BaseTime {

	private volatile long millisUTC;

	VarTime(final long millisUTC) {
		this.millisUTC = millisUTC;
	}

	@Override
	public final long asMillisUTC() {
		return millisUTC;
	}

	@Override
	public final TimeValue freeze() {
		return newTime(millisUTC);
	}

	@Override
	public final boolean isFrozen() {
		return false;
	}

}
