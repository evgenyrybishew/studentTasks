package ru.edu.java.tasks;

import java.util.*;


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
            this.counter = new HashMap<>();

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

        return this.sortWordCounts(this.getWordCounts());
    }


    private static boolean isDigit(String number){

        try{
            Integer.valueOf(number);
            return true;
        }catch (Exception e){
            return false;
        }

    }



    @Override
    public List<Map.Entry<String, Long>> sortWordCounts(Map<String, Long> orig) {

        if (orig == null)
            return null;

        List<Map.Entry<String, Long>> temp = new ArrayList<>(orig.entrySet());

        Collections.sort(temp, new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {

                if (Long.compare(o1.getValue(), o2.getValue()) != 0)
                    return Long.compare(o1.getValue(), o2.getValue()) * (-1);

                else if(isDigit(o1.getKey()) && isDigit(o2.getKey())){
                    return Integer.compare(Integer.valueOf(o1.getKey()), Integer.valueOf(o2.getKey()));
                }

                else if(isDigit(o1.getKey()) || isDigit(o2.getKey())){
                    return Integer.compare(o1.getKey().length(), o2.getKey().length());
                }

                return o1.getKey().compareTo(o2.getKey());
            }
        });

        return temp;
    }
}
