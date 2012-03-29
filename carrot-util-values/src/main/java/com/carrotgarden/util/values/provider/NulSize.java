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
	public final long asLong() {
		return 0L;
	}

	@Override
	protected final SizeValue returnSize(long value) {
		return ValueBuilder.newSize(value);
	}

}
