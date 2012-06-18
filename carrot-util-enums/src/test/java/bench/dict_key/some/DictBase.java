/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_key.some;

import com.carrotgarden.util.enums.DictEnum;

public class DictBase<V> extends DictKey<V> {

	public static final DictEnum<String> X1 = NEW("string");

	public static final DictEnum<Integer> X2 = NEW("integer");

	public static final DictEnum<Float> X3 = NEW("float");

}
