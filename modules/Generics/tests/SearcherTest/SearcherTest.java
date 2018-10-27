package SearcherTest;

import org.junit.Assert;
import org.junit.Test;
import searcher.Searcher;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearcherTest {


    public static class ClassA{
        protected int valueA;

        public ClassA(int value) {
            this.valueA = value;
        }

        public int getValue() {
            return valueA;
        }

        public void setValue(int valueA) {
            this.valueA = valueA;
        }
    }

    public static class ClassB1 extends ClassA{
        protected int valueB1;
        public ClassB1(int valueA, int valueB1) {
            super(valueA);
        }

        public int getValueB1() {
            return valueB1;
        }

        public void setValueB1(int valueB1) {
            this.valueB1 = valueB1;
        }
    }

    public static class ClassB2 extends ClassA {
        protected int valueB2;
        public ClassB2(int valueA, int valueB2) {
            super(valueA);
        }

        public int getValueB2() {
            return valueB2;
        }

        public void setValueB1(int valueB1) {
            this.valueB2 = valueB1;
        }
    }

    public static class ClassC extends ClassB1{
        protected int valueC;

        public ClassC(int valueA, int valueB1, int valueC) {
            super(valueA, valueB1);
            this.valueC = valueC;
        }

        public int getValueC() {
            return valueC;
        }

        public void setValueC(int valueC) {
            this.valueC = valueC;
        }
    }


    @Test
    public void findFirstTest(){

        List<ClassA> list = new ArrayList<>();
        list.add(new ClassA(10));
        list.add(new ClassB1(11, 12));
        list.add(new ClassB2(13,14));
        list.add(new ClassC(15,16, 17));

        Predicate<ClassA> predicate = x -> x.getValue() == 13;
        int position = Searcher.findFirst(list, 0, list.size() -1 , predicate);
        Assert.assertEquals(position, 2);
    }


    @Test
    public void notFoundTest(){
        List<ClassA> list = new ArrayList<>();
        list.add(new ClassA(10));
        list.add(new ClassB1(11, 12));
        list.add(new ClassB2(13,14));
        list.add(new ClassC(15,16, 17));

        Predicate<ClassA> predicate = x -> x.getValue() == 0;
        int result = Searcher.findFirst(list, 0, list.size()-1, predicate);
        Assert.assertEquals(result, -1);
    }
}
