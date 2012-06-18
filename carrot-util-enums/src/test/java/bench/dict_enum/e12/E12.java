/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_enum.e12;

import bench.dict_enum.e1.E1;

public class E12<V> extends E1<V> {

	protected E12(final String comment) {
		super(comment);
	}

	private final static <X> E12<X> NEW(final String comment) {
		return new E12<X>(comment);
	}

	public static final E12<String> D0 = NEW("E12-0");
	public static final E12<String> D1 = NEW("E12-1");
	public static final E12<String> D2 = NEW("E12-2");

}
