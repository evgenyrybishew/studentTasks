package evgeny.task3;

import org.junit.Test;

import static org.junit.Assert.*;

public class Task3Test {




    @Test
    public void test1() throws Exception {
        assertEquals(8, Task3.binaryToDec("1000"));
    }


    @Test
    public void test2() throws Exception {

        System.out.println(Long.MAX_VALUE);

        assertEquals(100500, Task3.binaryToDec("11000100010010100"));
    }



}