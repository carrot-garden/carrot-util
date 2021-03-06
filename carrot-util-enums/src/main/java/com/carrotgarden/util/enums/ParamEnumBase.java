/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.enums;

public class ParamEnumBase<V> extends DictEnumBase<V> implements ParamEnum<V> {

	protected ParamEnumBase() {
		this("", null);
	}

	protected ParamEnumBase(final V value) {
		this("", value);
	}

	protected ParamEnumBase(final String comment, final V value) {
		super(comment);
		this.value = value;
	}

	private final V value;

	@Override
	public final V value() {
		return value;
	}

	@Override
	public final boolean is(final ParamEnum<?> that) {
		/** assuming same class loader */
		return this == that;
	}

	@Override
	public final boolean isIn(final ParamEnum<?>... thatArray) {
		if (thatArray == null) {
			return false;
		}
		for (final ParamEnum<?> that : thatArray) {
			if (is(that)) {
				return true;
			}
		}
		return false;
	}

}
