/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_enum_static;

import com.carrotgarden.util.enums.DictEnumBase;

public class FieldOne<V> extends DictEnumBase<V> {

	protected FieldOne() {
		super();
	}

	private final static <X> FieldOne<X> NEW(final String comment) {
		return new FieldOne<X>();
	}

	public static final FieldOne<String> A0 = NEW("E1-0");
	public static final FieldOne<Integer> A1 = NEW("E1-1");
	public static final FieldOne<Runnable> A2 = NEW("E1-2");

}
