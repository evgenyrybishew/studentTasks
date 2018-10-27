//3.На вход подаётся строка, содержащее число в бинарной системе счичсления.
//        Нужно вернуть десятичное число(long) public long binaryToDec(String binary);

        package evgeny.task3;

public class Task3 {


    public static long binaryToDec(String binary) throws Exception{

        if(itsNotBinary(binary)){
            throw new Exception(binary + " it's not binary");
        }

        long pow = 0, result = 0;
        for(int i = binary.length() - 1; i >= 0; i--, pow++){

            if(binary.charAt(i) == '1'){
                result += Math.pow(2, pow);
            }
        }

        return result;




    }

    private static boolean itsNotBinary(String binary){


        try {

            Long.parseLong(binary);

            for (char i = '2'; i <= '9'; i++){
                if(binary.contains(i + "")) {
                    throw new Exception();
                }
            }

            return false;



        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            return true;

        }catch (Exception e){
            System.out.println(binary + " it's not binary");
            return true;
        }


    }



}



