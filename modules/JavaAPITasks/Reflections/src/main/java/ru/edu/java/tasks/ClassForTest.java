package ru.edu.java.tasks;

import java.util.Enumeration;

public class ClassForTest extends Reflections.SecretClass
        implements Comparable<ClassForTest>, Enumeration<ClassForTest> {
    public ClassForTest(String text) {
        super(text);
    }

    protected void printSomeThing() throws NullPointerException{
        System.out.println("I printed something!");
    }

    protected int sumSomething(int...numbers) throws IllegalAccessException{
        int sum = 0;
        for(int i : numbers)
            sum+=i;
        return sum;
    }

    protected int multSomething(int...numbers) throws ArithmeticException{
        int mul = 1;
        for(int i : numbers)
            mul*=i;
        return mul;
    }

    @Override
    public int compareTo(ClassForTest o) {
        return 0;
    }

    @Override
    public boolean hasMoreElements() {
        return false;
    }

    @Override
    public ClassForTest nextElement() {
        return null;
    }
}

