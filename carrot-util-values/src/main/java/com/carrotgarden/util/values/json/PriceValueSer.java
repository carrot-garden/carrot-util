/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.json;

import com.carrotgarden.util.values.api.DecimalValue;
import com.carrotgarden.util.values.api.PriceValue;

class PriceValueSer extends ScaledValueSer<PriceValue, DecimalValue> {

	protected PriceValueSer(Class<PriceValue> klaz) {
		super(klaz);
	}

}
