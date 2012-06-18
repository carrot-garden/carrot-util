/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.enums;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * base class for type-safe dictionary enumerators
 * 
 * shares enum maps for complete enum dict hierarchy
 */
public class DictEnumBase<V> implements DictEnum<V>, Comment {

	{
		// System.out.println("LOAD DictEnum");
	}

	/** GUID within inheritance hierarchy */
	private static final AtomicInteger SEQUENCE = new AtomicInteger(0);

	/** GUID -> ENUM */
	private static final ConcurrentMap<Integer, DictEnumBase<?>> ENUM_MAP = //
	new ConcurrentHashMap<Integer, DictEnumBase<?>>();

	/** KLAZ -> ORDINAL; during instance build */
	private static final ConcurrentMap<Class<DictEnumBase<?>>, AtomicInteger> ORDINAL_MAP = //
	new ConcurrentHashMap<Class<DictEnumBase<?>>, AtomicInteger>();

	/** KLAZ -> VALUES[] */
	private static final ConcurrentMap<Class<DictEnumBase<?>>, DictEnumBase<?>[]> VALUES_MAP = //
	new ConcurrentHashMap<Class<DictEnumBase<?>>, DictEnumBase<?>[]>();

	//

	/** @return clone map */
	public static final Map<Integer, DictEnumBase<?>> getEnumMap() {
		return new HashMap<Integer, DictEnumBase<?>>(ENUM_MAP);
	}

	//

	/** lazy init; not type-safe array; no clone */
	@SuppressWarnings("unchecked")
	protected static final <K extends DictEnumBase<?>> DictEnumBase<?>[] valuesFor(
			final Class<K> klaz) {

		DictEnumBase<?>[] values = VALUES_MAP.get(klaz);

		if (values == null) {

			/** init klaz: assign static fields */

			try {
				final ClassLoader loader = klaz.getClassLoader();
				Class.forName(klaz.getName(), true, loader);
			} catch (final Exception e) {
				throw new RuntimeException("can not init klaz", e);
			}

			/** produce target values list */

			final int size = ENUM_MAP.size();

			final List<DictEnumBase<?>> target = new ArrayList<DictEnumBase<?>>(
					size);

			final Set<String> nameSet = new HashSet<String>();

			for (int k = 0; k < size; k++) {
				final DictEnumBase<?> dict = ENUM_MAP.get(k);
				if (dict.getClass().isAssignableFrom(klaz)) {
					target.add(dict);
					nameSet.add(dict.name());
				}
			}

			/** check for duplicate names in enum dict hierarchy */

			if (nameSet.size() != target.size()) {

				System.out.println("contains duplicate names : " + klaz);

				for (final DictEnumBase<?> dict : target) {
					System.out.println(" name=" + dict.name() + " guid="
							+ dict.guid());
				}

				throw new RuntimeException("duplicate names in dict enum");

			}

			/** done */

			values = target.toArray(new DictEnumBase<?>[0]);

			VALUES_MAP.put((Class<DictEnumBase<?>>) klaz, values);

		}

		return values;

	}

	/** @return not type-save array; clone */
	public static final <K extends DictEnumBase<?>> DictEnumBase<?>[] valuesForBase(
			final Class<K> klaz) {

		final DictEnumBase<?>[] source = valuesFor(klaz);

		final DictEnumBase<?>[] target = source.clone();

		return target;

	}

	/** @return type-safe array; clone */
	@SuppressWarnings("unchecked")
	public static final <K extends DictEnumBase<?>> K[] valuesForEnum(
			final Class<K> klaz) {

		final DictEnumBase<?>[] source = valuesFor(klaz);

		final int size = source.length;

		final K[] target = (K[]) Array.newInstance(klaz, size);

		for (int k = 0; k < size; k++) {
			target[k] = (K) source[k];
		}

		return target;

	}

	public static final <K extends DictEnumBase<?>> int sizeOf(
			final Class<K> klaz) {

		final DictEnumBase<?>[] values = valuesFor(klaz);

		final int size = values.length;

		return size;

	}

	private String name;
	private String guid;

	private final int sequence;
	private final int ordinal;
	private final String code;
	private final String comment;

	//

	/** */
	@SuppressWarnings("unchecked")
	private int makeOrdinal() {

		final Class<DictEnumBase<?>> klazCurrent = (Class<DictEnumBase<?>>) getClass();

		final Class<?> klazParent = klazCurrent.getSuperclass();

		// System.out.println("parent=" + parent);

		AtomicInteger parentCount = ORDINAL_MAP.get(klazParent);
		if (parentCount == null) {
			parentCount = new AtomicInteger(0);
			ORDINAL_MAP.put((Class<DictEnumBase<?>>) klazParent, parentCount);
		}

		// System.out.println("parentCount=" + parentCount);

		final AtomicInteger countOld = ORDINAL_MAP.get(klazCurrent);

		if (countOld == null) {

			/** this is initial instance */

			final int indexStart;

			if (klazParent == DictEnumBase.class) {
				indexStart = 0;
			} else {
				indexStart = parentCount.get();
			}

			final AtomicInteger countNew = new AtomicInteger(indexStart);

			ORDINAL_MAP.put(klazCurrent, countNew);

			return countNew.getAndIncrement();

		} else {

			/** this is following instance */

			return countOld.getAndIncrement();

		}

	}

	private int makeSequence() {
		return SEQUENCE.getAndIncrement();
	}

	protected DictEnumBase() {

		this("", "");

	}

	protected DictEnumBase(final String comment) {

		this("", comment);

	}

	protected DictEnumBase(final String code, final String comment) {

		this.code = code;
		this.comment = comment;
		this.sequence = makeSequence();
		this.ordinal = makeOrdinal();

		ENUM_MAP.put(sequence, this);

	}

	/** lazy init */
	@Override
	public final String name() {

		if (name == null) {

			final Field[] fields = getClass().getDeclaredFields();

			for (final Field field : fields) {
				try {
					field.setAccessible(true);
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

	/** lazy init */
	@Override
	public final String guid() {
		if (guid == null) {
			guid = getClass().getName() + "#" + name();
		}
		return guid;
	}

	@Override
	public final int sequence() {
		return sequence;
	}

	@Override
	public final int ordinal() {
		return ordinal;
	}

	@Override
	public final boolean equals(final Object dict) {
		if (dict instanceof DictEnumBase) {
			final DictEnumBase<?> that = (DictEnumBase<?>) dict;
			return that.sequence() == this.sequence();
		}
		return false;
	}

	@Override
	public final int hashCode() {
		return sequence();
	}

	@Override
	public String code() {
		return code;
	}

	@Override
	public final String comment() {
		return comment;
	}

	private String text;

	/** lazy init */
	@Override
	public String toString() {
		if (text == null) {
			text = String
					.format("NAME=%-20s ORDINAL=%02d SEQUENCE=%02d GUID=%30s COMMENT=%s ",
							name(), ordinal(), sequence(), guid(), comment());
		}
		return text;
	}

	@Override
	public int compareTo(final EnumJDK other) {
		final DictEnumBase<?> that = (DictEnumBase<?>) other;
		return this.sequence() - that.sequence();
	}

}
