package PECS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class CarStorage implements Storage<Integer, Car> {

    Map<Integer, Car> map = new HashMap<>();

    @Override
    public void put(Integer key, Car value) {
        map.put(key, value);
    }

    @Override
    public Car get(Integer key) {
        return map.get(key);
    }



    @Override
    public void putAll(Map<Integer, ? extends Car> map) {
        this.map.putAll(map);
    }

    @Override
    public Map<Integer, Car> getAll(List<Integer> keys) {
        Map<Integer, Car> temp = new HashMap<>();

        for (Map.Entry<Integer, Car> item : map.entrySet()) {
            if (keys.contains(item.getKey()))
                map.put(item.getKey(), item.getValue());
        }
        return temp;

    }

    @Override
    public Map<Integer, Car> getAll(PECS.Predicate<? super Car> predicate) {
        return null;
    }

}
