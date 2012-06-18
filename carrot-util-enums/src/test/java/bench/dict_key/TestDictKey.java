/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_key;

import static org.junit.Assert.*;

import org.junit.Test;

import bench.dict_key.some.DictBase;
import bench.dict_key.some.DictOne;
import bench.dict_key.some.DictTwo;

import com.carrotgarden.util.enums.DictEnum;

public class TestDictKey {

	class Value {

		<V> V get(final DictEnum<V> key) {
			return null;
		}

	}

	@Test
	public void testName() {

		final Value value = new Value();

		final String result1 = value.get(DictOne.TEST);

		final Integer result2 = value.get(DictTwo.ONE);
		final Integer result3 = value.get(DictTwo.TWO);

		assertEquals(DictOne.ONE.name(), "ONE");
		assertEquals(DictOne.TEST.name(), "TEST");

		System.out.println(DictOne.ONE.name() + " " + DictOne.ONE.ordinal());
		System.out.println(DictTwo.TWO.name() + " " + DictTwo.TWO.ordinal());

		System.out.println("guid : " + DictOne.ONE.guid());
		System.out.println("guid : " + DictTwo.TWO.guid());

		System.out.println("DictOne.TEST.guid() : " + DictOne.TEST.guid());
		System.out.println("DictTwo.TEST.guid() : " + DictTwo.TEST.guid());

		System.out.println("DictBase.X1.guid() : " + DictBase.X1.guid());

		for (final DictEnum<?> key : DictBase.values()) {

			System.out.println("# 0 # key : " + key.guid() + " / "
					+ key.ordinal());

		}

		for (final DictEnum<?> key : DictOne.values()) {

			System.out.println("# 1 # key : " + key.guid() + " / "
					+ key.ordinal());

		}

		for (final DictEnum<?> key : DictTwo.values()) {

			System.out.println("# 2 # key : " + key.guid() + " / "
					+ key.ordinal());

		}

	}

}
