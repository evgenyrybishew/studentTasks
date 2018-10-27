package evgeny.task5;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ParkingTest {

    Parking parking;
    Date start, finish;

    @Before
    public void init(){

        int capacity = 5;
        int price = 100;
        parking = new Parking(capacity, price);
        start = new Date(2018, 5, 10, 21, 45);
        finish = new Date(2018, 5, 11, 8, 25);
    }



    @Test
    public void in() throws Exception {

        assertEquals(true, parking.in(23, start.getTime()));


    }

    @Test
    public void getParkingCost() throws Exception {

        assertEquals(1800, parking.getParkingCost(23, finish.getTime()));

    }

}