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

import com.carrotgarden.util.values.api.TimeValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

class TimeValueSer extends StdSerializer<TimeValue> {

	static Logger log = LoggerFactory.getLogger(TimeValueSer.class);

	protected TimeValueSer(final Class<TimeValue> klaz) {
		super(klaz);
	}

	@Override
	public void serialize(final TimeValue value, final JsonGenerator jgen,
			final SerializerProvider provider) throws IOException,
			JsonProcessingException {

		jgen.writeString(Long.toString(value.asMillisUTC()));

	}

}
