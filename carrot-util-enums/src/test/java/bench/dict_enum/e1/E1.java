/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_enum.e1;

import com.carrotgarden.util.enums.DictEnumBase;

public class E1<V> extends DictEnumBase<V> {

	protected E1(final String comment) {
		super(comment);
	}

	private final static <X> E1<X> NEW(final String comment) {
		return new E1<X>(comment);
	}

	public static final E1<String> A0 = NEW("E1-0");
	public static final E1<String> A1 = NEW("E1-1");
	public static final E1<String> A2 = NEW("E1-2");

}
