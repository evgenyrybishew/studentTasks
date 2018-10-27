package searcher;

import java.util.List;
import java.util.function.Predicate;

public class Searcher {
    public static<T>int findFirst(List<? extends T> list, int begin, int end, Predicate<? super T> p){

        for(int i = begin; i <= end; i++)
            if(p.test(list.get(i)))
                return i;
        return -1;
    }
}
