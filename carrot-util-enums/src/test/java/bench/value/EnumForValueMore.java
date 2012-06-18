/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.value;

// probably not a good idea?

class EnumForValueMore<V extends ValueIce> extends EnumForValue<V> {

	// keep first
	private static int sequence;
	private static final int size;
	private static final EnumForValue<?>[] values;
	static {
		sequence = EnumForValue.size;
		size = countEnumFields(EnumForValueMore.class) + EnumForValue.size;
		values = new EnumForValue<?>[size]; // TODO add copy from superclass
	}

	protected EnumForValueMore(V defVal) {
		super(values, sequence++, defVal);
	}

	//

	public static final EnumForValue<ValueZen> VALUE_ZEN = //
	new EnumForValue<ValueZen>(null);

}
