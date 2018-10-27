package propertyCounter;

import java.util.List;
import java.util.function.Predicate;

public class PropertyCounter {
    public static<T> int count(List<T> list, Predicate<T> property){
        int counter = 0;
        for(T item : list)
            if(property.test(item))
                counter++;
        return counter;
    }
}
