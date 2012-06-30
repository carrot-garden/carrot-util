/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.param_enum;

import com.carrotgarden.util.enums.ParamEnumBase;

public class FieldOne<V> extends ParamEnumBase<V> {

	private FieldOne(final V value) {
		super(value);
	}

	private final static <X> FieldOne<X> NEW(final X value) {
		return new FieldOne<X>(value);
	}

	public static final FieldOne<String> A0 = NEW("E1-0");
	public static final FieldOne<Integer> A1 = NEW(123);
	public static final FieldOne<Runnable> A2 = NEW((Runnable) new Thread());

}
