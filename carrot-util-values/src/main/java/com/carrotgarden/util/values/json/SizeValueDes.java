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

import com.carrotgarden.util.values.api.IntegerValue;
import com.carrotgarden.util.values.provider.ValueBuilder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

class SizeValueDes extends StdDeserializer<IntegerValue> {

	static Logger log = LoggerFactory.getLogger(SizeValueDes.class);

	protected SizeValueDes(final Class<IntegerValue> klaz) {
		super(klaz);
	}

	@Override
	public IntegerValue deserialize(final JsonParser parser,
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

			final long size = Long.parseLong(text);

			return ValueBuilder.newInteger(size);

		} catch (final Exception e) {

			log.debug("", e);
			return null;

		}

	}

}
