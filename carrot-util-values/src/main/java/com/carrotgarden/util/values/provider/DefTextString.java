/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.provider;

import java.util.Locale;

import com.carrotgarden.util.values.api.TextValue;

final class DefTextString extends BaseText {

	private final String value;

	DefTextString(final String value) {
		// detach from source
		this.value = new String(value);
	}

	@Override
	public final String toString() {
		return value;
	}

	@Override
	public final CharSequence subSequence(final int start, final int end) {
		return ValueBuilder.newText(value.substring(start, end));
	}

	@Override
	public final TextValue toUpperCase() {
		return new DefTextString(value.toUpperCase(Locale.US));
	}

	@Override
	public final TextValue toLowerCase() {
		return new DefTextString(value.toLowerCase(Locale.US));
	}

}
