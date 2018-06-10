package ru.edu.java.tasks;

public class ArrayVectorImp implements ArrayVector {

    private double[] array;

    public ArrayVectorImp() {
        this.array = new double[0];
    }

    public ArrayVectorImp(double[] array) {
        this.array = array;
    }


    @Override
    public void set(double... elements) {
        this.array = elements;
    }


    @Override
    public double[] get() {
        return this.array;
    }


    @Override
    public ArrayVector clone() {
        return new ArrayVectorImp(this.array.clone());
    }


    @Override
    public int getSize() {
        return this.array.length;
    }


    @Override
    public void set(int index, double value) {

        if (index < 0)
            return;

        if (index > this.array.length) {

            double[] temp = new double[index + 1];

            for (int i = 0; i < this.array.length; i++)
                temp[i] = this.array[i];

            this.array = temp;
        }

        this.array[index] = value;
    }


    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        return array[index];
    }


    @Override
    public double getMax() {

        double max = Double.MIN_VALUE;

        for (int i = 0; i < this.array.length; i++) {
            if (Double.compare(this.array[i], max) > 0)
                max = array[i];
        }
        return max;
    }


    @Override
    public double getMin() {

        double min = Double.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (Double.compare(this.array[i], min) < 0)
                min = this.array[i];
        }

        return min;
    }


    @Override
    public void sortAscending() {

        int optimalSize = 50;
        if(this.array.length <= optimalSize)
            bubbleSort();
        else
            quikSort(this.array, 0, this.array.length - 1);
    }



    private void bubbleSort(){
        for(int i = 0; i < array.length; i++)
            for(int j = 0; j < array.length; j++)
                if(Double.compare(array[i], array[j]) > 0){
                    double temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
    }


    private void quikSort(double[] ar, int start, int end) {
        double splitter = ar[(start + end) / 2];
        int i = start;
        int j = end;

        while (i <= j) {
            while (Double.compare(splitter, ar[i]) > 0)
                i++;

            while (Double.compare(ar[j], splitter) > 0)
                j--;

            if (i > j)
                break;

            double temp = ar[i];
            ar[i] = ar[j];
            ar[j] = temp;

            i++;
            j--;
        }
        if (j > start)
            quikSort(ar, start, j);
        if (i < end)
            quikSort(ar, i, end);
    }


    @Override
    public void mult(double factor) {

        for (int i = 0; i < array.length; i++) {
            this.array[i] *= factor;
        }
    }


    @Override
    public ArrayVector sum(ArrayVector anotherVector) {

        int size = Integer.min(this.array.length, anotherVector.getSize());

        for (int i = 0; i < size; i++) {
            this.array[i] += anotherVector.get(i);

        }
        return this;
    }


    @Override
    public double scalarMult(ArrayVector anotherVector) {
        double scalarMult = 0;
        int size = Integer.min(this.array.length, anotherVector.getSize());

        for (int i = 0; i < size; i++) {
            scalarMult += (this.array[i] * anotherVector.get(i));

        }
        return scalarMult;
    }


    @Override
    public double getNorm() {
        return Math.sqrt(this.scalarMult(this));
    }
}
