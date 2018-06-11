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




    /**
     * @return real part of this complex number
     */
    @Override
    public double getRe() {
        return this.re;
    }

    /**
     * @return imaginary part of this complex number
     */
    @Override
    public double getIm() {
        return this.im;
    }

    /**
     * @return true if this complex number has real part only (otherwise false)
     */
    @Override
    public boolean isReal() {
        return this.im == 0;
    }

    /**
     * Sets both real and imaginary part of this number.
     *
     * @param re
     * @param im
     */
    @Override
    public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }

    /**
     * Parses the given string value and sets the real and imaginary parts of this number accordingly.<br/>
     * The string format is "re+imi", where re and im are numbers (floating point or integer) and 'i' is a special symbol
     * denoting imaginary part (if present, it's always the last character in the string).<br/>
     * Both '+' and '-' symbols can be the first characters of re and im; but '*' symbol is NOT allowed.<br/>
     * Correct examples: "-5+2i", 1+i", "+4-i", "i", "-3i", "3". Incorrect examples: "1+2*i", "2+2", "j".<br/>
     * Note: explicit exception generation is an OPTIONAL requirement for this task,
     * but NumberFormatException can be thrown by {@link Double#parseDouble(String)}).<br/>
     * Note: it is not reasonable to use regex while implementing this method: the parsing logic is too complicated.
     *
     * @param value
     * @throws NumberFormatException if the given string value is incorrect
     */
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

    /**
     * Find "+" or "-" in string
     * @param value
     * @return number of position for "+" or "-"
     */
    private static int findSplitter(String value){

        for(int i = value.length()-1; i >= 0; i--){
            if(value.charAt(i) == '+' || value.charAt(i) == '-'){
                return i;
            }
        }
        return -1;
    }

    /**
     * Creates and returns a copy of this object: <code>x.copy().equals(x)</code> but <code>x.copy()!=x</code>.
     *
     * @return the copy of this number
     * @see #clone()
     */
    @Override
    public ComplexNumber copy() {
        ComplexNumber copy = new ComplexNumberImp();
        copy.set(this.re, this.im);
        return copy;
    }

    /**
     * Creates and returns a copy of this object: the same as {@link #copy()}.<br/>
     * Note: when implemented in your class, this method overrides the {@link Object#clone()} method but changes
     * the visibility and the return type of the Object's method: the visibility modifier is changed
     * from protected to public, and the return type is narrowed from Object to ComplexNumber (see also
     * <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-8.html#d5e11368">covariant types example from JLS</a>).
     *
     * @return the copy of this number
     * @see Object#clone()
     */
    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        return this.copy();
    }

    /**
     * Compares this number with the other number by the absolute values of the numbers:
     * x < y if and only if |x| < |y| where |x| denotes absolute value (modulus) of x.<br/>
     * Can also compare the square of the absolute value which is defined as the sum
     * of the real part squared and the imaginary part squared: |re+imi|^2 = re^2 + im^2.
     *
     * @param other the object to be compared with this object.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the given object.
     * @see Comparable#compareTo(Object)
     */
    @Override
    public int compareTo(ComplexNumber other) {
        double thisComplex = this.im * this.im + this.re * this.re;
        double otherComplex = other.getIm() * other.getIm() + other.getRe() * other.getRe();

        return Double.compare(thisComplex, otherComplex);
    }

    /**
     * Sorts the given array in ascending order according to the comparison rule defined in
     * {@link #compareTo(ComplexNumber)}.<br/>
     * It's strongly recommended to use {@link Arrays} utility class here
     * (and do not transform the given array to a double[] array).<br/>
     * Note: this method could be static: it does not use this instance of the ComplexNumber.
     * Nevertheless, it is not static because static methods can't be overridden.
     *
     * @param array an array to sort
     */
    @Override
    public void sort(ComplexNumber[] array) {
        Arrays.sort(array);
    }



    /**
     * Changes the sign of this number. Both real and imaginary parts change their sign here.
     *
     * @return this number (the result of negation)
     */
    @Override
    public ComplexNumber negate() {
        ComplexNumber temp = new ComplexNumberImp();
        double re = (int)this.re == 0 ? 0.0 : this.re * -1;
        double im = (int)this.im == 0 ? 0.0 : this.im * -1;
        temp.set(re, im);
        return temp;
    }

    /**
     * Adds the given complex number arg2 to this number. Both real and imaginary parts are added.
     *
     * @param arg2 the second operand of the operation
     * @return this number (the sum)
     */
    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        ComplexNumber temp =
                new ComplexNumberImp(this.re + arg2.getRe(), this.im + arg2.getIm());
        return temp;
    }

    /**
     * Multiplies this number by the given complex number arg2. If this number is a+bi and arg2 is c+di then
     * the result of their multiplication is (a*c-b*d)+(b*c+a*d)i<br/>
     * The method should work correctly even if arg2==this.
     *
     * @param arg2 the second operand of the operation
     * @return this number (the result of multiplication)
     */
    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        double re = (this.re * arg2.getRe()) - (this.im * arg2.getIm());
        double im = (this.re * arg2.getIm()) + (this.im * arg2.getRe());
        ComplexNumber temp = new ComplexNumberImp(re, im);
        return temp;
    }

}
