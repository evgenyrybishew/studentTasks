package PECS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {

        CarStorage carStorage = new CarStorage();
        VehicleStorage vehicleStorage = new VehicleStorage();

        Map<Integer, Car> carMap = new HashMap<>();

        for(int i = 0; i < 10; i++)
            carMap.put(i, new Car("Label" + i, Math.random() * 1000 + 1, "Owner" + i));

        carStorage.putAll(carMap);


        vehicleStorage.putAll(carMap);

        vehicleStorage.put(11, new Car("CAR", 9999, "My"));

        carStorage.getAll((Predicate<Vehicle>) car -> car.getLabel().equals("Label3"));

    }
}
