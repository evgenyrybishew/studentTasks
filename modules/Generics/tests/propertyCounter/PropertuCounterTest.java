package propertyCounter;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PropertuCounterTest {
    @Test
    public void countTestIsEven(){
        Predicate<Integer> isEven = x -> x % 2==0;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 100; i++)
            list.add(i);
        Assert.assertEquals(PropertyCounter.count(list, isEven), 50);
    }

    @Test
    public void counterTestIsContent(){
        String content = "test";
        Predicate<String>isContent = x -> x.contains(content);
        List<String>list = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            if(i % 3 == 0){
                list.add("string conteins " + content);
                continue;
            }
            list.add("simple string");
        }
        Assert.assertEquals(PropertyCounter.count(list, isContent), 34);
    }

    @Test
    public void counterTestNegative(){
        String contain = "test";
        Predicate<String > predicate = x -> x.contains(contain);
        List<String>strings = new ArrayList<>();
        for(int i = 0; i < 10; i++)
            strings.add("string" + i);

        Assert.assertEquals(PropertyCounter.count(strings, predicate), 0);
    }
}
