/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.param_enum;

public class Base implements Freezer {

	protected final Object[] array;

	protected Base(final int size) {
		this.array = new Object[size];
	}

	protected Base(final Object[] array) {
		this.array = array;
	}

	@SuppressWarnings("unchecked")
	protected <V> V get(final int index) {
		return (V) array[index];
	}

	protected <V> void set(final int index, final V value) {
		array[index] = value;
	}

	@Override
	public Object freeze() {
		return null;
	}

	@Override
	public boolean isFrozen() {
		return false;
	}

}
