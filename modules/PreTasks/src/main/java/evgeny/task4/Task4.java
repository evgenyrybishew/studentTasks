//4.На вход методу подаётся два массива long. Вернуть элементы, которые встречаются
//        в обоих массивах public long[] intersection(long[] arr1, long[] arr2);

package evgeny.task4;


import java.util.HashSet;

public class Task4 {



    public static long[] intersection(long[] arr1, long[] arr2){

        int size = Math.max(arr1.length, arr2.length);
        HashSet<Long> temp = new HashSet<Long>(size);


        for(int i = 0; i < arr1.length; i++){

            for(int j = 0; j < arr2.length; j++){

                if(arr1[i] == arr2[j]){
                    temp.add(arr1[i]);
                }
            }
        }

        long[] result = new long[temp.size()];

        int counter = 0;

        for(long i : temp)
            result[counter++] = i;

        return result;


    }



}
