/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_enum.e11;

import bench.dict_enum.e1.E1;

public class E11<V> extends E1<V> {

	protected E11(final String comment) {
		super(comment);
	}

	private final static <X> E11<X> NEW(final String comment) {
		return new E11<X>(comment);
	}

	public static final E11<String> A_0 = NEW("E11-0");
	public static final E11<String> A_1 = NEW("E11-1");
	public static final E11<String> A_2 = NEW("E11-2");

	public static final E11<String> B_0 = NEW("E11-3");
	public static final E11<String> B_1 = NEW("E11-4");
	public static final E11<String> B_2 = NEW("E11-5");

}
