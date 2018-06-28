package ru.edu.java.tasks;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordProcessorImp implements WordProcessor {

    private String text;
    private boolean isAlreadyRead = false;
    private final static Set<String> spec = new HashSet(Arrays.asList("^ $ * + - ? ( ) [ ] { } \\ |".split(" ")));
    private final static Set<String> numbers =  new HashSet(Arrays.asList("1 2 3 4 5 6 7 8 9 0".split(" ")));

    public WordProcessorImp() {

    }




    public WordProcessorImp(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setSource(String src) {
        try {
            if (src == null)
                throw new IllegalArgumentException("src == null");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }


        this.text = src;
    }

    @Override
    public void setSourceFile(String srcFile) throws IOException {
        try {
            if (srcFile == null)
                throw new IllegalArgumentException("src == srcFile");
        } catch (IllegalArgumentException e) {
            throw new IOException(e.getMessage());
        }

        List<String> lines = Files.readAllLines(Paths.get(srcFile));
        this.text = "";
        for (String s : lines)
            text += s + "\n";
    }

    @Override
    public void setSource(FileInputStream fis) throws IOException {
        try {
            if (fis == null)
                throw new IllegalArgumentException("fis == srcFile");
            else if(isAlreadyRead)
                throw new IllegalArgumentException("This item can not read more files");
        } catch (IllegalArgumentException e) {
            throw new IOException(e.getMessage());
        }

        StringBuilder stringBuffer = new StringBuilder();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(fis, "UTF8"))) {
            String str = in.readLine();
            while (str != null) {
                stringBuffer.append(str);
                str = in.readLine();
            }
            this.text = stringBuffer.toString();
            this.isAlreadyRead = true;
        }
    }




    private String makeRegEx(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ \\f\\n\\r\\t]");

        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);

            if(spec.contains(symbol + ""))
                sb.append("[\\" + symbol + "]");
            else if(numbers.contains(symbol + ""))
                sb.append("[" + symbol + "]");
            else{
                char upper = input.toUpperCase().charAt(i);
                char lower = input.toLowerCase().charAt(i);
                sb.append("[" + upper + lower + "]");
            }
        }
        sb.append("[.]*[^ \\.\\,\\:\\f\\n\\r\\t]*");
        return sb.toString();
    }

    @Override
    public Set<String> wordsStartWith(String begin) {
        if (this.text == null)
            throw new IllegalStateException();

        Set<String> words = new HashSet<>();
        Pattern pattern = Pattern.compile(makeRegEx(begin));
        Matcher matcher = pattern.matcher(this.text);
        while (matcher.find())
            words.add(matcher.group().trim().toLowerCase());
        return words;
    }
}
