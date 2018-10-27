package swap;

public class Swap {
    public static<T> void doSwap (T[] array, int i, int j) throws ArrayIndexOutOfBoundsException{
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;

    }
}
