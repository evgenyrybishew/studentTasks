package ru.edu.java.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounterImp implements WordCounter {


    private Map<String, Long> counter = null;
    private String text = null;


    @Override
    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public Map<String, Long> getWordCounts() throws IllegalStateException {

        if (this.text == null)
            return null;

        if (counter == null)
            this.counter = new HashMap<String, Long>();

        Pattern pattern = Pattern.compile("(?U)(\\w*)");
        Matcher matcher = pattern.matcher(this.text);

        String[] words = this.text.split("(?U)\\s*(\\s |,|!|\\.|(&gt;)|(&lt;)|\\W)\\s*");

        for (String word : words) {
            word = word.toLowerCase();
            if ((word.matches("(?U)(\\w+)"))) {

                if (this.counter.containsKey(word)) {
                    long value = this.counter.get(word);
                    this.counter.put(word, ++value);
                } else
                    this.counter.put(word, 1l);
            }
        }
        return this.counter;
    }


    @Override
    public List<Map.Entry<String, Long>> getWordCountsSorted() throws IllegalStateException {
        return null;
    }

    @Override
    public List<Map.Entry<String, Long>> sortWordCounts(Map<String, Long> orig) {
        return null;
    }
}
