package ru.edu.java.tasks;

public class Test {


    public static void main(String[] args) {


        ArrayVector original = new ArrayVectorImp();


        //void set(double... elements);
        original.set(4,5,8,54,6,6);


        //double[] get();
        double[] getArray = original.get();
        getArray[3] = 999;
        System.out.println("GetArray array, element 3 = " + getArray[3]);
        System.out.println("Original array, element 3 = " + original.get()[3]);

        System.out.println(new String("_____________________________"));



        //public ArrayVector clone()
        ArrayVectorImp clone = (ArrayVectorImp) original.clone();
        clone.get()[2] = -100;
        System.out.println("Clone array, element 2 = " + clone.get()[2]);
        System.out.println("Original array, element 2 = " + original.get()[2]);

        System.out.println(new String("_____________________________"));

        //public int getSize()
        System.out.println("Original array size is " + original.getSize());


        //public void set(int index, double value)
        System.out.println(new String("_____________________________"));
        System.out.println("Before");
        System.out.println("Original array, element 10 = " + "none");
        original.set(10, 404);
        System.out.println("After");
        System.out.println("Original array, element 2 = " + original.get()[10]);


        System.out.println(new String("_____________________________"));
        //double get(int index)

        try {
            System.out.println("Original array, element " + original.getSize() + 2 + "  = " + original.get(original.getSize() + 2));
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Original array, element 10 = " + original.get(10));

        //public double getMax()
        System.out.println(new String("_____________________________"));
        System.out.println("Max element in original array: " + original.getMax());



        //public double getMin()
        System.out.println(new String("_____________________________"));
        System.out.println("Min element in original array: " + original.getMin());


        //public void sortAscending()
        System.out.println(new String("_____________________________"));
        System.out.println("Sort:");
        System.out.println("Before");
        for(int i = 0; i < original.getSize(); i++)
            System.out.print(original.get(i) + " ");

        System.out.println("After");
        original.sortAscending();
        for(int i = 0; i < original.getSize(); i++)
            System.out.print(original.get(i) + " ");

        //public void mult(double factor)
        System.out.println(new String("\n_____________________________"));
        System.out.println("Vector mult");
        System.out.println("Before");
        for(int i = 0; i < original.getSize(); i++)
            System.out.print(original.get(i) + " ");
        System.out.println("\nAfter");
        original.mult(2);
        for(int i = 0; i < original.getSize(); i++)
            System.out.print(original.get(i) + " ");



        //public ArrayVector sum(ArrayVector anotherVector)

        System.out.println(new String("\n_____________________________"));
        System.out.println("Vector sum");
        ArrayVector anotherVector = new ArrayVectorImp();
        anotherVector.set(6,8,9,4,2,5,7,9,2,57);
        System.out.println("AnotherVector: ");
        for(int i = 0; i < anotherVector.getSize(); i++)
            System.out.print(anotherVector.get(i) + " ");
        System.out.println("\nOriginal: ");
        for(int i = 0; i < original.getSize(); i++)
            System.out.print(original.get(i) + " ");

        System.out.println("\nSum: ");
        ArrayVector sum =  original.sum(anotherVector);

        for(int i = 0; i < sum.getSize(); i++)
            System.out.print(sum.get(i) + " ");




        //public double scalarMult(ArrayVector anotherVector)
        System.out.println(new String("\n_____________________________"));
        System.out.println("Scalar mult");

        System.out.println("AnotherVector: ");
        for(int i = 0; i < anotherVector.getSize(); i++)
            System.out.print(anotherVector.get(i) + " ");
        System.out.println("\nOriginal: ");
        for(int i = 0; i < original.getSize(); i++)
            System.out.print(original.get(i) + " ");

        System.out.println("\n" + original.scalarMult(anotherVector));

        //double getNorm();
        System.out.println(new String("\n_____________________________"));
        System.out.println("Norm for original array: " + original.getNorm());

















    }

}
