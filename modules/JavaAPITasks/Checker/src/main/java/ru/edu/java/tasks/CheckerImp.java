package ru.edu.java.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckerImp implements Checker {


    @Override
    public Pattern getPLSQLNamesPattern() {
        return Pattern.compile("[A-Za-z][\\w_$]{0,29}");
    }

    @Override
    public Pattern getHrefURLPattern() {
        return Pattern.compile("<a[\\s]+([^>]+)>((?:.)*)<([/]||[\\s])a>");
    }

    @Override
    public Pattern getEMailPattern() {
        return Pattern.compile("[a-zA-Z0-9](|[\\w-_\\.]{0,20}[a-zA-Z0-9])@([a-zA-Z0-9](|[\\w-]*[\\w])\\.){1,100000000}(ru|com|net|org)");
    }

    @Override
    public boolean checkAccordance(String inputString, Pattern pattern) throws IllegalArgumentException {

        if(inputString == null && pattern == null)
            return true;
        else if(inputString == null || pattern == null)
            throw new IllegalArgumentException();

        Matcher matcher = pattern.matcher(inputString);
        return matcher.matches();
    }

    @Override
    public List<String> fetchAllTemplates(StringBuffer inputString, Pattern pattern) throws IllegalArgumentException {
        if (inputString == null || pattern == null) throw new IllegalArgumentException();
        List<String> allTemplates = new ArrayList<>();
        Matcher matcher = pattern.matcher(inputString);

        while (matcher.find())
            allTemplates.add(matcher.group());

        return allTemplates;
    }
}
