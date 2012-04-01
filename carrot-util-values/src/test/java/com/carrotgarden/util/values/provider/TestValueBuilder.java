/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.provider;

import static com.carrotgarden.util.values.provider.ValueBuilder.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.carrotgarden.util.test.size.JavaSize;
import com.carrotgarden.util.values.api.PriceValue;

public class TestValueBuilder {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestNewPrice1() {

		final PriceValue p0 = new DefPrice0(1000);

		final PriceValue p1 = new DefPriceA(1000, 2);

		final PriceValue p2 = new DefPriceB(1000, 2);

		assertEquals(p1.compareTo(p2), 0);
		assertEquals(p2.compareTo(p1), 0);

		assertEquals(JavaSize.of(ValueConst.NULL_PRICE), 8);
		assertEquals(JavaSize.of(p0), 16);
		assertEquals(JavaSize.of(p1), 16);
		assertEquals(JavaSize.of(p2), 24);

		assertTrue(newPrice(0, 0) instanceof NulPrice);

		assertTrue(newPrice(12, 0) instanceof DefPrice0);
		assertTrue(newPrice(122342341, 0) instanceof DefPrice0);
		assertTrue(newPrice(121212121212L, 0) instanceof DefPrice0);

		assertTrue(newPrice(12, 1) instanceof DefPriceA);
		assertTrue(newPrice(122342341, -123) instanceof DefPriceA);

		assertTrue(newPrice(121212121212L, -112) instanceof DefPriceB);

	}

	@Test
	public void TestNewPrice2() {

		assertTrue(newPrice(10, 00) instanceof DefPrice0);
		assertTrue(newPrice(10, -1) instanceof DefPrice1);
		assertTrue(newPrice(10, -2) instanceof DefPrice2);
		assertTrue(newPrice(10, -3) instanceof DefPrice3);
		assertTrue(newPrice(10, -4) instanceof DefPrice4);
		assertTrue(newPrice(10, -5) instanceof DefPrice5);
		assertTrue(newPrice(10, -6) instanceof DefPrice6);
		assertTrue(newPrice(10, -7) instanceof DefPrice7);
		assertTrue(newPrice(10, -8) instanceof DefPrice8);
		assertTrue(newPrice(10, -9) instanceof DefPrice9);

	}

	@Test
	public void TestNewPrice3() {

		assertEquals(newPrice(10, 00), newPrice(10.0));
		assertEquals(newPrice(10, -1), newPrice(1.0));
		assertEquals(newPrice(10, -2), newPrice(0.1));
		assertEquals(newPrice(10, -3), newPrice(0.01));
		assertEquals(newPrice(10, -4), newPrice(0.001));
		assertEquals(newPrice(10, -5), newPrice(0.0001));
		assertEquals(newPrice(10, -6), newPrice(0.00001));
		assertEquals(newPrice(10, -7), newPrice(0.000001));
		assertEquals(newPrice(10, -8), newPrice(0.0000001));

		assertEquals(newPrice(1, 00), newPrice(1.0));
		assertEquals(newPrice(1, -1), newPrice(0.1));
		assertEquals(newPrice(1, -2), newPrice(0.01));
		assertEquals(newPrice(1, -3), newPrice(0.001));
		assertEquals(newPrice(1, -4), newPrice(0.0001));
		assertEquals(newPrice(1, -5), newPrice(0.00001));
		assertEquals(newPrice(1, -6), newPrice(0.000001));
		assertEquals(newPrice(1, -7), newPrice(0.0000001));

		assertEquals(newPrice(123456, 00), newPrice(123456.0));
		assertEquals(newPrice(123456, -1), newPrice(12345.60));
		assertEquals(newPrice(123456, -2), newPrice(1234.560));

		assertEquals(newPrice(243456, -4), newPrice(24.3456));

		assertEquals(newPrice(1681235, -3), newPrice(1681.235));

		assertEquals(newPrice(16812356, -4), newPrice(1681.2356));

		assertEquals(newPrice(168123567, -5), newPrice(1681.23567));

		assertEquals(newPrice(1681235678, -6), newPrice(1681.235678));

		assertEquals(newPrice(16812356789L, -7), newPrice(1681.2356789));

		assertEquals(newPrice(7168123567892L, -8), newPrice(71681.23567892));

		assertEquals(newPrice(71681235678923L, -9), newPrice(71681.235678923));

	}

}
