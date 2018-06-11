package ru.edu.java.tasks;

import java.util.Arrays;

public class ComplexNumberImp implements ComplexNumber {

    private double re;
    private double im;


    public ComplexNumberImp(){
        this.re = 0;
        this.im = 0;
    }


    public ComplexNumberImp(double re, double im){
        this.re = re;
        this.im = im;
    }

    public ComplexNumberImp(String value){
        this.set(value);
    }



    @Override
    public double getRe() {
        return this.re;
    }


    @Override
    public double getIm() {
        return this.im;
    }


    @Override
    public boolean isReal() {
        return this.im == 0;
    }


    @Override
    public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }

    @Override
    public void set(String value) throws NumberFormatException {
        int splitter = findSplitter(value);
        String[] numbers = new String[2];

        if(splitter == 0 || splitter == -1){
            if(value.contains("i")){
                numbers[0] = "0";
                numbers[1] = value.substring(0, value.length());
            }
            else{
                numbers[0] = value;
                numbers[1] = "0";
            }
        }
        else{
            numbers[0] = value.substring(0, splitter);
            numbers[1] = value.substring(splitter, value.length());
        }
        re = Double.parseDouble(numbers[0]);

        if(!numbers[1].contains("i") && !numbers[1].equals("0"))
            throw new NumberFormatException(value);

        if(numbers[1].length() == 2){
            if(numbers[1].charAt(0) == '+' || numbers[1].charAt(0) == '-')
                im = Double.parseDouble(numbers[1].replaceAll("i", "1"));
            else
                im = Double.parseDouble(numbers[1].replaceAll("i", ""));
        }
        else if(numbers[1].length() == 1){
            im = Double.parseDouble(numbers[1].replaceAll("i", "1"));
        }
        else
            im = Double.parseDouble(numbers[1].substring(0, numbers[1].length() - 1));
    }

    private static int findSplitter(String value){

        for(int i = value.length()-1; i >= 0; i--){
            if(value.charAt(i) == '+' || value.charAt(i) == '-'){
                return i;
            }
        }
        return -1;
    }

    @Override
    public ComplexNumber copy() {
        ComplexNumber copy = new ComplexNumberImp(this.re, this.im);
        return copy;
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        return (ComplexNumber) super.clone();
    }


    @Override
    public int compareTo(ComplexNumber other) {
        double thisComplex = this.im * this.im + this.re * this.re;
        double otherComplex = other.getIm() * other.getIm() + other.getRe() * other.getRe();
        return Double.compare(thisComplex, otherComplex);
    }

    @Override
    public void sort(ComplexNumber[] array) {
        Arrays.sort(array);
    }


    @Override
    public ComplexNumber negate() {
        double re = (int)this.re == 0 ? 0.0 : this.re * -1;
        double im = (int)this.im == 0 ? 0.0 : this.im * -1;
        this.set(re, im);
        return this;
    }


    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        this.set(this.re + arg2.getRe(), this.im + arg2.getIm());
        return this;
    }


    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        double re = (this.re * arg2.getRe()) - (this.im * arg2.getIm());
        double im = (this.re * arg2.getIm()) + (this.im * arg2.getRe());
        this.set(re, im);
        return this;
    }

}
