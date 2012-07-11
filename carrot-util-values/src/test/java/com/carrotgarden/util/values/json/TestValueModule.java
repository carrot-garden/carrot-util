/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.json;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carrotgarden.util.values.provider.ValueBuilder;

public class TestValueModule {

	private static final Logger log = LoggerFactory
			.getLogger(TestValueModule.class);

	@Test
	public void testBuild() {

		Jason.registerModule(ValueModule.build());

		//

		final Dummy source = new Dummy();

		source.decimal = ValueBuilder.newDecimal(234234, -3);
		source.price = ValueBuilder.newPrice(123, -2);
		source.size = ValueBuilder.newInteger(1234567890123L);
		source.text = ValueBuilder.newText("hello string");
		source.time = ValueBuilder.newTime(System.currentTimeMillis());
		log.debug("source: {}", source);

		final String json = Jason.intoText(source);

		final Dummy target = Jason.fromText(json, Dummy.class);
		log.debug("target: {}", target);

		//

		assertEquals(source.decimal, target.decimal);
		assertEquals(source.price, target.price);
		assertEquals(source.size, target.size);
		assertEquals(source.text, target.text);
		assertEquals(source.time, target.time);

		//

	}

}
