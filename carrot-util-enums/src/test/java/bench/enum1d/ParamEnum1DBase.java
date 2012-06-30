/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.enum1d;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.carrotgarden.util.enums.EnumJDK;
import com.carrotgarden.util.enums.ParamEnum;

public class ParamEnum1DBase<V> implements ParamEnum<V> {

	protected final int ordinal;

	@Override
	public final int ordinal() {
		return ordinal;
	}

	protected final String name;

	@Override
	public final String name() {
		return name;
	}

	protected final V defaultValue;

	@Override
	public final V value() {
		return defaultValue;
	}

	//

	protected ParamEnum1DBase(final ParamEnum1DBase[] values,
			final int ordinal, final V defVal) {

		this.ordinal = ordinal;

		this.name = nameOf(ordinal);

		this.defaultValue = defVal;

		values[ordinal] = this;

	}

	private static final int MODIFIERS = Modifier.PUBLIC | Modifier.STATIC
			| Modifier.FINAL;

	private static final boolean isEnumField(final Field field) {

		final int modifiers = field.getModifiers();

		final Class<?> klaz = field.getDeclaringClass();

		return modifiers == MODIFIERS
				&& ParamEnum1DBase.class.isAssignableFrom(klaz);

	}

	protected static final <E extends ParamEnum1DBase<?>> int countEnumFields(
			final Class<E> klaz) {

		final Field[] fieldArray = klaz.getDeclaredFields();

		int count = 0;

		for (final Field field : fieldArray) {
			// System.err.println("field=" + field);
			if (isEnumField(field)) {
				count++;
			}
		}

		return count;

	}

	protected String nameOf(final int ordinal) {

		final Field[] fieldArray = this.getClass().getDeclaredFields();

		int count = 0;

		for (final Field field : fieldArray) {
			if (isEnumField(field)) {
				if (count == ordinal) {
					return field.getName();
				} else {
					count++;
				}
			}
		}

		return null;

	}

	@Override
	public final String toString() {
		return name;
	}

	@Override
	public final boolean is(final ParamEnum that) {
		// assuming same class loader
		return this == that;
	}

	@Override
	public final boolean isIn(final ParamEnum... thatArray) {
		for (final ParamEnum that : thatArray) {
			if (is(that)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(final EnumJDK other) {
		if (other == null) {
			throw new NullPointerException("other == null");
		}
		final ParamEnum1DBase that = (ParamEnum1DBase) other;
		if (this.getClass() != that.getClass())
			throw new ClassCastException(
					"this and that must come from same class");
		return this.ordinal - that.ordinal;
	}

	@Override
	public int sequence() {
		return ordinal;
	}

}
