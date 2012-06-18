/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_enum_static;

public class FieldTwo<V> extends FieldOne<V> {

	protected FieldTwo() {
		super();
	}

	private final static <X> FieldTwo<X> NEW(final String comment) {
		return new FieldTwo<X>();
	}

	public static final FieldTwo<String> B0 = NEW("E1-0");
	public static final FieldTwo<Integer> B1 = NEW("E1-1");
	public static final FieldTwo<Runnable> B2 = NEW("E1-2");

}
