/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.util.test.size;

import static com.carrotgarden.util.test.size.JavaSize.*;

class test_Class1 {

	final static int SIZE = OBJECT_SIZE + 4 + 1 + REFERENCE_SIZE + 7;

	@SuppressWarnings("unused")
	private int a1; // 4

	public byte a2; // 1

	protected char a3[]; // 1 ref

	static byte a33; // 0

}
