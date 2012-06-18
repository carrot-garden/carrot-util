/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_enum_static;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.carrotgarden.util.enums.DictEnumBase;

public class TestDictEnumStatic {

	@Test
	public void test0() {

		final DictEnumBase<?>[] valuesOne = DictEnumBase
				.valuesForBase(FieldOne.class);

		assertEquals(valuesOne.length, 3);

		//

		final DictEnumBase<?>[] valuesTwo = DictEnumBase
				.valuesForBase(FieldTwo.class);

		assertEquals(valuesTwo.length, 6);

		//

		final Map<Integer, DictEnumBase<?>> map = DictEnumBase.getEnumMap();

		// assertEquals(map.size(), 6);

		//

		final IceOne one = new IceOneBean();
		one.set(FieldOne.A0, "hello 1");
		assertEquals(one.get(FieldOne.A0), "hello 1");

		//

		final IceTwo two = new IceTwoBean();
		//
		two.set(FieldOne.A0, "hello 1");
		assertEquals(two.get(FieldOne.A0), "hello 1");
		//
		two.set(FieldTwo.B0, "hello 2");
		assertEquals(two.get(FieldTwo.B0), "hello 2");

		//

	}

}
