/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

class Jason {

	private static Logger log = LoggerFactory.getLogger(Jason.class);

	private static final ObjectMapper mapper = new ObjectMapper();

	static {

		applyMapperPolicy(mapper);

	}

	public static void applyMapperPolicy(final ObjectMapper mapper) {

		/* READ: */

		// must annotate fields with @JsonProperty explicitly
		// mapper.getDeserializationConfig().disable(
		// DeserializationConfig.Feature.AUTO_DETECT_FIELDS);
		// must annotate fields with @JsonProperty explicitly
		// mapper.getDeserializationConfig().disable(
		// DeserializationConfig.Feature.AUTO_DETECT_SETTERS);
		// must annotate fields with @JsonCerator explicitly
		// mapper.getDeserializationConfig().disable(
		// DeserializationConfig.Feature.AUTO_DETECT_CREATORS);
		// make all unknown into optional
		// mapper.getDeserializationConfig().disable(
		// DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);

		/* WRITE: */

		// will introduce tabs
		// mapper.getSerializationConfig().enable(
		// SerializationConfig.Feature.INDENT_OUTPUT);

		// must annotate with @JsonProperty explicitly
		// mapper.getSerializationConfig().disable(
		// SerializationConfig.Feature.AUTO_DETECT_FIELDS);
		// must annotate with @JsonProperty explicitly
		// mapper.getSerializationConfig().disable(
		// SerializationConfig.Feature.AUTO_DETECT_GETTERS);
		// must annotate with @JsonProperty explicitly
		// mapper.getSerializationConfig().disable(
		// SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS);
		// no more empty fields
		// mapper.getSerializationConfig().disable(
		// SerializationConfig.Feature.WRITE_NULL_PROPERTIES);

	}

	public static void registerModule(final Module module) {
		mapper.registerModule(module);
	}

	public static <T> T fromText(final String text, final Class<T> klaz) {
		try {
			final T value = mapper.readValue(text, klaz);
			return value;
		} catch (final Exception e) {
			log.error("", e);
			return null;
		}
	}

	public static String intoText(final Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (final Exception e) {
			log.error("", e);
			return null;
		}
	}

}
