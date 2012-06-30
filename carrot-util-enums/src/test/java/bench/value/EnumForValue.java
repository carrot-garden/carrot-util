/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.value;

import bench.enum1d.ParamEnum1DBase;

class EnumForValue<V extends ValueIce> extends ParamEnum1DBase<V> {

	// keep first
	private static int sequence;
	protected static final int size;
	protected static final EnumForValue<?>[] values;
	static {
		sequence = 0;
		size = countEnumFields(EnumForValue.class);
		values = new EnumForValue<?>[size];
	}

	public static final int size() {
		return size;
	}

	public static final EnumForValue<?>[] values() {
		return values.clone();
	}

	//

	protected EnumForValue(final V defVal) {
		super(values, sequence++, defVal);
	}

	protected EnumForValue(final EnumForValue<?>[] values, int sequence,
			final V defVal) {
		super(values, sequence++, defVal);
	}

	//

	public static final EnumForValue<ValueOne> VALUE_ONE = //
	new EnumForValue<ValueOne>(null);

	public static final EnumForValue<ValueTwo> VALUE_TWO = //
	new EnumForValue<ValueTwo>(null);

}
