package ru.edu.java.tasks;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComplexNumberImpTest {


    ComplexNumber number = new ComplexNumberImp("-5+2i");



    @Test
    public void getReTest() {
        Assert.assertEquals(-5, number.getRe(), 0.1);
    }

    @Test
    public void getImTest() {
        Assert.assertEquals(2, number.getIm(), 0.1);
    }

    @Test
    public void isRealTest() {
        Assert.assertFalse(number.isReal());
    }

    @Test
    public void setTest() {
        number.set(4, 5);
        Assert.assertEquals(4, number.getRe(), 0.01);
        Assert.assertEquals(5, number.getIm(), 0.01);
        number.set(4, 0);
        Assert.assertTrue(number.isReal());


    }

    @Test
    public void set1() {
        String[] str = {"-5+2i", "1+i", "+4-i", "i", "-3i", "3", "+5-4i", "4i"};

        double[][] assertArray = {{-5, 2}, {1, 1}, {4, -1}, {0, 1}, {0, -3}, {3, 0}, {5, -4}, {0, 4}};

        for(int i = 0; i < str.length; i++){
            number.set(str[i]);
            Assert.assertEquals(assertArray[i][0], number.getRe(), 0.1);
            Assert.assertEquals(assertArray[i][1], number.getIm(), 0.1);
        }
    }


    @Test
    public void set2() {
        try {
            String str = "1+2*i";
            number.set(str);
            fail();
        }catch (NumberFormatException e){
            Assert.assertNotNull(e);
        }
    }

    @Test
    public void set3() {
        try {
            String str = "2+2";
            number.set(str);
            fail();
        }catch (NumberFormatException e){
            Assert.assertNotNull(e);
        }
    }


    @Test
    public void set4() {
        try {
            String str = "j";
            number.set(str);
            fail();
        }catch (NumberFormatException e){
            Assert.assertNotNull(e);
        }
    }

    @Test
    public void copy() {
        ComplexNumber copy = number.copy();
        Assert.assertFalse(copy == number);
    }

    @Test
    public void clone1() throws CloneNotSupportedException {

            ComplexNumber copy = number.clone();
            Assert.assertFalse(copy == number);

    }

    @Test
    public void compareTo() {

        ComplexNumber a = new ComplexNumberImp("+4-i");
        ComplexNumber b = new ComplexNumberImp("1+i");

        Assert.assertTrue(a.compareTo(b) > 0);
        Assert.assertFalse(b.compareTo(a) > 0);
        Assert.assertFalse(a.compareTo(b) == 0);
    }

    @Test
    public void sort() {
        String[] str = {"-5+2i", "1+i", "+4-i", "i", "-3i", "3"};
        String[] str1 = {"i", "1+i",  "-3i", "3",  "+4-i","-5+2i"};
        ComplexNumber[] array = new ComplexNumberImp[str.length];
        ComplexNumber[] array1 = new ComplexNumberImp[str1.length];

        for(int i = 0; i < array.length; i++)
            array[i] = new ComplexNumberImp(str[i]);
        for(int i = 0; i < array1.length; i++)
            array1[i] = new ComplexNumberImp(str1[i]);

        number.sort(array);

       for(int i = 0; i < array1.length; i++){
           Assert.assertEquals(array[i].getRe(), array1[i].getRe(), 0.1);
           Assert.assertEquals(array[i].getIm(), array1[i].getIm(), 0.1);

       }

    }

    @Test
    public void negate() {
        number.negate();
        Assert.assertTrue(number.compareTo(new ComplexNumberImp(5, -2)) == 0);
    }

    @Test
    public void add() {

        ComplexNumber sum = number.add(new ComplexNumberImp(10, -3));
        Assert.assertTrue(sum.compareTo(new ComplexNumberImp(5, -1)) == 0);

        sum = new ComplexNumberImp(17, 24).add(new ComplexNumberImp(-31, -7));
        Assert.assertTrue(sum.compareTo(new ComplexNumberImp(14, 17)) == 0);

    }

    @Test
    public void multiply() {

        ComplexNumber mult = new ComplexNumberImp(5,4).multiply(new ComplexNumberImp(3, 7));
        Assert.assertTrue(mult.compareTo(new ComplexNumberImp(-13, 47)) == 0);
        mult = new ComplexNumberImp(-3,0).multiply(new ComplexNumberImp(3, -2));
        Assert.assertTrue(mult.compareTo(new ComplexNumberImp(-9, 6)) == 0);
    }
}