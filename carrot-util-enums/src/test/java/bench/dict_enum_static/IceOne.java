package bench.dict_enum_static;

public interface IceOne {

	<V> V get(FieldOne<V> field);

	<V> void set(FieldOne<V> field, V value);

}
