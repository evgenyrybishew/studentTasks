//5. Разработать класс автостоянка:
//        5.1 В конструктор класса передаётся вместимость автостоянки и стоимость часа.
//        5.2 У класса автостоянка есть 2 операции:
//        Вьехать -в аргументы id машины(Long) и время вьезда(Long) Возвращается boolean если можно вьехать.
//        Вьехать можно, если на автостоянке есть свободные места, а также если машина ещё не вьезжала.
//        Выехать - id машины(Long) и время выезда(Long) Возвращается стоимость стоянки(количество часов * стоимость 1 часа)
//        5.3 Доработать класс, что бы стоимость возвращалаьс с учётом ночных часов:
//        Время 0 это 0 часов первых суток(24 это 0 часов вторых суток и т.п)
//        Стоимость стоянки с 23 до 6 утра умножается на 2.
//        Необходимо реализовать алгоритм, а также закодировать тесты, проверяющие корректную реализацию.

package evgeny.task5;

import java.util.*;

public class Parking {

    private int capacity;
    private int price;
    private int free;


    private class Log{

        private Date _in;
        private Date _out;
        private long carId;


        public Log(long inTime, long carId) {
            this.carId = carId;
            this._in = new Date(inTime);
        }

        public void out(long outTime){
            this._out = new Date(outTime);
        }

        public Date get_in() {
            return _in;
        }

        public void set_in(Date _in) {
            this._in = _in;
        }

        public Date get_out() {
            return _out;
        }

        public void set_out(Date _out) {
            this._out = _out;
        }

        public long getCarId() {
            return carId;
        }

        public void setCarId(long carId) {
            this.carId = carId;
        }
    }





    private Map<Long, Log> log;


    public Parking(int capacity, int price) {
        this.capacity = capacity;
        this.price = price;
        this.log = new HashMap<Long, Log>();
        this.free = capacity;
    }





    public boolean in(long carId, long time){

        if(this.free == 0 || log.containsKey(carId)){
            return false;
        }
        this.free--;
        log.put(carId, new Log(time, carId));
        return true;
    }


    public int getParkingCost(long carId, long time) throws Exception{


        Log temp = log.get(carId);
        if(temp == null){
            throw new Exception("Error!!! The car did not leave");
        }
        temp.out(time);


        Date start = temp.get_in();

        Date finish = temp.get_out();

        int cof = 0;

        for (long i = start.getTime(); ; i += 3600000){

            Date date = new Date(i);

            System.out.println(date.getDate() + " " + date.getHours()+ " " + date.getMinutes());

            if(date.getTime() >= finish.getTime()){
                break;
            }

            if(date.getHours() < 6 || date.getHours() >= 23){
                cof += 2;
            }
            else{
                cof++;
            }

        }

        this.free++;
        return this.price * cof;
    }



}
