/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_enum;

import java.util.Map;

import org.junit.Test;

import bench.dict_enum.e1.E1;
import bench.dict_enum.e11.E11;
import bench.dict_enum.e111.E111;
import bench.dict_enum.e12.E12;
import bench.dict_enum.e121.E121;
import bench.dict_enum.e122.E122;

import com.carrotgarden.util.enums.DictEnum;
import com.carrotgarden.util.enums.DictEnumBase;

public class TestDictEnumBase {

	@Test
	public void test0() {

		for (final DictEnum<?> dict : DictEnumBase.valuesForBase(E1.class)) {
			System.out.println("# key : " + dict);
		}

		System.out.println("########");

		for (final DictEnum<?> dict : DictEnumBase.valuesForBase(E122.class)) {
			System.out.println("# dict : " + dict);
		}

		System.out.println("########");

		for (final DictEnum<?> dict : DictEnumBase.valuesForBase(E11.class)) {
			System.out.println("# dict : " + dict);
		}

		System.out.println("########");

		for (final DictEnum<?> dict : DictEnumBase.valuesForBase(E12.class)) {
			System.out.println("# dict : " + dict);
		}

		System.out.println("########");

		for (final DictEnum<?> dict : DictEnumBase.valuesForBase(E111.class)) {
			System.out.println("# dict : " + dict);
		}

		System.out.println("########");

		for (final DictEnum<?> dict : DictEnumBase.valuesForBase(E121.class)) {
			System.out.println("# dict : " + dict);
		}

		//
		final Map<Integer, DictEnumBase<?>> map = DictEnumBase.getEnumMap();

		// assertEquals(map.size(), 24);

	}

}
