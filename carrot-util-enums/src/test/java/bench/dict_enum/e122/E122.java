/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_enum.e122;

import bench.dict_enum.e12.E12;

public class E122<V> extends E12<V> {

	private E122(final String comment) {
		super(comment);
	}

	private final static <X> E122<X> NEW(final String comment) {
		return new E122<X>(comment);
	}

	public static final E122<String> E0 = NEW("E122-0");
	public static final E122<String> E1 = NEW("E122-1");
	public static final E122<String> E3 = NEW("E122-2");

	public static final E122<String> E4 = NEW("E122-3");
	public static final E122<String> E5 = NEW("E122-4");
	public static final E122<String> E6 = NEW("E122-5");

}
