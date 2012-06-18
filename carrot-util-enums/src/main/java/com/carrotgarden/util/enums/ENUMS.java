/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.enums;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ENUMS {

	public static final int ENUM_MODS = //
	Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL;

	public static final boolean isEnumField(final Class<?> base,
			final Field field) {

		final int modifiers = field.getModifiers();

		final Class<?> klaz = field.getDeclaringClass();
		// System.out.println("klaz " + klaz);

		final boolean isAssign = base.isAssignableFrom(klaz);

		return (modifiers == ENUMS.ENUM_MODS && isAssign);

	}

	// System.out.println("@ field.getName() " + field.getName());
	// System.out.println("@ klaz " + klaz);

	public static <O extends Ordinal> String nameFrom(final Class<O> base,
			final O instance, final int ordinal) {

		final Class<?> klaz = instance.getClass();

		if (base.isAssignableFrom(klaz)) {

			final Field[] fieldArray = klaz.getDeclaredFields();

			for (final Field field : fieldArray) {
				try {

					final Object value = field.get(instance);

					if (value instanceof Ordinal) {

						final Ordinal ordinalValue = (Ordinal) value;

						if (ordinalValue.ordinal() == ordinal) {
							return field.getName();
						}

					}

				} catch (final Exception e) {
					e.printStackTrace();
				}

			}

		}

		return null;

	}

}
