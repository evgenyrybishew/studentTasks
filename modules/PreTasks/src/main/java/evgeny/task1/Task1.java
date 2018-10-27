

//1. Написать программу, которая выводит:
//        (+)1.1 Все аргументы, которые ей передали.
//        (+)1.2 Все системные переменные.
//        (+)1.3 Все переменные окружения.


package evgeny.task1;

import java.util.Map;

public class Task1 {

    public static void main(String[] args) {

        String arg = "Program arguments: ";
        for(String s : args){
            arg += s + " ";
        }
        System.out.println(arg);

        String envPropString = "Environment variables: ";

        Map<String, String> envProp = System.getenv();

        int i = 1;
        for(Map.Entry<String, String> entry : envProp.entrySet()){
            envPropString += i++ + ". " + entry.getKey() + "\n";
        }

        System.out.println(envPropString);


        String jvmPropString = "JVM variables: ";

        Map<Object, Object> jvmProp = System.getProperties();


        i = 1;
        for(Map.Entry<Object, Object> entry : jvmProp.entrySet()){
            jvmPropString += i++ + ". " + entry.getKey() + "\n";
        }

        System.out.println(jvmPropString);


    }

}
