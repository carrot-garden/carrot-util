/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.provider;

import java.util.UUID;

import com.carrotgarden.util.ascii.ASCII;
import com.carrotgarden.util.values.api.DecimalValue;
import com.carrotgarden.util.values.api.IntegerValue;
import com.carrotgarden.util.values.api.PriceValue;
import com.carrotgarden.util.values.api.SizeValue;
import com.carrotgarden.util.values.api.TextValue;
import com.carrotgarden.util.values.api.TimeValue;
import com.carrotgarden.util.values.lang.MathExtra;
import com.carrotgarden.util.values.lang.MathExtra.DoubleParts;

public final class ValueBuilder {

	private ValueBuilder() {
	}

	public static final PriceValue newPrice(final long mantissa) {
		if (mantissa == 0) {
			return ValueConst.ZERO_PRICE;
		} else {
			return new DefPrice0(mantissa);
		}
	}

	public static final PriceValue newPrice(final long mantissa,
			final int exponent) throws ArithmeticException {
		switch (exponent) {
		case -9:
			return new DefPrice9(mantissa);
		case -8:
			return new DefPrice8(mantissa);
		case -7:
			return new DefPrice7(mantissa);
		case -6:
			return new DefPrice6(mantissa);
		case -5:
			return new DefPrice5(mantissa);
		case -4:
			return new DefPrice4(mantissa);
		case -3:
			return new DefPrice3(mantissa);
		case -2:
			return new DefPrice2(mantissa);
		case -1:
			return new DefPrice1(mantissa);
		case 00:
			return newPrice(mantissa);
		default:
			MathExtra.castIntToByte(exponent);
			final int mantSmall = (int) mantissa;
			if (mantSmall == mantissa) {
				return new DefPriceA(mantSmall, exponent);
			} else {
				return new DefPriceB(mantissa, exponent);
			}
		}
	}

	public static final PriceValue newPrice(final double value) {
		final DoubleParts parts = MathExtra.extractDecimal(value);
		final long mantissa = parts.getMantissa();
		final int exponent = parts.getExponent();
		return newPrice(mantissa, exponent);
	}

	public static final PriceValue newPriceMutable(final long mantissa,
			final int exponent) throws ArithmeticException {

		MathExtra.castIntToByte(exponent);

		return new VarPrice(mantissa, exponent);

	}

	private static final int SIZE_CACHE_LIMIT = 1024;

	private static final IntegerValue[] SIZE_CACHE = new IntegerValue[SIZE_CACHE_LIMIT];

	static {
		for (int k = 0; k < SIZE_CACHE_LIMIT; k++) {
			SIZE_CACHE[k] = new DefInteger(k);
		}
		SIZE_CACHE[0] = ValueConst.ZERO_INTEGER;
	}

	public static final IntegerValue newInteger(final long size) {
		if (0 <= size && size < SIZE_CACHE_LIMIT) {
			return SIZE_CACHE[(int) size];
		} else {
			return new DefInteger(size);
		}
	}

	public static final IntegerValue newSizeMutable(final long size) {
		return new VarInteger(size);
	}

	public static final TimeValue newTime(final long time) {
		return new DefTime(time);
	}

	public static final TimeValue newTimeMutable(final long time) {
		return new VarTime(time);
	}

	private static final boolean hasZeroBytes(final byte[] array) {
		for (final byte b : array) {
			if (b == 0) {
				return true;
			}
		}
		return false;
	}

	// use for ASCII
	public static final TextValue newText(final byte[] array) {
		if (array == null) {
			return ValueConst.NULL_TEXT;
		}
		if (array.length <= 8 && !hasZeroBytes(array)) {
			return new DefTextLong(array);
		} else {
			return new DefTextByteArray(array);
		}
	}

	// use for NON-ASCII
	public static final TextValue newText(final char[] array) {
		if (array == null) {
			return ValueConst.NULL_TEXT;
		}
		return new DefTextString(new String(array));
	}

	// will select appropriate representation
	public static final TextValue newText(final String string) {
		if (string == null) {
			return ValueConst.NULL_TEXT;
		}
		if (isPureAscii(string)) {
			return newText(string.getBytes(ASCII.ASCII_CHARSET));
		} else {
			return new DefTextString(string);
		}
	}

	public static final TextValue newTextId() {
		final UUID uuid = UUID.randomUUID();
		final TextValue text = newText(uuid.toString());
		return text;
	}

	public static final boolean isStrictMultiple(final PriceValue priceTest,
			final PriceValue priceStep) {
		final long count = priceTest.count(priceStep);
		final PriceValue priceBack = priceStep.mult(count);
		if (priceBack.equals(priceTest)) {
			return true;
		} else {
			return false;
		}
	}

	// do not use - not thread safe
	// public static final boolean isPureAscii(final String string) {
	// return ASCII.ASCII_ENCODER.canEncode(string);
	// }

	public static final boolean isPureAscii(final CharSequence seq) {
		final int size = seq.length();
		for (int k = 0; k < size; k++) {
			if ((seq.charAt(k) & 0xFF00) != 0) {
				return false;
			}
		}
		return true;
	}

	public static DecimalValue newDecimal(final long mantissa,
			final int exponent) {
		return new DefDecimal(mantissa, exponent);
	}

	public static DecimalValue newDecimal(final double value) {
		final DoubleParts parts = MathExtra.extractDecimal(value);
		final long mantissa = parts.getMantissa();
		final int exponent = parts.getExponent();
		return new DefDecimal(mantissa, exponent);
	}

	public static DecimalValue newDecimalMutable(final long mantissa,
			final int exponent) {
		return new VarDecimal(mantissa, exponent);
	}

	public static SizeValue newSize(final long mantissa, final int exponent) {
		final int mantSmall = (int) mantissa;
		if (mantSmall == mantissa) {
			return new DefSizeA(mantSmall, exponent);
		} else {
			return new DefSizeB(mantissa, exponent);
		}
	}

	public static final SizeValue newSize(final double value) {
		final DoubleParts parts = MathExtra.extractDecimal(value);
		final long mantissa = parts.getMantissa();
		final int exponent = parts.getExponent();
		return newSize(mantissa, exponent);
	}

}
