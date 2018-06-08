package ru.edu.java.tasks;

public class ArrayVectorImp implements ArrayVector {

    private double[] array;

    public ArrayVectorImp() {
        this.array = new double [0];
    }

    public ArrayVectorImp(double[] array){
        this.array = array;
    }



    /**
     * Задает все элементы вектора (определяет длину вектора).
     * Передаваемый массив не клонируется.
     * @param elements Не равен null
     */
    @Override
    public void set(double... elements) {

        this.array = elements;

    }



    /**
     * Возвращает все элементы вектора. Массив не клонируется.
     */
    @Override
    public double[] get() {
        return this.array;
    }


    /**
     * Возвращает копию вектора (такую, изменение элементов
     *  в которой не приводит к изменению элементов данного вектора).<br/>
     */
    @Override
    public ArrayVector clone() {
        return new ArrayVectorImp(this.array.clone());
    }

    /**
     * Возвращает число элементов вектора.
     */
    @Override
    public int getSize() {
        return this.array.length;
    }


    /**
     * Изменяет элемент по индексу.
     * @param index В случае выхода индекса за пределы массива:<br/>
     *  а) если index<0, ничего не происходит;<br/>
     *  б) если index>=0, размер массива увеличивается так, чтобы index стал последним.
     */
    @Override
    public void set(int index, double value) {

        if(index < 0)
            return;

        if(index > this.array.length){

            double[] temp = new double[index + 1];

            for(int i = 0; i < this.array.length; i++)
                temp[i] = this.array[i];

            this.array = temp;
        }

        this.array[index] = value;
    }



    /**
     * Возвращает элемент по индексу.
     * @param index В случае выхода индекса за пределы массива
     * генерирует ArrayIndexOutOfBoundsException
     */

    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {

        if(index < 0 || index > this.array.length)
            throw new ArrayIndexOutOfBoundsException();

        return array[index];
    }


    /**
     * Возвращает максимальный элемент вектора.
     */
    @Override
    public double getMax() {

        double max = Double.MIN_VALUE;

        for(int i = 0; i < this.array.length; i++){
            if(Double.compare(this.array[i], max) > 0)
                max = array[i];
        }
        return max;
    }


    /**
     * Возвращает минимальный элемент вектора.
     */
    @Override
    public double getMin() {

        double min = Double.MAX_VALUE;

        for(int i = 0; i < array.length; i++){
            if(Double.compare(this.array[i], min) < 0)
                min = this.array[i];
        }

        return min;
    }


    /**
     * Сортирует элементы вектора в порядке возрастания.
     */
    @Override
    public void sortAscending() {

        quikSort(this.array, 0, this.array.length - 1);

    }


    private void quikSort(double[] ar, int start, int end){

        double splitter = ar[(start + end) / 2];

        int i = start;
        int j = end;


        while (i <= j){


            while (Double.compare(splitter, ar[i]) > 0){
                i++;
            }
            while (Double.compare(ar[j], splitter) > 0){
                j--;
            }
            if(i > j)
                break;

            double temp = ar[i];
            ar[i] = ar[j];
            ar[j] = temp;

            i++;
            j--;
        }

        if(j > start)
            quikSort(ar, start, j);
        if(i < end)
            quikSort(ar, i, end);

    }

    /**
     * Умножает вектор на число.<br/>
     * @param factor
     */
    @Override
    public void mult(double factor) {

        for(int i = 0; i < array.length; i++){
            this.array[i] *= factor;
        }

    }

    /**
     * Складывает вектор с другим вектором, результат запоминает в элементах данного вектора.<br/>
     * @param anotherVector Не равен null
     * @return Ссылка на себя (результат сложения)
     */
    @Override
    public ArrayVector sum(ArrayVector anotherVector) {

        int size = Integer.min(this.array.length, anotherVector.getSize());

        for(int i = 0; i < size; i++){
            this.array[i] += anotherVector.get(i);


        }
        return this;
    }



    /**
     * Возвращает скалярное произведение двух векторов.<br/>
     * Если векторы имеют разный размер, последние элементы большего вектора не учитываются.
     * @param anotherVector Не равен null
     */
    @Override
    public double scalarMult(ArrayVector anotherVector) {

        double scalarMult = 0;
        int size = Integer.min(this.array.length, anotherVector.getSize());

        for(int i = 0; i < size; i++){
            scalarMult += (this.array[i] * anotherVector.get(i));

        }
        return scalarMult;
    }


    /**
     * Возвращает евклидову норму вектора (длину вектора
     *  в n-мерном евклидовом пространстве, n={@link #getSize()}).
     */
    @Override
    public double getNorm() {

        return Math.sqrt(this.scalarMult(this));
    }
}
