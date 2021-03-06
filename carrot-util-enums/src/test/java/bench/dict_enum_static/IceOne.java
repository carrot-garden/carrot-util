/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.dict_enum_static;

public interface IceOne {

	<V> V get(FieldOne<V> field);

	<V> void set(FieldOne<V> field, V value);

}
