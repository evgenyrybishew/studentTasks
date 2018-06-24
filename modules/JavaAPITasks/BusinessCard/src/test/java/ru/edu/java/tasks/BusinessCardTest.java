package ru.edu.java.tasks;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

public class BusinessCardTest {

    //TODO: ✔ Переименовал файлы ресурсов
    @Test
    public void getBusinessCardTest() throws Exception {
        //TODO: ✔ рефактор Field
        //TODO: ✔ заменить temp на более подходящее именование сущности
        BusinessCard card = new BusinessCardImp();
        Scanner in = new Scanner(new File("modules/JavaAPITasks/BusinessCard/src/test/resources/test.txt"));
        card.getBusinessCard(in);

        Field fieldName = card.getClass().getDeclaredField("name");
        fieldName.setAccessible(true);
        String name = (String) fieldName.get(card);
        Assert.assertTrue(name.equals("Chuck"));

        Field fieldLastName = card.getClass().getDeclaredField("lastname");
        fieldLastName.setAccessible(true);
        String lastname = (String) fieldLastName.get(card);
        Assert.assertTrue(lastname.equals("Norris"));

        Field fieldDepartment = card.getClass().getDeclaredField("department");
        fieldDepartment.setAccessible(true);
        String department = (String) fieldDepartment.get(card);
        Assert.assertTrue(department.equals("DSI"));

        Field fieldBirthDate = card.getClass().getDeclaredField("birthDate");
        fieldBirthDate.setAccessible(true);
        Calendar bd = (Calendar) fieldBirthDate.get(card);

        Assert.assertEquals(bd.get(Calendar.DAY_OF_MONTH), 10);
        Assert.assertEquals(bd.get(Calendar.MONTH), 4);
        Assert.assertEquals(bd.get(Calendar.YEAR), 1940);

        Field fieldGender = card.getClass().getDeclaredField("gender");
        fieldGender.setAccessible(true);
        String gender = (String) fieldGender.get(card);
        Assert.assertTrue(gender.equals("M"));

        Field fieldSalary = card.getClass().getDeclaredField("salary");
        fieldSalary.setAccessible(true);
        int salary = (int) fieldSalary.get(card);
        Assert.assertEquals(1000, salary);

        Field fieldTel = card.getClass().getDeclaredField("tel");
        fieldTel.setAccessible(true);
        String tel = (String) fieldTel.get(card);
        Assert.assertTrue(tel.equals("1234567890"));
    }


    @Test
    public void getBusinessCardTestWrongTel() throws Exception {
        BusinessCard card = new BusinessCardImp();
        Scanner in = new Scanner(new File("modules/JavaAPITasks/BusinessCard/src/test/resources/wrongPhone.txt"));
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
        Scanner in = new Scanner(new File("modules/JavaAPITasks/BusinessCard/src/test/resources/wrongSalary.txt"));
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
        Scanner in = new Scanner(new File("modules/JavaAPITasks/BusinessCard/src/test/resources/wrongGenger.txt"));
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
        Scanner in = new Scanner(new File("modules/JavaAPITasks/BusinessCard/src/test/resources/inputMismatchException.txt"));
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
