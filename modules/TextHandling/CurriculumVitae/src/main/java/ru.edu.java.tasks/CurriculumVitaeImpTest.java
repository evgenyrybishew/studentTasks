package ru.edu.java.tasks;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class CurriculumVitaeImpTest {


    private String readFile(String path) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(path));
        String source = "";
        for (String str : lines)
            source += str + " ";
        return source;
    }


    @Test
    public void setTextTest() throws Exception {

        CurriculumVitae cv = new CurriculumVitaeImp();
        cv.setText(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt"));

        Field field = cv.getClass().getDeclaredField("text");
        field.setAccessible(true);
        String text = (String) field.get(cv);

        Assert.assertTrue(text.equals(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt")));


    }

    @Test
    public void getTextTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt"));
        Assert.assertTrue(cv.getText().equals(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt")));
    }

    @Test
    public void getPhonesTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt"));
        String phones = readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/phones.txt");
        ArrayList<CurriculumVitae.Phone> phoneList = (ArrayList<CurriculumVitae.Phone>) cv.getPhones();
        StringBuilder result = new StringBuilder();
        for (CurriculumVitae.Phone p : phoneList) {
            result.append(p.getNumber() + " ");
        }

        Assert.assertTrue(result.toString().replaceAll("\\s+", " ").trim().equals(phones.trim()));
    }

    @Test
    public void getFullNameTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt"));
        String fullName = cv.getFullName();
        Assert.assertTrue(fullName.trim().equals("Sidorov Ptetr Vasilievich"));
    }

    @Test
    public void getFullNameNoSuchElementExceptionTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/wrongCV.txt");
        boolean fail = false;
        try {
            cv.getFullName();
        } catch (NoSuchElementException e) {
            fail = true;
        } finally {
            Assert.assertTrue(fail);
        }
    }


    @Test
    public void getFirstNameTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt"));
        Assert.assertTrue(cv.getFirstName().equals("Sidorov"));
        cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv2.txt"));
        Assert.assertTrue(cv.getFirstName().equals("Sidorov"));


    }

    @Test
    public void getMiddleNameTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt"));
        Assert.assertTrue(cv.getMiddleName().equals("Ptetr"));
        cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv2.txt"));
        Assert.assertNull(cv.getMiddleName());
    }

    @Test
    public void getLastName() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt"));
        Assert.assertTrue(cv.getLastName().equals("Vasilievich"));
        cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv2.txt"));
        Assert.assertTrue(cv.getLastName().equals("Ptetr"));
    }

    @Test
    public void updateLastName() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt"));
        cv.updateLastName("Fedorovich");
        Field field = cv.getClass().getDeclaredField("text");
        field.setAccessible(true);
        String text = (String) field.get(cv);
        Assert.assertTrue(text.equals(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/updateCV.txt")));
    }

    @Test
    public void updatePhoneTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt"));

        CurriculumVitae.Phone oldPhone = new CurriculumVitae.Phone("123-45-67", 999, 2345);
        CurriculumVitae.Phone newPhone = new CurriculumVitae.Phone("999-99-99", 999, 2345);
        cv.updatePhone(oldPhone, newPhone);

        Field field = cv.getClass().getDeclaredField("text");
        field.setAccessible(true);
        String text = (String) field.get(cv);

        Assert.assertTrue(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cvUpdatePhones.txt").equals(text));
    }

    @Test
    public void updatePhoneIllegalStateExceptionTest() throws Exception {

        CurriculumVitae cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt"));
        CurriculumVitae.Phone oldPhone = new CurriculumVitae.Phone("123-45-67", 999, 2345);
        boolean ex = false;
        try {
            cv.updatePhone(oldPhone, null);
        } catch (IllegalStateException e) {
            ex = true;
        } finally {
            Assert.assertTrue(ex);
        }
    }

    @Test
    public void updatePhoneIllegalArgumentExceptionTest() throws Exception {

        CurriculumVitae cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt"));
        CurriculumVitae.Phone oldPhone = new CurriculumVitae.Phone("777-55-99", 999, 2345);
        CurriculumVitae.Phone newPhone = new CurriculumVitae.Phone("999-99-99", 999, 2345);

        boolean ex = false;
        try {
            cv.updatePhone(oldPhone, newPhone);
        } catch (IllegalArgumentException e) {
            ex = true;
        } finally {
            Assert.assertTrue(ex);
        }
    }

    @Test
    public void hideTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt"));

        cv.hide("sidorov.petr@mail.net");

        Field field = cv.getClass().getDeclaredField("text");
        field.setAccessible(true);
        String text = (String) field.get(cv);
        Assert.assertTrue(text.equals(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/hideTest.txt")));

        field = cv.getClass().getDeclaredField("hiddenInfo");
        field.setAccessible(true);
        Map<String, String> map = (Map<String, String>) field.get(cv);

        Assert.assertTrue(map.containsValue("sidorov.petr@mail.net"));
    }

    //TODO: fix bug - IllegalArgumentException for (916)700-6964
    @Test
    public void hidePhoneTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt"));
        cv.hidePhone("999 123-45-67 ext.2345");
        cv.hidePhone("256-1004");

        Field field = cv.getClass().getDeclaredField("text");
        field.setAccessible(true);
        String text = (String) field.get(cv);
        Assert.assertTrue(text.equals(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/hidePhone.txt")));

        field = cv.getClass().getDeclaredField("hiddenInfo");
        field.setAccessible(true);
        Map<String, String> map = (Map<String, String>) field.get(cv);
        Assert.assertTrue(map.containsValue("999 123-45-67 ext.2345"));
        Assert.assertTrue(map.containsValue("256-1004"));

    }

    @Test
    public void unhideAllTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt"));

        Field field = cv.getClass().getDeclaredField("text");
        field.setAccessible(true);
        field.set(cv, readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/forUnhideAllTest.txt"));

        Map<String, String> hidenInfo = new HashMap<>();

        hidenInfo.put("XXX XXX-XX-XX ext.XXXX", "999 123-45-67 ext.2345");
        hidenInfo.put("XXX-XXXX", "256-1004");
        hidenInfo.put("XXXXXXX.XXXX@XXXX.XXX", "sidorov.petr@mail.net");
        field = cv.getClass().getDeclaredField("hiddenInfo");
        field.setAccessible(true);
        field.set(cv, hidenInfo);

        int counter = cv.unhideAll();
        field = cv.getClass().getDeclaredField("text");
        field.setAccessible(true);
        String text = (String) field.get(cv);

        Assert.assertTrue(text.equals(readFile("modules/TextHandling/CurriculumVitae/src/main/java/ru.edu.java.tasks/cv.txt")));
        Assert.assertEquals(3, counter);
    }
}