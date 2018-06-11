package ru.edu.java.tasks;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeImplTest {

    @Test
    public void increaseAndGetSalaryTest() {
        Employee employee = new EmployeeImpl();
        employee.increaseSalary(100);
        Assert.assertEquals(1100, employee.getSalary());
    }

    @Test
    public void setGetNameTest() {
        Employee employee = new EmployeeImpl();
        employee.setFirstName("John");
        employee.setLastName("Smith");

        Assert.assertTrue(employee.getFirstName().equals("John"));
        Assert.assertTrue(employee.getLastName().equals("Smith"));
        Assert.assertTrue(employee.getFullName().equals("John Smith"));
    }

    @Test
    public void setGetManager() {

        Employee manager = new EmployeeImpl("John", "Smith", 2000);
        Employee employee = new EmployeeImpl("Tom", "Talor", 1000);
        employee.setManager(manager);

        Assert.assertTrue(employee.getManagerName().equals("John Smith"));
    }

    @Test
    public void getTopManager() {
        Employee level1 = new EmployeeImpl("level", "one", 1000);
        Employee level2 = new EmployeeImpl("level", "two", 1000, level1);
        Employee level3 = new EmployeeImpl("level", "three", 3000, level2);
        Employee level4 = new EmployeeImpl("level", "three", 3000, level3);
        Assert.assertTrue(level1 == level4.getTopManager());
    }
}