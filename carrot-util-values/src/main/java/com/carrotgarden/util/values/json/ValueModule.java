/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.json;

import com.carrotgarden.util.values.api.DecimalValue;
import com.carrotgarden.util.values.api.PriceValue;
import com.carrotgarden.util.values.api.IntegerValue;
import com.carrotgarden.util.values.api.TextValue;
import com.carrotgarden.util.values.api.TimeValue;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ValueModule {

	public static final String COLON = ":";
	public static final String EXP = "E";
	public static final String AT = "@";

	public static Module build() {

		final Version version = new Version(1, 0, 0, null, null, null);

		final SimpleModule module = new SimpleModule("values", version);

		//

		final Class<DecimalValue> klazDecimal = DecimalValue.class;
		module.addSerializer(new DecimalValueSer(klazDecimal));
		module.addDeserializer(klazDecimal, new DecimalValueDes(klazDecimal));

		//

		final Class<PriceValue> klazPrice = PriceValue.class;
		module.addSerializer(new PriceValueSer(klazPrice));
		module.addDeserializer(klazPrice, new PriceValueDes(klazPrice));

		//

		final Class<IntegerValue> klazSize = IntegerValue.class;
		module.addSerializer(new SizeValueSer(klazSize));
		module.addDeserializer(klazSize, new SizeValueDes(klazSize));

		//

		final Class<TextValue> klazText = TextValue.class;
		module.addSerializer(new TextValueSer(klazText));
		module.addDeserializer(klazText, new TextValueDes(klazText));

		//

		final Class<TimeValue> klazTime = TimeValue.class;
		module.addSerializer(new TimeValueSer(klazTime));
		module.addDeserializer(klazTime, new TimeValueDes(klazTime));

		//

		return module;

	}

}
