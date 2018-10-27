import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ThreadPartTest {


    @Test
    public void nodeConstructorAndDoNodeMapTest(){

        int[][] array = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                         {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                         {0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                         {1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                         {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                         {0, 1, 0, 1, 0, 1, 0, 1, 0, 1}};


        ThreadPart threadPart = new ThreadPart(array, 4, 8, 1);


        int[][]result = {{0, 0, 0, 0, 0},
                         {1, 1, 1, 1, 1},
                         {0, 1, 1, 1, 1},
                         {1, 0, 0, 0, 0},
                         {1, 0, 1, 0, 1},
                         {0, 1, 0, 1, 0}};

        Assert.assertTrue(Arrays.deepEquals(threadPart.getMap(), result));
    }

}
