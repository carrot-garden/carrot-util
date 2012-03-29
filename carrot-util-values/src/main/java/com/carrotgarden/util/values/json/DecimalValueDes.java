/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carrotgarden.util.values.api.DecimalValue;
import com.carrotgarden.util.values.provider.ValueBuilder;

class DecimalValueDes extends ScaledValueDes<DecimalValue, DecimalValue> {

	static Logger log = LoggerFactory.getLogger(DecimalValueDes.class);

	protected DecimalValueDes(Class<DecimalValue> klaz) {
		super(klaz);
	}

	@Override
	protected DecimalValue newValue(long mantissa, int exponent) {
		return ValueBuilder.newDecimal(mantissa, exponent);
	}

}
