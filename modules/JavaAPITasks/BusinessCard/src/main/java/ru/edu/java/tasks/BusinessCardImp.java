package ru.edu.java.tasks;

import java.util.*;

public class BusinessCardImp implements BusinessCard {


    private String name;
    private String lastname;
    private String department;
    private Calendar birthDate;
    private String gender;
    private int salary;
    private String tel;

    public BusinessCardImp(){}

    public BusinessCardImp(String name, String lastname, String department, Calendar birthDate, String gender,
                           int salary, String tel) {
        this.name = name;
        this.lastname = lastname;
        this.department = department;
        this.birthDate = birthDate;
        this.gender = gender;
        this.salary = salary;
        this.tel = tel;
    }

    @Override
    public BusinessCard getBusinessCard(Scanner scanner) throws NoSuchElementException, InputMismatchException {


        final int FIELDS = 7;
        String[] data = scanner.nextLine().split(";");
        if (data.length == FIELDS) {
            this.name = data[0];
            this.lastname = data[1];
            this.department = data[2];

            String[] date = data[3].split("-");
            birthDate = new GregorianCalendar(Integer.valueOf(date[2]),
                    Integer.valueOf(date[1]), Integer.valueOf(date[0]));

            if (data[4].equals("M") || data[4].equals("F"))
                this.gender = data[4];
            else
                throw new InputMismatchException("Invalid value for gender!");

            if(!isDigit(data[5]))
                throw new InputMismatchException("Invalid value for salary!");
            int checkSalary = Integer.valueOf(data[5]);

            if(checkSalary < 100 || checkSalary > 100000)
                throw new InputMismatchException("Invalid value for salary!");
            this.salary = checkSalary;

            if(data[6].length() != 10 || !isDigit(data[6]))
                throw new InputMismatchException("Invalid value for phone!");
            this.tel = data[6];
            return  this;
        }
        throw new NoSuchElementException();
    }


    private boolean isDigit(String input){

        try{
            Integer.valueOf(input);
            return true;
        }catch (Exception e){
            return  false;
        }
    }


    @Override
    public String getEmployee() {
        return "Employee: " + this.name + " " + this.lastname + "\n"+
                "Salary: " + this.salary + "\n"+
                "Age: " + this.getAge() + "\n" +
                "Gender: " + this.getGender() + "\n" +
                "Phone: " + this.getPhoneNumber();

    }

    @Override
    public String getDepartment() {
        return this.department;
    }

    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public int getAge() {
        return Calendar.getInstance().get(Calendar.YEAR) - this.birthDate.get(Calendar.YEAR);
    }

    @Override
    public String getGender() {
        return this.gender.equals("M") ? "Male" : "Female";
    }

    @Override
    public String getPhoneNumber() {
        return "+7 " + this.tel.substring(0, 3) + "-" + this.tel.substring(3,6) + "-" + this.tel.substring(6,8) + "-" +
                tel.substring(8);
    }

}
