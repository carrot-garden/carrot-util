/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_enum_static;

import com.carrotgarden.util.enums.DictEnumBase;

public class IceOneBean extends Base implements IceOne {

	public IceOneBean() {
		this(DictEnumBase.sizeOf(FieldOne.class));
	}

	protected IceOneBean(final int size) {
		super(size);
	}

	protected IceOneBean(final Object[] array) {
		super(array);
	}

	@Override
	public <V> V get(final FieldOne<V> field) {
		return get(field.ordinal());
	}

	@Override
	public <V> void set(final FieldOne<V> field, final V value) {
		set(field.ordinal(), value);
	}

	@Override
	public IceOneBean freeze() {
		return new IceOneBean(array);
	}

}
