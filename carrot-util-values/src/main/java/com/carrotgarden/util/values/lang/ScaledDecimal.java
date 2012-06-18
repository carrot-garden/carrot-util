/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.lang;

/**
 * represents type safe base 10 floating point
 * 
 * http://en.wikipedia.org/wiki/Floating_point
 * 
 * "T" primary type
 * 
 * "F" multiplier (factor) type
 * 
 */
public interface ScaledDecimal<T extends ScaledDecimal<T, F>, F extends ScaledDecimal<?, ?>>
		extends Comparable<T> {

	//

	/**
	 * a.k.a significand;
	 * 
	 * http://en.wikipedia.org/wiki/Significand
	 */
	long mantissa();

	/**
	 * a.k.a scale;
	 * 
	 * http://en.wikipedia.org/wiki/Exponentiation
	 */
	int exponent();

	//

	/** mantissa == 0 */
	boolean isZero();

	/** compare scales of two numbers */
	boolean equalsScale(T that);

	//

	/** change scale */
	T scale(int exponent) throws ArithmeticException;

	/** normalize: remove non significant zeros from mantissa, if any */
	T norm();

	/** change sign */
	T neg();

	//

	/** type safe addition for T */
	T add(T that) throws ArithmeticException;

	/** type safe subtract for T */
	T sub(T that) throws ArithmeticException;

	/** type safe multiply for T */
	T mult(long factor) throws ArithmeticException;

	/** type safe division for T */
	T div(long factor) throws ArithmeticException;

	/** type safe division for T */
	long count(T that) throws ArithmeticException;

	//

	/** type safe multiply for T, F */
	T mult(F factor) throws ArithmeticException;

	/** type safe division for T, F */
	T div(F factor) throws ArithmeticException;

	//

	/** can be used for sorting */
	@Override
	int compareTo(T that);

	/** contract: must compare only to T */
	@Override
	boolean equals(Object that);

	/** can be used as keys */
	@Override
	int hashCode();

	//

	/** legacy compatibility */
	@Deprecated
	int asInt() throws ArithmeticException;

	/** legacy compatibility */
	@Deprecated
	long asLong() throws ArithmeticException;

	/** legacy compatibility */
	@Deprecated
	float asFloat();

	/** legacy compatibility */
	@Deprecated
	double asDouble();

	//

}
