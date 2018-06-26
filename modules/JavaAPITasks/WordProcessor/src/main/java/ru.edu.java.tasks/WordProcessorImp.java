package ru.edu.java.tasks;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordProcessorImp implements WordProcessor {

    private String text ;

    public WordProcessorImp(){
        this.text = null;
    }

    public WordProcessorImp(String text){
        this.text = text;
    }

    //TODO: ??? @throws IllegalArgumentException если <code>src == null</code>
    //Это же установка значения, а если использовать этод метод сразу после создания
    //объекта WordProcessorImp, то всегда будет выбрасываться исключение

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setSource(String src) {
        this.text = src;
    }

    @Override
    public void setSourceFile(String srcFile) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(srcFile));
        this.text = "";
        for (String s : lines)
            text += s + "\n";
    }

    @Override
    public void setSource(FileInputStream fis) throws IOException {
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(fis, "UTF8"));
        String str;
        while ((str = in.readLine()) != null) {
            stringBuffer.append(str);
        }
        this.text = stringBuffer.toString();
        in.close();
    }

    private String makeRegEx(String input){
        StringBuilder sb = new StringBuilder();
        sb.append("[ \\f\\n\\r\\t]");

        for(int i = 0; i < input.length(); i++){
            char upper = input.toUpperCase().charAt(i);
            char lower = input.toLowerCase().charAt(i);
            sb.append("[" + upper + lower + "]");
        }
        sb.append("[A-Za-zА-Яа-я]*[^ \\.\\,\\:\\f\\n\\r\\t]*");
        return sb.toString();
    }

    @Override
    public Set<String> wordsStartWith(String begin) {
        if(this.text == null)
            throw new IllegalStateException();

        Set<String>words = new HashSet<>();
        Pattern pattern = Pattern.compile(makeRegEx(begin));
        Matcher matcher = pattern.matcher(this.text);

        while (matcher.find()){
            words.add(matcher.group().trim().toLowerCase());
        }
        return words;
    }
}
