package swap;

import org.junit.Assert;
import org.junit.Test;

public class SwapTest {

    @Test
    public void StringSwapTest() {
        String[] strings = {"null", "first", "second", "third"};
        Swap.doSwap(strings, 1, 2);

        Assert.assertTrue(strings[1].equals("second"));
        Assert.assertTrue(strings[2].equals("first"));


        Assert.assertTrue(strings[0].equals("null"));
        Assert.assertTrue(strings[3].equals("third"));
        //TODO swap одинаковых
    }

    @Test
    public void swapForEqual(){
        String[] strings = {"Hello", "World", "World", "Hello"};
        Swap.doSwap(strings, 0, 3);

        Assert.assertTrue(strings[0].equals("Hello"));
        Assert.assertTrue(strings[1].equals("World"));
        Assert.assertTrue(strings[2].equals("World"));
        Assert.assertTrue(strings[3].equals("Hello"));
    }


    @Test
    public void IntegerSwapTest(){
        Integer[] integers = {0,1,2,3};

        Swap.doSwap(integers, 0,3);
        Assert.assertTrue(integers[0] == 3);
        Assert.assertTrue(integers[3] == 0);
    }


    @Test
    public void ArrayIndexOutOfBounds(){
        Integer[] integers = {0,1,2,3};
        try{
            Swap.doSwap(integers, 3, 6 );
        }catch (ArrayIndexOutOfBoundsException e){
            Assert.assertTrue(true);
        }
    }
}
