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
import com.carrotgarden.util.values.api.SizeValue;
import com.carrotgarden.util.values.api.TextValue;
import com.carrotgarden.util.values.api.TimeValue;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Dummy {

	@JsonProperty("decimal")
	DecimalValue decimal;

	@JsonProperty("price")
	PriceValue price;

	@JsonProperty("size")
	SizeValue size;

	@JsonProperty("text")
	TextValue text;

	@JsonProperty("time")
	TimeValue time;

	@Override
	public String toString() {
		return Jason.intoText(this);
	}

}
