/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carrotgarden.util.values.lang.ScaledDecimal;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

class ScaledValueSer<T extends ScaledDecimal<T, F>, F extends ScaledDecimal<F, ?>>
		extends StdSerializer<T> {

	static Logger log = LoggerFactory.getLogger(ScaledValueSer.class);

	protected ScaledValueSer(final Class<T> klaz) {
		super(klaz);
	}

	@Override
	public void serialize(final T value, final JsonGenerator jgen,
			final SerializerProvider provider) throws IOException,
			JsonProcessingException {

		final StringBuilder text = new StringBuilder(64);
		text.append(value.mantissa());
		text.append(ValueModule.EXP);
		text.append(value.exponent());
		jgen.writeString(text.toString());

	}

}
