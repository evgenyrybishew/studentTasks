package ru.edu.java.tasks;

public class EmployeeImpl implements Employee {


    private String firsname;
    private String lastname;
    private int salary;

    private Employee manager;

    public EmployeeImpl(){
        this.firsname = "undefine";
        this.lastname = "undefine";
        this.salary = 1000;

        this.manager = null;

    }

    public EmployeeImpl(String firsname, String lastname, int salary) {
        this.firsname = firsname;
        this.lastname = lastname;
        this.salary = salary;
    }

    public EmployeeImpl(String firsname, String lastname, int salary, Employee manager) {
        this.firsname = firsname;
        this.lastname = lastname;
        this.salary = salary;
        this.manager = manager;
    }


    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public void increaseSalary(int value) {
        this.salary += value;
    }

    @Override
    public String getFirstName() {
        return this.firsname;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firsname = firstName;
    }


    @Override
    public String getLastName() {
        return lastname;
    }


    @Override
    public void setLastName(String lastName) {
        this.lastname = lastName;
    }


    @Override
    public String getFullName() {
        return this.firsname + " " + this.lastname;
    }

    @Override
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public String getManagerName() {

        if(this.manager == null)
            return "No manager";
        return this.manager.getFullName();
    }


    @Override
    public Employee getTopManager() {

        if(this.manager == null)
            return this;

        return this.manager.getTopManager();

    }
}
