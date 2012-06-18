/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.enums;

/** type-safe ENUM key */
public interface DictEnum<V> extends EnumJDK, Sequence, Guid, CodeString {

	/**
	 * @return absolute index in enum hierarchy; creation order; can change from
	 *         one JVM run another; otherwise is GUID during JVM run session;
	 */
	@Override
	int sequence();

	/**
	 * @return relative index inside class (and super class and *); immutable;
	 *         based on ENUM creation order inside inheritance hierarchy; based
	 *         on ENUM order inside each class level;
	 * */
	@Override
	int ordinal();

	/**
	 * @return field name; unique inside each inheritance hierarchy
	 **/
	@Override
	String name();

	//

	/**
	 * @return immutable code for external serialization; user provided optional
	 *         parameter; not checked for uniqueness;
	 */
	@Override
	String code();

	/** @return unique key based on dict enum class names space */
	@Override
	String guid();

	/** equality is based on {@link #sequence()} */
	@Override
	boolean equals(Object dict);

	/** hash code is based on {@link #sequence()} */
	@Override
	int hashCode();

}
