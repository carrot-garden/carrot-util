/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.provider;

import com.carrotgarden.util.anno.NotMutable;
import com.carrotgarden.util.values.api.IntegerValue;
import com.carrotgarden.util.values.lang.MathExtra;

@NotMutable
abstract class BaseInteger extends ValueFreezer<IntegerValue> implements IntegerValue {

	//

	protected abstract IntegerValue returnSize(long value);

	@Override
	public abstract long asLong();

	//

	@Deprecated
	@Override
	public final int asInt() {
		return MathExtra.castLongToInt(asLong());
	}

	@Override
	public final int compareTo(final IntegerValue that) {
		final long v1 = this.asLong();
		final long v2 = that.asLong();
		return v1 < v2 ? -1 : (v1 == v2 ? 0 : 1);
	}

	@Override
	public final int hashCode() {
		final long value = asLong();
		return (int) (value ^ (value >>> 32));
	}

	@Override
	public final boolean equals(Object thatSize) {
		if (thatSize instanceof IntegerValue) {
			IntegerValue that = (IntegerValue) thatSize;
			return this.asLong() == that.asLong();
		}
		return false;
	}

	@Override
	public final String toString() {
		return String.format("Size > %9d", asLong()); // 16
	}

	@Override
	public final boolean isNull() {
		return this == ValueConst.NULL_INTEGER;
	}

	@Override
	public final IntegerValue add(final IntegerValue that) throws ArithmeticException {
		return returnSize(MathExtra.longAdd(this.asLong(), that.asLong()));
	}

	@Override
	public final IntegerValue sub(final IntegerValue that) throws ArithmeticException {
		return returnSize(MathExtra.longSub(this.asLong(), that.asLong()));
	}

	@Override
	public final IntegerValue mult(final long factor) throws ArithmeticException {
		return returnSize(MathExtra.longMult(this.asLong(), factor));
	}

	@Override
	public final IntegerValue div(final long factor) throws ArithmeticException {
		return returnSize(this.asLong() / factor);
	}

	@Override
	public final long count(final IntegerValue that) throws ArithmeticException {
		return (this.asLong() / that.asLong());
	}

	@Override
	public final boolean isZero() {
		return asLong() == 0L;
	}

}
