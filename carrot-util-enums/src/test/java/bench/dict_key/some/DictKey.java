/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_key.some;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.carrotgarden.util.enums.Comment;
import com.carrotgarden.util.enums.DictEnum;
import com.carrotgarden.util.enums.EnumJDK;

public class DictKey<V> implements DictEnum<V>, Comment {

	private static final AtomicInteger CREATED = new AtomicInteger(0);

	private static final ConcurrentMap<Integer, DictEnum<?>> ENUM_MAP = //
	new ConcurrentHashMap<Integer, DictEnum<?>>();

	private static final ConcurrentMap<Class<DictEnum<?>>, AtomicInteger> INDEX_MAP = //
	new ConcurrentHashMap<Class<DictEnum<?>>, AtomicInteger>();

	//

	public static final DictEnum<?>[] values() {

		return ENUM_MAP.values().toArray(new DictEnum<?>[0]);

	}

	//

	private String name;
	private String guid;

	private final int ordinal;
	private final int index;
	private final String code;
	private final String comment;

	//

	protected DictKey() {
		throw new RuntimeException("do not use");
	}

	private static class Manager extends SecurityManager {
		public Class<?>[] getStack() {
			return super.getClassContext();
		}
	}

	private final static Manager manager = new Manager();

	private final Class<?> klaz;

	private final AtomicInteger INDEX = new AtomicInteger(0);

	private DictKey(final String code, final String comment) {

		this.klaz = manager.getStack()[3];

		this.ordinal = CREATED.getAndIncrement();

		this.index = INDEX.getAndIncrement();

		this.code = code;

		this.comment = comment;

		ENUM_MAP.put(ordinal, this);

	}

	@Override
	public final String name() {

		if (name == null) {

			final Field[] fields = klaz.getDeclaredFields();

			for (final Field field : fields) {
				try {
					if (this == field.get(this)) {
						name = field.getName();
						break;
					}
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}

			if (name == null) {
				name = "" + ordinal();
			}

		}

		return name;

	}

	@Override
	public final String guid() {

		if (guid == null) {
			guid = klaz.getName() + "#" + name();
		}

		return guid;

	}

	@Override
	public final int sequence() {
		return index;
	}

	@Override
	public final int ordinal() {
		return ordinal;
	}

	@Override
	public final boolean equals(final Object dict) {

		if (dict instanceof DictEnum) {

			final DictKey<?> that = (DictKey<?>) dict;

			return that.ordinal() == this.ordinal();

		}

		return false;

	}

	@Override
	public final int hashCode() {

		return ordinal();

	}

	public static final DictEnum<?> fromOrdinal(final int ordinal) {

		final DictEnum<?> dict = ENUM_MAP.get(ordinal);

		if (dict == null) {
			return UNKNOWN;
		}

		return dict;

	}

	@Override
	public final String code() {

		return code;

	}

	@Override
	public final String comment() {

		return comment;

	}

	protected final static <X> DictKey<X> NEW(final String comment) {
		return new DictKey<X>("", comment);
	}

	protected final static <X> DictKey<X> NEW(final String code,
			final String comment) {
		return new DictKey<X>(code, comment);
	}

	// ########################

	public static final DictEnum<?> UNKNOWN = NEW("default instance for non-existing entry");

	@Override
	public int compareTo(final EnumJDK o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
