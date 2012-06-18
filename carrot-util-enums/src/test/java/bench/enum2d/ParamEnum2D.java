/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.enum2d;

import com.carrotgarden.util.enums.ParamEnum;


public interface ParamEnum2D<V, T extends ParamEnum2D<V, T>> extends
		ParamEnum<V, T> {

	int row();

	int col();

}
