/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_enum.e121;

import bench.dict_enum.e12.E12;

public class E121<V> extends E12<V> {

	private E121(final String comment) {
		super(comment);
	}

	private final static <X> E121<X> NEW(final String comment) {
		return new E121<X>(comment);
	}

	public static final E121<String> E0 = NEW("E121-0");
	public static final E121<String> E1 = NEW("E121-1");
	public static final E121<String> E3 = NEW("E121-2");

}
