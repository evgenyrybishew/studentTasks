package ru.edu.java.tasks;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;


public class ArrayVectorImpTest {

    @Test
    public void setGetTest() {
        ArrayVector vector = new ArrayVectorImp();
        vector.set(3.14, 2.71, 9.8);

        Assert.assertTrue(Double.compare(vector.get(0), 3.14) == 0);
        Assert.assertTrue(Double.compare(vector.get(1), 2.71) == 0);
        Assert.assertTrue(Double.compare(vector.get(2), 9.8) == 0);
    }

    @Test
    public void getArrayTest() {
        ArrayVector vector = new ArrayVectorImp();
        vector.set(3.14, 2.71, 9.8);
        double[] array = {3.14, 2.71, 9.8};
        Assert.assertTrue(Arrays.equals(array, vector.get()));
    }


    @Test
    public void cloneTest() {
        double[] array = {3.14, 2.71, 9.8};
        ArrayVector vector = new ArrayVectorImp(array);
        ArrayVector clone = vector.clone();
        Assert.assertFalse(vector == clone);
    }

    @Test
    public void getSizeTest() {
        double[] array = {3.14, 2.71, 9.8};
        ArrayVector vector = new ArrayVectorImp(array);
        Assert.assertEquals(3, vector.getSize());
    }

    @Test
    public void setTest() throws NoSuchFieldException, IllegalAccessException {
        double[] array = {3.14, 2.71, 9.8};
        ArrayVector vector = new ArrayVectorImp(array);
        vector.set(1, 0);
        Field field = vector.getClass().getDeclaredField("array");
        field.setAccessible(true);
        array = (double[]) field.get(vector);

        Assert.assertEquals(0, array[1], 0.1);

        vector.set(100, 100);
        array = (double[]) field.get(vector);
        Assert.assertEquals(array[100], 100, 0.1);
    }

    @Test
    public void getTest() {
        double[] array = {3.14, 2.71, 9.8};
        ArrayVector vector = new ArrayVectorImp(array);

        Assert.assertEquals(vector.get(0), 3.14, 0.01);
        boolean error = false;
        try {
            vector.get(100);
        } catch (ArrayIndexOutOfBoundsException e) {
            error = true;
        } finally {
            Assert.assertTrue(error);
        }
    }


    @Test
    public void getMaxTest() {
        double[] array = {3.14, 2.71, 9.8, 9.9, -4.1, 23, 0};
        ArrayVector vector = new ArrayVectorImp(array);
        Assert.assertEquals(23, vector.getMax(), 0.01);
    }

    @Test
    public void getMinTest() {
        double[] array = {3.14, 2.71, 9.8, 9.9, -4.1, 23, 0};
        ArrayVector vector = new ArrayVectorImp(array);
        Assert.assertEquals(-4.1, vector.getMin(), 0.01);
    }

    @Test
    public void sortAscendingTest() throws NoSuchFieldException, IllegalAccessException {

        Random rnd = new Random();
        double[] array = new double[70];
        for (int i = 0; i < array.length; i++)
            array[i] = rnd.nextDouble();

        ArrayVector vector = new ArrayVectorImp(array);
        vector.sortAscending();
        Arrays.sort(array);

        Field field = vector.getClass().getDeclaredField("array");
        field.setAccessible(true);
        double[] fromVector = (double[]) field.get(vector);

        Assert.assertTrue(Arrays.equals(fromVector, array));


        array = new double[10];
        for (int i = 0; i < array.length; i++)
            array[i] = rnd.nextDouble();
        vector = new ArrayVectorImp(array);
        vector.sortAscending();
        Arrays.sort(array);
        fromVector = (double[]) field.get(vector);

        Assert.assertTrue(Arrays.equals(fromVector, array));
    }

    @Test
    public void mult() throws NoSuchFieldException, IllegalAccessException {
        double[] array = {3.14, 2.71, 9.8};

        ArrayVector vector = new ArrayVectorImp(array);
        vector.mult(2);

        for (int i = 0; i < array.length; i++)
            array[i] *= 2;

        Field field = vector.getClass().getDeclaredField("array");
        field.setAccessible(true);
        double[] fromVector = (double[]) field.get(vector);
        Assert.assertTrue(Arrays.equals(fromVector, array));
    }

    @Test
    public void sum() throws NoSuchFieldException, IllegalAccessException {
        double[] array1 = {4, 5, 7, 2, 4, 6, 3, 2};
        ArrayVector vector1 = new ArrayVectorImp(array1);

        double[] array2 = {4, 6, 4, 3, 5, 67, 8, 5, 5, 3, 7};
        ArrayVector vector2 = new ArrayVectorImp(array2);

        double[] assertArray = new double[array1.length];

        for (int i = 0; i < assertArray.length; i++)
            assertArray[i] = array1[i] + array2[i];

        vector1.sum(vector2);

        Field field = vector1.getClass().getDeclaredField("array");
        field.setAccessible(true);
        double[] fromVector = (double[]) field.get(vector1);

        Assert.assertTrue(Arrays.equals(assertArray, fromVector));
    }

    @Test
    public void scalarMult() {
        double[] array1 = {4, 5, 7};
        ArrayVector arrayVector = new ArrayVectorImp(array1);
        double[] array2 = {4, 6, 4, 3, 5};
        ArrayVector other = new ArrayVectorImp(array2);
        Assert.assertEquals(74, arrayVector.scalarMult(other), 0.01);
    }

    @Test
    public void getNorm() {
        double[] array1 = {4, 5, 7};
        ArrayVector arrayVector = new ArrayVectorImp(array1);
        Assert.assertEquals(9.48, arrayVector.getNorm(), 0.01);
    }
}