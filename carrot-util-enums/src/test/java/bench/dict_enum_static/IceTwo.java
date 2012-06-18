/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_enum_static;

public interface IceTwo extends IceOne {

	<V> V get(FieldTwo<V> field);

	<V> void set(FieldTwo<V> field, V value);

}
