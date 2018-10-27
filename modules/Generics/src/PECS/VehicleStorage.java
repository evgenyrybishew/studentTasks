package PECS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class VehicleStorage implements Storage<Integer, Vehicle> {
    Map<Integer, Vehicle> map = new HashMap<>();

    @Override
    public void put(Integer key, Vehicle value) {
        map.put(key, value);
    }

    @Override
    public Vehicle get(Integer key) {
        return map.get(key);
    }




    @Override
    public void putAll(Map<Integer, ? extends Vehicle> map) {
        this.map.putAll(map);
    }

    @Override
    public Map<Integer, Vehicle> getAll(List<Integer> keys) {
        Map<Integer, Vehicle> temp = new HashMap<>();

        for (Map.Entry<Integer, Vehicle> item : map.entrySet()) {
            if (keys.contains(item.getKey()))
                map.put(item.getKey(), item.getValue());
        }
        return temp;
    }



    @Override
    public Map<Integer, Vehicle> getAll(PECS.Predicate<? super Vehicle> predicate){
        return null;
    }
}
