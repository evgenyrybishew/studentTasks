package ru.edu.java.tasks;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.fail;

public class ComplexNumberTest {
    @Test
    public void setGetTest() {
        ComplexNumber number = new ComplexNumberImp();
        number.set(3, 5);
        Assert.assertEquals(3, number.getRe(), 0.1);
        Assert.assertEquals(5, number.getIm(), 0.1);
    }


    @Test
    public void isRealTest() {
        ComplexNumber number = new ComplexNumberImp(3, 5);
        Assert.assertFalse(number.isReal());
        number = new ComplexNumberImp(5, 0);
        Assert.assertTrue(number.isReal());
    }


    @Test
    public void setStringTest() {
        ComplexNumber number = new ComplexNumberImp();
        String[] str = {"-5+2i", "1+i", "+4-i", "i", "-3i", "3", "+5-4i", "4i"};

        double[][] assertArray = {{-5, 2}, {1, 1}, {4, -1}, {0, 1}, {0, -3}, {3, 0}, {5, -4}, {0, 4}};

        for (int i = 0; i < str.length; i++) {
            number.set(str[i]);
            Assert.assertEquals(assertArray[i][0], number.getRe(), 0.1);
            Assert.assertEquals(assertArray[i][1], number.getIm(), 0.1);
        }
    }


    @Test
    public void setStringTest2() {
        ComplexNumber number = new ComplexNumberImp();
        try {
            String str = "1+2*i";
            number.set(str);
            fail();
        } catch (NumberFormatException e) {
            Assert.assertNotNull(e);
        }
    }

    @Test
    public void setStringTest3() {
        ComplexNumber number = new ComplexNumberImp();
        try {
            String str = "2+2";
            number.set(str);
            fail();
        } catch (NumberFormatException e) {
            Assert.assertNotNull(e);
        }
    }


    @Test
    public void setStringTest4() {
        ComplexNumber number = new ComplexNumberImp();
        try {
            String str = "j";
            number.set(str);
            fail();
        } catch (NumberFormatException e) {
            Assert.assertNotNull(e);
        }
    }

    @Test
    public void copyTest() {
        ComplexNumber number = new ComplexNumberImp();
        ComplexNumber copy = number.copy();
        Assert.assertFalse(copy == number);
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        ComplexNumber number = new ComplexNumberImp();
        ComplexNumber copy = number.clone();
        Assert.assertFalse(copy == number);

    }

    @Test
    public void compareToTest() {

        ComplexNumber a = new ComplexNumberImp("+4-i");
        ComplexNumber b = new ComplexNumberImp("1+i");

        Assert.assertTrue(a.compareTo(b) > 0);
        Assert.assertFalse(b.compareTo(a) > 0);
        Assert.assertFalse(a.compareTo(b) == 0);
    }

    @Test
    public void sortTest() {
        ComplexNumber number = new ComplexNumberImp();
        String[] str = {"-5+2i", "1+i", "+4-i", "i", "-3i", "3"};
        String[] str1 = {"i", "1+i", "-3i", "3", "+4-i", "-5+2i"};
        ComplexNumber[] array = new ComplexNumberImp[str.length];
        ComplexNumber[] array1 = new ComplexNumberImp[str1.length];

        for (int i = 0; i < array.length; i++)
            array[i] = new ComplexNumberImp(str[i]);
        for (int i = 0; i < array1.length; i++)
            array1[i] = new ComplexNumberImp(str1[i]);

        number.sort(array);

        for (int i = 0; i < array1.length; i++) {
            Assert.assertEquals(array[i].getRe(), array1[i].getRe(), 0.1);
            Assert.assertEquals(array[i].getIm(), array1[i].getIm(), 0.1);

        }

    }

    @Test
    public void negate() throws NoSuchFieldException, IllegalAccessException {
        ComplexNumber number = new ComplexNumberImp(3, 5);
        number.negate();

        Field reField = number.getClass().getDeclaredField("re");
        Field imField = number.getClass().getDeclaredField("im");
        reField.setAccessible(true);
        imField.setAccessible(true);

        double re = (double) reField.get(number);
        double im = (double) imField.get(number);

        Assert.assertEquals(re, -3, 0.1);
        Assert.assertEquals(im, -5, 0.1);
    }

    @Test
    public void add() throws NoSuchFieldException, IllegalAccessException {
        ComplexNumber number = new ComplexNumberImp(-5, 2);
        ComplexNumber other = new ComplexNumberImp(10, -3);
        number.add(other);

        Field reField = number.getClass().getDeclaredField("re");
        Field imField = number.getClass().getDeclaredField("im");
        reField.setAccessible(true);
        imField.setAccessible(true);

        double re = (double) reField.get(number);
        double im = (double) imField.get(number);

        Assert.assertEquals(re, 5, 0.1);
        Assert.assertEquals(im, -1, 0.1);

    }

    @Test
    public void multiply() throws NoSuchFieldException, IllegalAccessException {

        ComplexNumber number = new ComplexNumberImp(5, 4);
        ComplexNumber other = new ComplexNumberImp(3, 7);
        number.multiply(other);

        Field reField = number.getClass().getDeclaredField("re");
        Field imField = number.getClass().getDeclaredField("im");
        reField.setAccessible(true);
        imField.setAccessible(true);

        double re = (double) reField.get(number);
        double im = (double) imField.get(number);

        Assert.assertEquals(re, -13, 0.1);
        Assert.assertEquals(im, 47, 0.1);
    }
}
