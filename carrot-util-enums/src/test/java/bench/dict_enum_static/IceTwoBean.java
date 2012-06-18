package bench.dict_enum_static;

import com.carrotgarden.util.enums.DictEnumBase;

public class IceTwoBean extends IceOneBean implements IceTwo {

	public IceTwoBean() {
		super(DictEnumBase.sizeOf(FieldTwo.class));
	}

	@Override
	public <V> V get(final FieldTwo<V> field) {
		return get(field.ordinal());
	}

	@Override
	public <V> void set(final FieldTwo<V> field, final V value) {
		set(field.ordinal(), value);
	}

}
