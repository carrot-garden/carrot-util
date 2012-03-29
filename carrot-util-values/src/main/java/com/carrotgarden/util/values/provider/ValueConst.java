/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.provider;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carrotgarden.util.test.size.JavaSize;
import com.carrotgarden.util.values.api.DecimalValue;
import com.carrotgarden.util.values.api.PriceValue;
import com.carrotgarden.util.values.api.SizeValue;
import com.carrotgarden.util.values.api.TextValue;
import com.carrotgarden.util.values.api.TimeValue;
import com.carrotgarden.util.values.api.Value;

public final class ValueConst {

	private static final Logger log = LoggerFactory.getLogger(ValueConst.class);

	public static final void sizeReport(final Class<?> klaz) {

		final StringBuilder text = new StringBuilder(1024);

		text.append("##################################");
		text.append("\n");
		text.append("Instance Size Report");
		text.append("\n");

		final Field[] fieldArray = klaz.getFields();

		for (final Field field : fieldArray) {
			if (Value.class.isAssignableFrom(field.getType())) {
				try {
					final String name = field.getName();
					final Object value = field.get(null);
					final int size = JavaSize.of(value);
					text.append(String.format("%-20s %,9d", name, size));
					text.append("\n");
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		}

		text.append("##################################");
		text.append("\n");

		log.debug("\n{}\n", text);

	}

	private ValueConst() {
	}

	public static final DecimalValue NULL_DECIMAL = //
	new DefDecimal(0, 0);

	public static final TextValue NULL_TEXT = //
	new NulText();

	public static final PriceValue NULL_PRICE = //
	new NulPrice();
	public static final PriceValue ZERO_PRICE = //
	new NulPrice();

	public static final SizeValue NULL_SIZE = //
	new NulSize();
	public static final SizeValue ZERO_SIZE = //
	new NulSize();

	public static final TimeValue NULL_TIME = //
	new NulTime();
	public static final TimeValue ZERO_TIME = //
	new NulTime();

	public static final SizeValue[] NULL_SIZE_ARRAY = new SizeValue[0];

	static {
		// sizeReport(ValueConst.class);
	}

}
