package ru.edu.java.tasks;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurriculumVitaeImp implements CurriculumVitae {


    private String text;
    private Map<String, String> hiddenInfo;


    public CurriculumVitaeImp() {
        hiddenInfo = new HashMap<>();
    }

    public CurriculumVitaeImp(String text) {
        this.hiddenInfo = new HashMap<>();
        this.text = text;
    }


    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() throws IllegalStateException {
        if (this.text == null)
            throw new IllegalStateException();
        return this.text;
    }


    public class PhoneInfoContainer {
        private String phone;
        private int extension;
        private int areaCode;

        public PhoneInfoContainer(String phone, int extension, int areaCode) {
            this.phone = phone;
            this.extension = extension;
            this.areaCode = areaCode;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getExtension() {
            return extension;
        }

        public void setExtension(int extension) {
            this.extension = extension;
        }

        public int getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(int areaCode) {
            this.areaCode = areaCode;
        }
    }

    @Override
    public List<Phone> getPhones() throws IllegalStateException {
        if (this.text == null)
            throw new IllegalStateException();

        List<Phone> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(this.text);

        while (matcher.find()) {
            PhoneInfoContainer phoneInfo = new PhoneInfoContainer(matcher.group(), Integer.MIN_VALUE, Integer.MIN_VALUE);
            extension(phoneInfo);
            areaCode(phoneInfo, result);
        }
        return result;
    }


    private void extension(PhoneInfoContainer phoneInfo) throws NumberFormatException {

        Pattern pattern = Pattern.compile("ext\\.?\\s*([0-9]+)");
        Matcher matcher = pattern.matcher(phoneInfo.getPhone());

        if (matcher.find()) {
            pattern = Pattern.compile("[0-9]+");
            matcher = pattern.matcher(matcher.group());
            matcher.find();

            phoneInfo.setExtension(Integer.parseInt(matcher.group()));
            phoneInfo.setPhone(phoneInfo.getPhone().split("ext\\.?\\s*([0-9]+)")[0]);
        }
    }

    private void areaCode(PhoneInfoContainer phoneInfo, List<Phone> result) throws NumberFormatException {

        Pattern pattern = Pattern.compile("[^\\d]");
        Matcher matcher = pattern.matcher(phoneInfo.getPhone());

        String tempPhone = phoneInfo.getPhone();

        if (matcher.find())
            tempPhone = matcher.replaceAll("");

        if (tempPhone.length() <= 7)
            result.add(new Phone(phoneInfo.getPhone(), phoneInfo.getAreaCode(), phoneInfo.getExtension()));
        else {
            int start = 0;
            int end = tempPhone.length() - 7;
            char[] tempBuffer = new char[end - start];
            tempPhone.getChars(start, end, tempBuffer, 0);
            phoneInfo.setAreaCode(Integer.valueOf(new String(tempBuffer)));

            phoneInfo.setPhone(phoneInfo.getPhone().replace("(", ""));
            phoneInfo.setPhone(phoneInfo.getPhone().replace(")", ""));
            phoneInfo.setPhone(phoneInfo.getPhone().substring(3).trim());
            result.add(new Phone(phoneInfo.getPhone(), phoneInfo.getAreaCode(), phoneInfo.getExtension()));
        }
    }


    @Override
    public String getFullName() throws NoSuchElementException, IllegalStateException {
        if (this.text == null)
            throw new IllegalStateException();

        Pattern pattern = Pattern.compile("([A-Z][a-z]+ | [A-Z]\\.)([A-Z][a-z]+ | [A-Z]\\.)([A-Z][a-z]+ | [A-Z]\\.)?");
        Matcher matcher = pattern.matcher(this.text);
        String fullName;
        if (matcher.find())
            fullName = matcher.group();
        else
            throw new NoSuchElementException();

        return fullName;
    }

    @Override
    public String getFirstName() throws NoSuchElementException, IllegalStateException {
        return this.getFullName().split(" ")[0];
    }

    @Override
    public String getMiddleName() throws NoSuchElementException, IllegalStateException {
        String[] name = getFullName().split(" ");
        return name.length == 3 ? name[1] : null;

    }

    @Override
    public String getLastName() throws NoSuchElementException, IllegalStateException {
        String[] name = getFullName().split(" ");
        return name.length == 3 ? name[2] : name[1];
    }

    @Override
    public void updateLastName(String newLastName) throws NoSuchElementException, IllegalStateException {
        if (newLastName != null) {
            Pattern pattern = Pattern.compile(this.getLastName());
            Matcher matcher = pattern.matcher(this.text);
            if (matcher.find())
                this.text = matcher.replaceAll(newLastName);
        }
    }

    @Override
    public void updatePhone(Phone oldPhone, Phone newPhone) throws IllegalArgumentException, IllegalStateException {
        if (oldPhone == null || newPhone == null)
            throw new IllegalStateException();
        if (this.text != null) {
            Pattern pattern = Pattern.compile(oldPhone.getNumber());
            Matcher matcher = pattern.matcher(this.text);

            if (matcher.find())
                this.text = matcher.replaceAll(newPhone.getNumber());
            else
                throw new IllegalArgumentException();
        }
    }

    private boolean isSpecialSymbol(char symbol){
        char[] symbpls = "!$()*+.<>?[]\\^{}|".toCharArray();
        for(char c : symbpls)
            if(c == symbol)
                return true;
        return false;
    }


    private String makePatternStr(String input){
        StringBuffer maker = new StringBuffer(input);
        for(int i = 0; i < input.length(); i++)
            if(isSpecialSymbol(maker.charAt(i))) {
                maker.insert(i, '\\');
                input = maker.toString();
                i++;
            }
        return maker.toString();
    }


    private void hideContent(String content, String regex) throws IllegalArgumentException, IllegalStateException {
        Pattern pattern = Pattern.compile(makePatternStr(content));
        Matcher matcher = pattern.matcher(this.text);

        if (matcher.find()) {
            String hiddenText = content.replaceAll(regex, "X");
            if (!this.hiddenInfo.containsKey(hiddenText)) {
                this.hiddenInfo.put(hiddenText, content);
                this.text = matcher.replaceAll(hiddenText);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }


    @Override
    public void hide(String piece) throws IllegalArgumentException, IllegalStateException {
        if (this.text == null)
            throw new IllegalStateException();
        hideContent(piece, "[^\\.,@ ]");
    }

    @Override
    public void hidePhone(String phone) throws IllegalArgumentException, IllegalStateException {
        if (this.text == null)
            throw new IllegalStateException();
        hideContent(phone, "[0-9]");

    }

    @Override
    public int unhideAll() throws IllegalStateException {
        if (this.text == null)
            throw new IllegalStateException();

        int counter = 0;

        for (Map.Entry<String, String> entry : this.hiddenInfo.entrySet()) {
            Pattern pattern = Pattern.compile(entry.getKey());
            Matcher matcher = pattern.matcher(this.text);

            this.text = matcher.replaceAll(entry.getValue());
            counter++;
        }
        this.hiddenInfo.clear();
        return counter;
    }

}
