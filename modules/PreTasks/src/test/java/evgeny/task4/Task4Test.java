package evgeny.task4;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Task4Test {



    @Test
    public void intersection() throws Exception {

        long[] pattern = {202, 404, 175};
        long[]a = {202, 448, 404, 501, 501, 175};
        long[]b = {175,202,404,404,175};
        assertEquals(true, Arrays.equals(pattern, Task4.intersection(a,b)));

    }

}