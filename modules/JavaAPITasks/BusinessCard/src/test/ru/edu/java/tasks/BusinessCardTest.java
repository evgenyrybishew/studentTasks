package ru.edu.java.tasks;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

public class BusinessCardTest {

    @Test
    public void getBusinessCardTest() throws Exception {

        BusinessCard card = new BusinessCardImp();
        Scanner in = new Scanner(new File("modules/JavaAPITasks/BusinessCard/src/test/resources/test.txt"));
        card.getBusinessCard(in);

        Field field = card.getClass().getDeclaredField("name");
        field.setAccessible(true);
        String temp = (String) field.get(card);
        Assert.assertTrue(temp.equals("Chuck"));

        field = card.getClass().getDeclaredField("lastname");
        field.setAccessible(true);
        temp = (String) field.get(card);
        Assert.assertTrue(temp.equals("Norris"));

        field = card.getClass().getDeclaredField("department");
        field.setAccessible(true);
        temp = (String) field.get(card);
        Assert.assertTrue(temp.equals("DSI"));

        field = card.getClass().getDeclaredField("birthDate");
        field.setAccessible(true);
        Calendar bd = (Calendar) field.get(card);

        Assert.assertEquals(bd.get(Calendar.DAY_OF_MONTH), 10);
        Assert.assertEquals(bd.get(Calendar.MONTH), 4);
        Assert.assertEquals(bd.get(Calendar.YEAR), 1940);

        field = card.getClass().getDeclaredField("gender");
        field.setAccessible(true);
        temp = (String) field.get(card);
        Assert.assertTrue(temp.equals("M"));

        field = card.getClass().getDeclaredField("salary");
        field.setAccessible(true);
        int salary = (int) field.get(card);
        Assert.assertEquals(1000, salary);

        field = card.getClass().getDeclaredField("tel");
        field.setAccessible(true);
        temp = (String) field.get(card);
        Assert.assertTrue(temp.equals("1234567890"));
    }


    @Test
    public void getBusinessCardTestWrongTel() throws Exception {
        BusinessCard card = new BusinessCardImp();
        Scanner in = new Scanner(new File("modules/JavaAPITasks/BusinessCard/src/test/resources/wrong1.txt"));
        boolean wrong = false;
        try {
            card.getBusinessCard(in);
        } catch (NoSuchElementException e) {
            wrong = true;
        }
        Assert.assertTrue(wrong);
    }

    @Test
    public void getBusinessCardTestWrongSalary() throws Exception {
        BusinessCard card = new BusinessCardImp();
        Scanner in = new Scanner(new File("modules/JavaAPITasks/BusinessCard/src/test/resources/wrong2.txt"));
        boolean wrong = false;
        try {
            card.getBusinessCard(in);
        } catch (NoSuchElementException e) {
            wrong = true;
        }
        Assert.assertTrue(wrong);
    }

    @Test
    public void getBusinessCardTestWrongGender() throws Exception {
        BusinessCard card = new BusinessCardImp();
        Scanner in = new Scanner(new File("modules/JavaAPITasks/BusinessCard/src/test/resources/wrong3.txt"));
        boolean wrong = false;
        try {
            card.getBusinessCard(in);
        } catch (NoSuchElementException e) {
            wrong = true;
        }
        Assert.assertTrue(wrong);
    }

    @Test
    public void getBusinessCardTestnIputMismatchException() throws Exception {
        BusinessCard card = new BusinessCardImp();
        Scanner in = new Scanner(new File("modules/JavaAPITasks/BusinessCard/src/test/resources/wrong4.txt"));
        boolean wrong = false;
        try {
            card.getBusinessCard(in);
        } catch (InputMismatchException e) {
            wrong = true;
        }
        Assert.assertTrue(wrong);
    }


    @Test
    public void getEmployeeTest() {
        BusinessCard card = new BusinessCardImp("Chuck", "Norris", "DSI",
                new GregorianCalendar(1940, 4, 10),
                "M", 1000, "1234567890");

        Assert.assertTrue(card.getEmployee().equals("Employee: Chuck Norris\n" +
                "Salary: 1000\n" +
                "Age: 78\n" +
                "Gender: Male\n" +
                "Phone: +7 123-456-78-90"));
    }

    @Test
    public void getDepartmentTest() {
        BusinessCard card = new BusinessCardImp("Chuck", "Norris", "DSI",
                new GregorianCalendar(1940, 4, 10),
                "M", 1000, "1234567890");

        Assert.assertTrue(card.getDepartment().equals("DSI"));
    }

    @Test
    public void getSalaryTest() {
        BusinessCard card = new BusinessCardImp("Chuck", "Norris", "DSI",
                new GregorianCalendar(1940, 4, 10),
                "M", 1000, "1234567890");

        Assert.assertEquals(1000, card.getSalary());
    }

    @Test
    public void getAgeTest() {
        BusinessCard card = new BusinessCardImp("Chuck", "Norris", "DSI",
                new GregorianCalendar(1940, 4, 10),
                "M", 1000, "1234567890");
        Assert.assertEquals(78, card.getAge());


    }

    @Test
    public void getGenderTest() {
        BusinessCard card = new BusinessCardImp("Chuck", "Norris", "DSI",
                new GregorianCalendar(1940, 4, 10),
                "M", 1000, "1234567890");

        Assert.assertTrue(card.getGender().equals("Male"));
    }

    @Test
    public void getPhoneNumberTest() {

        BusinessCard card = new BusinessCardImp("Chuck", "Norris", "DSI",
                new GregorianCalendar(1940, 4, 10),
                "M", 1000, "1234567890");

        Assert.assertTrue(card.getPhoneNumber().equals("+7 123-456-78-90"));

    }

}
