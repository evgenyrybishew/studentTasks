package PECS;

import java.util.List;
import java.util.Map;

public interface Storage<K, V> {

    void put(K key, V value);

    V get(K key);

    void putAll(Map<K, ? extends V> map);

    Map<K, V> getAll(List<K> keys);

    Map<K, V> getAll(Predicate<? super V> predicate);

}
