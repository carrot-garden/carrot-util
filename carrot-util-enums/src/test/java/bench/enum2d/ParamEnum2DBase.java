/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.enum2d;


import bench.enum1d.ParamEnum1DBase;

public abstract class ParamEnum2DBase<V, T extends ParamEnum2D<V, T>> extends
		ParamEnum1DBase<V, T> implements ParamEnum2D<V, T> {

	//

	protected final int row;

	@Override
	public final int row() {
		return row;
	}

	protected final int col;

	@Override
	public final int col() {
		return col;
	}

	protected ParamEnum2DBase(final ParamEnum1DBase<?, ?>[] values,
			final int ordinal, final V defVal, final int row, final int col) {
		super(values, ordinal, defVal);
		this.row = row;
		this.col = col;
	}

}
