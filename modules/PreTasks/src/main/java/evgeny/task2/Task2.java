//2. Написать программу, которая выводит "Hello, World!" количество раз переданное:
//        (+)2.2 Если передан параметр вида JAVA_COUNT=XXX, где XXX число раз, то используется оно.
//        (-)2.3 Если передана системная настройка вида JAVA_COUNT=XXX, где XXX число раз, то используется оно.
//        (-)2.4 Если определена переменная окружения вида JAVA_COUNT=XXX, где XXX число раз, то используется оно.
//        (-)2.5 Если определена переменная окружения вида JAVA_PROPERTIES_FILE=XXX, где XXX это путь к существующему файлу,
//        то загружаем настройки оттуда и пытаемся получить настройку оттуда.
//        (-)2.6 Если ничего не задано, выводим сообщение о вариантах исопльзования программы и завершаем.


package evgeny.task2;

public class Task2 {

    public static void main(String[] args) {

        if(args.length > 1 || args.length == 0){
            errorMessage();
            return;
        }

        int count = 0;
        try {

            if(!args[0].split("=")[0].equals("JAVA_COUNT")){
                throw new Exception();
            }

            count = Integer.parseInt(args[0].split("=")[1]);

        }catch (Exception e){
            errorMessage();
        }

        for(int i = 0; i < count; i++){
            System.out.println("Hello World!");
        }





    }


    public static void errorMessage(){

        System.out.println("Программа выводит \"Hello, World!\":\n" +
                "1 параметр вида JAVA_COUNT=XXX, где XXX число раз.\n" +
                "2 системная настройка вида JAVA_COUNT=XXX, где XXX число раз.\n" +
                "3 переменная окружения вида JAVA_COUNT=XXX, где XXX число раз.\n" +
                "4 переменная окружения вида JAVA_PROPERTIES_FILE=XXX, где XXX это путь к существующему файлу");

    }


}
