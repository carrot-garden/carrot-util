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
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

abstract class ScaledValueDes<T extends ScaledDecimal<T, F>, F extends ScaledDecimal<F, ?>>
		extends StdDeserializer<T> {

	static Logger log = LoggerFactory.getLogger(ScaledValueDes.class);

	protected ScaledValueDes(final Class<T> klaz) {
		super(klaz);
	}

	@Override
	public T deserialize(final JsonParser parser,
			final DeserializationContext context) throws IOException,
			JsonProcessingException {

		try {

			final JsonToken token = parser.getCurrentToken();
			if (token != JsonToken.VALUE_STRING) {
				log.debug("token != JsonToken.VALUE_STRING");
				return null;
			}

			final String text = parser.getText();
			if (text == null) {
				log.debug("text == null");
				return null;
			}

			final int idx = text.indexOf(ValueModule.EXP);
			if (idx < 0) {
				log.debug("idx < 0");
				return null;
			}

			final String textMantissa = text.substring(0, idx);
			final String textExponent = text.substring(idx + 1);

			final long mantissa = Long.parseLong(textMantissa);
			final int exponent = Integer.parseInt(textExponent);

			return newValue(mantissa, exponent);

		} catch (final Exception e) {

			log.debug("", e);
			return null;

		}

	}

	protected abstract T newValue(long mantissa, int exponent);

}
