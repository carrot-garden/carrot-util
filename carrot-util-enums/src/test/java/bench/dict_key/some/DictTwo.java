/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_key.some;

import com.carrotgarden.util.enums.DictEnum;

public class DictTwo<V> extends DictOne<V> {

	public static final DictEnum<String> TEST = NEW("string");

	public static final DictEnum<Integer> TWO = NEW("integer");

}
