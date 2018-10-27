package evgeny.task5;

import java.util.Date;

public class Test {


    public static void main(String[] args) {

        Parking parking = new Parking(5, 100);


//        Date start = new Date(2018, 5, 10, 12, 30);
//        Date finish = new Date(2018, 5, 10, 15, 40);
//        boolean sucsess = parking.in(23, start.getTime());
//
//        int price = parking.getParkingCost(23, finish.getTime());
//
//        System.out.println(price);


//        Date start = new Date(2018, 5, 10, 21, 45);
//        Date finish = new Date(2018, 5, 11, 8, 25);
//        boolean sucsess = parking.in(23, start.getTime());
//
//        int price = parking.getParkingCost(23, finish.getTime());
//
//        System.out.println(price);


        Date start = new Date(2018, 5, 10, 23, 00);
        Date finish = new Date(2018, 5, 11, 6, 00);
        boolean sucsess = parking.in(23, start.getTime());

        int price = 0;
        try {
            price = parking.getParkingCost(10, finish.getTime());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



        System.out.println(price);

        sucsess = parking.in(23, start.getTime());

        System.out.println(sucsess);

        sucsess = parking.in(24, start.getTime());
        System.out.println(sucsess);
        sucsess = parking.in(25, start.getTime());
        System.out.println(sucsess);
        sucsess = parking.in(26, start.getTime());
        System.out.println(sucsess);
        sucsess = parking.in(27, start.getTime());
        System.out.println(sucsess);
        sucsess = parking.in(28, start.getTime());
        System.out.println(sucsess);
        sucsess = parking.in(29, start.getTime());
        System.out.println(sucsess);




    }


}
