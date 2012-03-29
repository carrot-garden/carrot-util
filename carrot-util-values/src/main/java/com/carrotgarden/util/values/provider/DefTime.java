/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.provider;

import com.carrotgarden.util.anno.NotMutable;

//16 bytes on 32 bit JVM
@NotMutable
final class DefTime extends BaseTime {

	private final long millisUTC;

	DefTime(final long millisUTC) {
		this.millisUTC = millisUTC;
	}

	@Override
	public final long asMillisUTC() {
		return millisUTC;
	}

}
