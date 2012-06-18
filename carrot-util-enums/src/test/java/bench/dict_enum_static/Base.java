package bench.dict_enum_static;

public class Base implements Freezer {

	protected final Object[] array;

	protected Base(final int size) {
		this.array = new Object[size];
	}

	protected Base(final Object[] array) {
		this.array = array;
	}

	@SuppressWarnings("unchecked")
	protected <V> V get(final int index) {
		return (V) array[index];
	}

	protected <V> void set(final int index, final V value) {
		array[index] = value;
	}

	@Override
	public Object freeze() {
		return null;
	}

	@Override
	public boolean isFrozen() {
		return false;
	}

}
