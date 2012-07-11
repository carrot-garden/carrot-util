/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.values.api;

import com.carrotgarden.util.anno.NotMutable;
import com.carrotgarden.util.values.lang.ScaledDecimal;

/** should be used for trade price only */
@NotMutable
public interface PriceValue extends Value<PriceValue>,
		ScaledDecimal<PriceValue, DecimalValue> {

}
