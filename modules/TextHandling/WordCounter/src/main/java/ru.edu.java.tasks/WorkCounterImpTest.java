package ru.edu.java.tasks;

import org.junit.Assert;

import java.util.*;

public class WorkCounterImpTest {

    @org.junit.Test
    public void setGetTextNullTest() {
        WordCounter text = new WordCounterImp();
        Assert.assertNull(text.getText());
        text.setText(null);
        Assert.assertNull(text.getText());
    }

    @org.junit.Test
    public void setGetTest() {
        WordCounter text = new WordCounterImp();
        text.setText("This version of the GNU Lesser General Public License incorporates\n" +
                "the terms and conditions of version 3 of the GNU General Public\n" +
                "License, supplemented by the additional permissions listed below.");

        Assert.assertTrue(text.getText().equals("This version of the GNU Lesser General Public License incorporates\n" +
                "the terms and conditions of version 3 of the GNU General Public\n" +
                "License, supplemented by the additional permissions listed below."));

    }

    @org.junit.Test
    public void getWordCountsTest() {

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
        reg.put("i", 2l);
        reg.put("a", 1l);
        reg.put("3", 1l);
        reg.put("1", 1l);
        reg.put("r", 1l);


        Set<Map.Entry<String, Long>> set = counter.getWordCounts().entrySet();
        Set<Map.Entry<String, Long>> regSet = reg.entrySet();
        Assert.assertTrue(set.equals(regSet));

    }

    @org.junit.Test
    public void getWordCountsSortedTest() {

        WordCounter counter = new WordCounterImp();
        counter.setText("apple zero zero bus apple 100000 grape nut bus zero grape 450 123 123 450");

        HashMap<String, Long> map = new HashMap<>();

        map.put("apple", 2l);
        map.put("zero", 3l);
        map.put("bus", 2l);
        map.put("grape", 2l);
        map.put("nut", 1l);

        map.put("123", 2l);
        map.put("450", 2l);
        map.put("100000" , 1l);

        List<Map.Entry<String, Long>> test = counter.getWordCountsSorted();


        Assert.assertTrue(test.get(0).getKey().equals("zero"));
        Assert.assertTrue(test.get(1).getKey().equals("apple"));
        Assert.assertTrue(test.get(2).getKey().equals("bus"));
        Assert.assertTrue(test.get(3).getKey().equals("123"));
        Assert.assertTrue(test.get(4).getKey().equals("450"));
        Assert.assertTrue(test.get(5).getKey().equals("grape"));
        Assert.assertTrue(test.get(6).getKey().equals("nut"));
        Assert.assertTrue(test.get(7).getKey().equals("100000"));
    }

    @org.junit.Test
    public void sortWordCountsTest() {
        HashMap<String, Long> map = new HashMap<>();

        map.put("apple", 2l);
        map.put("zero", 3l);
        map.put("bus", 1l);
        map.put("grape", 2l);
        map.put("nut", 1l);

        WordCounter counter = new WordCounterImp();

        List<Map.Entry<String, Long>> test = counter.sortWordCounts(map);

        Assert.assertTrue(test.get(0).getKey().equals("zero"));
        Assert.assertTrue(test.get(1).getKey().equals("apple"));
        Assert.assertTrue(test.get(2).getKey().equals("grape"));
        Assert.assertTrue(test.get(3).getKey().equals("bus"));
        Assert.assertTrue(test.get(4).getKey().equals("nut"));


    }
}
