package ru.edu.java.tasks;

import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkCounterImpTest {

    @org.junit.Test
    public void setGetTextNullTest() {
        WordCounter text = new WordCounterImp();
        Assert.assertNull(text.getText());
        text.setText(null);
        Assert.assertNull(text.getText());
    }

    @org.junit.Test
    public void setGetText() {
        WordCounter text = new WordCounterImp();
        text.setText("This version of the GNU Lesser General Public License incorporates\n" +
                "the terms and conditions of version 3 of the GNU General Public\n" +
                "License, supplemented by the additional permissions listed below.");

        Assert.assertTrue(text.getText().equals("This version of the GNU Lesser General Public License incorporates\n" +
                "the terms and conditions of version 3 of the GNU General Public\n" +
                "License, supplemented by the additional permissions listed below."));

    }

    @org.junit.Test
    public void getWordCounts() {

        WordCounter counter = new WordCounterImp();
        counter.setText("Hello    world 4or  &gt;    r  red yellow green #FFF   &lt;  1. \"123 \", \"Mother\" 2. \"мыла\", 3. <i>a<i>" +
                "world mother hello green мыла");


        Map<String, Long> reg = new HashMap<>();
        reg.put("hello", 2l);
        reg.put("world", 2l);
        reg.put("4or", 1l);
        reg.put("red", 1l);
        reg.put("fff", 1l);
        reg.put("mother", 2l);
        reg.put("мыла", 2l);
        reg.put("123", 1l);
        reg.put("green", 2l);
        reg.put("yellow", 1l);
        reg.put("4or", 1l);
        reg.put("123", 1l);
        reg.put("2", 1l);
        reg.put("i",2l);
        reg.put("a", 1l);
        reg.put("3", 1l);
        reg.put("1", 1l);
        reg.put("r", 1l);


        Set<Map.Entry<String, Long>> set = counter.getWordCounts().entrySet();
        Set<Map.Entry<String, Long>> regSet = reg.entrySet();
        Assert.assertTrue(set.equals(regSet));

    }

    @org.junit.Test
    public void getWordCountsSorted() {
    }

    @org.junit.Test
    public void sortWordCounts() {
    }


    public static void main(String[] args) {
//        Pattern pattern = Pattern.compile("([a-z])");
//
//        String[] words = pattern.split("Hello    world 4or  &gt;   r  red yellow green #FFF   &lt;  1. \"123 \", \"Mother\" 2. \"мылы\", 3. <i>a<i>" +
//                "world mother hello green мыла");
//
//
//
//        for(String word:words)
//            System.out.println(word);


        String input = "Hello    world 4or  &gt;   r  red yellow green #FFF   &lt;  1. \"123 \", \"Mother\" 2. \"мылы\", 3. <i>a<i>" +
                "world mother hello green мыла жава";

        Pattern pattern = Pattern.compile("(?U)(\\w*)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {

            if (matcher.group().length() <= 2)
                continue;

            System.out.println(matcher.group());
        }
    }


}