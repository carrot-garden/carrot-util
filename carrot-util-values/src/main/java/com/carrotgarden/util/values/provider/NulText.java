/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.provider;

import com.carrotgarden.util.values.api.TextValue;

final class NulText extends BaseText {

	static final String TEXT = "";

	@Override
	public final String toString() {
		return TEXT;
	}

	@Override
	public final TextValue toUpperCase() {
		return this;
	}

	@Override
	public final TextValue toLowerCase() {
		return this;
	}

}
