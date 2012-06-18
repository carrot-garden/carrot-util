/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_enum.e111;

import bench.dict_enum.e11.E11;

public class E111<V> extends E11<V> {

	protected E111(final String comment) {
		super(comment);
	}

	private final static <X> E111<X> NEW(final String comment) {
		return new E111<X>(comment);
	}

	public static final E111<String> C0 = NEW("E111-0");
	public static final E111<String> C1 = NEW("E111-1");
	public static final E111<String> C2 = NEW("E111-2");

}
