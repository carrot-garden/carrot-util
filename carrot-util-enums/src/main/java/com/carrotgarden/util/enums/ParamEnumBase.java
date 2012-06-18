package com.carrotgarden.util.enums;

public class ParamEnumBase<V, T extends ParamEnum<V, T>> extends
		DictEnumBase<V> implements ParamEnum<V, T> {

	protected ParamEnumBase() {
		super("");
		this.value = null;
	}

	protected ParamEnumBase(final String comment, final V value) {
		super(comment);
		this.value = value;
	}

	private final V value;

	@Override
	public final V value() {
		return value;
	}

	@Override
	public final boolean is(final ParamEnum<?, ?> that) {
		/** assuming same class loader */
		return this == that;
	}

	@Override
	public final boolean isIn(final ParamEnum<?, ?>... thatArray) {
		if (thatArray == null) {
			return false;
		}
		for (final ParamEnum<?, ?> that : thatArray) {
			if (is(that)) {
				return true;
			}
		}
		return false;
	}

}
