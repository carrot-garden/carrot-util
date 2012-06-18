package bench.dict_enum_static;

public interface IceTwo extends IceOne {

	<V> V get(FieldTwo<V> field);

	<V> void set(FieldTwo<V> field, V value);

}
