package ru.edu.java.tasks;

import org.junit.Assert;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordProcessorTest {

    public WordProcessorTest() throws IOException {
    }

    private String readFile(String path) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(path));
        String source = "";
        for (String str : lines)
            source += str + "\n";
        return source;
    }

    String testText = readFile("modules/JavaAPITasks/WordProcessor/src/test/resource/text.txt");

    @Test
    public void setSourceAndGetTextTest() throws IOException {
        WordProcessor wp = new WordProcessorImp();
        wp.setSource(testText);
        Assert.assertTrue(wp.getText().equals(testText));
    }

    @Test
    public void setSourceFileTest() throws IOException, NoSuchFieldException, IllegalAccessException {
        WordProcessor wp = new WordProcessorImp();
        wp.setSourceFile("modules/JavaAPITasks/WordProcessor/src/test/resource/text.txt");
        Field textField = wp.getClass().getDeclaredField("text");
        textField.setAccessible(true);
        String text = (String)textField.get(wp);
        Assert.assertTrue(testText.equals(text));
    }

    @Test
    public void setSourceTest() throws IOException, NoSuchFieldException, IllegalAccessException {
        FileInputStream fis=new FileInputStream("modules/JavaAPITasks/WordProcessor/src/test/resource/text.txt");
        WordProcessor wp = new WordProcessorImp();
        wp.setSource(fis);

        Field textField = wp.getClass().getDeclaredField("text");
        textField.setAccessible(true);
        String text = (String)textField.get(wp);

        List<String> forText = new ArrayList<>();
        for(String s : text.split("")){
            if(!s.equals("") && !s.equals("\n"))
                forText.add(s);
        }

        List<String>forTestText = new ArrayList<>();
        for(String s : testText.split("")){
            if(!s.equals("") && !s.equals("\n"))
                forTestText.add(s);
        }

        Assert.assertTrue(forText.equals(forTestText));
    }

    private Set<String> doSetFromString(String input){

        Set<String>words = new HashSet<>();
        for(String s : input.split("\n"))
            words.add(s);

        return words;
    }

    @Test
    public void wordsStartWithTest() throws IOException {
        WordProcessor wp = new WordProcessorImp(testText);

        Set<String>result = wp.wordsStartWith("б");
        Set<String>test = doSetFromString(readFile("modules/JavaAPITasks/WordProcessor/src/test/resource/Б.txt"));
        Assert.assertTrue(result.equals(test));

        result = wp.wordsStartWith("За");
        test = doSetFromString(readFile("modules/JavaAPITasks/WordProcessor/src/test/resource/За.txt"));
        Assert.assertTrue(result.equals(test));

        result = wp.wordsStartWith("Вы");
        test = doSetFromString(readFile("modules/JavaAPITasks/WordProcessor/src/test/resource/Вы.txt"));
        Assert.assertTrue(result.equals(test));
    }
}