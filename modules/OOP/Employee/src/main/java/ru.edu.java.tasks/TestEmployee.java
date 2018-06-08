package ru.edu.java.tasks;

public class TestEmployee {

    public static void main(String[] args) {

        Employee employee = new EmployeeImpl();
        System.out.println(employee.getSalary());

        employee.setFirstName("Ivan");
        employee.setLastName("Ivanov");

        System.out.println("Name: " + employee.getFirstName() + " Lastname: " + employee.getLastName());
        System.out.println("Full name: " + employee.getFullName());


        Employee manager = new EmployeeImpl("Sidor", "Sidorov", 3500);

        System.out.println("Manager for " + employee.getFullName() + " - " + employee.getManagerName());
        System.out.println("And now:");
        employee.setManager(manager);
        System.out.println("Manager for " + employee.getFullName() + " - " + employee.getManagerName());




        Employee level3 = new EmployeeImpl("Manager", "3 level", 1000);

        manager.setManager(level3);

        Employee level4 = new EmployeeImpl("Manager", "4 level", 1000);

        level3.setManager(level4);

        Employee level5 = new EmployeeImpl("Manager", "5 level", 1000);

        level4.setManager(level5);



        System.out.println(employee.getTopManager().getFullName());


    }

}
