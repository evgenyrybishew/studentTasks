package ru.edu.tasks;

import org.junit.Assert;
import org.junit.Test;
import ru.edu.java.tasks.CurriculumVitae;
import ru.edu.java.tasks.CurriculumVitaeImp;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CurriculumVitaeTest {
    //TODO: ✔ Вынести в отдельную переменную "modules/TextHandling/CurriculumVitae/test/resource/cv.txt"
    String cvFileString = readFile("modules/TextHandling/CurriculumVitae/test/resource/cv.txt");

    public CurriculumVitaeTest() throws IOException {
    }


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
        cv.setText(cvFileString);

        Field field = cv.getClass().getDeclaredField("text");
        field.setAccessible(true);
        String text = (String) field.get(cv);

        Assert.assertTrue(text.equals(cvFileString));


    }

    @Test
    public void getTextTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(cvFileString);
        Assert.assertTrue(cv.getText().equals(cvFileString));
    }

    @Test
    public void getPhonesTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(cvFileString);
        String phones = readFile("modules/TextHandling/CurriculumVitae/test/resource/phones.txt");
        ArrayList<CurriculumVitae.Phone> phoneList = (ArrayList<CurriculumVitae.Phone>) cv.getPhones();
        StringBuilder result = new StringBuilder();
        for (CurriculumVitae.Phone p : phoneList) {
            result.append(p.getNumber() + " ");
        }

        Assert.assertTrue(result.toString().replaceAll("\\s+", " ").trim().equals(phones.trim()));
    }

    @Test
    public void getFullNameTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(cvFileString);
        String fullName = cv.getFullName();
        Assert.assertTrue(fullName.trim().equals("Sidorov Ptetr Vasilievich"));
    }


    @Test
    public void getFullNameNoSuchElementExceptionTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/test/resource/wrongCV.txt"));
        boolean fail = false;
        try {
            cv.getFullName();
        } catch (NoSuchElementException e) {
            fail = true;
        } finally {
            Assert.assertTrue(fail);
        }
    }

    //TODO:  ✔ проверка -> длинну строки отвечающая за полное имя. Имя, Фамилия, Отчество 2 и более символа
    @Test
    public void getFullNameLengthOfFullName() throws IOException {
        CurriculumVitae cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/test/resource/fullNameLength.txt"));
        int lenght = 5;
        Assert.assertEquals(lenght, cv.getFullName().trim().length());
    }



    @Test
    public void getFirstNameTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(cvFileString);
        Assert.assertTrue(cv.getFirstName().equals("Sidorov"));
        cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/test/resource/cv2.txt"));
        Assert.assertTrue(cv.getFirstName().equals("Sidorov"));


    }

    @Test
    public void getMiddleNameTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(cvFileString);
        Assert.assertTrue(cv.getMiddleName().equals("Ptetr"));
        cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/test/resource/cv2.txt"));
        Assert.assertNull(cv.getMiddleName());
    }

    @Test
    public void getLastName() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(cvFileString);
        Assert.assertTrue(cv.getLastName().equals("Vasilievich"));
        cv = new CurriculumVitaeImp(readFile("modules/TextHandling/CurriculumVitae/test/resource/cv2.txt"));
        Assert.assertTrue(cv.getLastName().equals("Ptetr"));
    }

    @Test
    public void updateLastName() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(cvFileString);
        cv.updateLastName("Fedorovich");
        Field field = cv.getClass().getDeclaredField("text");
        field.setAccessible(true);
        String text = (String) field.get(cv);
        Assert.assertTrue(text.equals(readFile("modules/TextHandling/CurriculumVitae/test/resource/updateCV.txt")));
    }
    //TODO: ✔ изменение areaCode не влияет на метод обновления номера
    @Test
    public void updatePhoneTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(cvFileString);

        CurriculumVitae.Phone oldPhone = new CurriculumVitae.Phone("123-45-67", 999, 2345);
        CurriculumVitae.Phone newPhone = new CurriculumVitae.Phone("999-99-99", 989, 2347);
        cv.updatePhone(oldPhone, newPhone);

        Field field = cv.getClass().getDeclaredField("text");
        field.setAccessible(true);
        String text = (String) field.get(cv);

        Assert.assertTrue(readFile("modules/TextHandling/CurriculumVitae/test/resource/cvUpdatePhones.txt").equals(text));
    }

    @Test
    public void updatePhoneIllegalStateExceptionTest() throws Exception {

        CurriculumVitae cv = new CurriculumVitaeImp(cvFileString);
        CurriculumVitae.Phone oldPhone = new CurriculumVitae.Phone("123-45-67", 999, 2345);
        boolean failed = false;
        try {
            cv.updatePhone(oldPhone, null);
        } catch (IllegalStateException e) {
            failed = true;
        } finally {
            Assert.assertTrue(failed);
        }
    }

    @Test
    public void updatePhoneIllegalArgumentExceptionTest() throws Exception {

        CurriculumVitae cv = new CurriculumVitaeImp(cvFileString);
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
        CurriculumVitae cv = new CurriculumVitaeImp(cvFileString);

        cv.hide("sidorov.petr@mail.net");

        Field field = cv.getClass().getDeclaredField("text");
        field.setAccessible(true);
        String text = (String) field.get(cv);
        Assert.assertTrue(text.equals(readFile("modules/TextHandling/CurriculumVitae/test/resource/hideTest.txt")));

        field = cv.getClass().getDeclaredField("hiddenInfo");
        field.setAccessible(true);
        Map<String, String> map = (Map<String, String>) field.get(cv);

        Assert.assertTrue(map.containsValue("sidorov.petr@mail.net"));
    }

    //TODO: ✔ fix bug - IllegalArgumentException for (916)700-6964 cv.hide("\\)\\)\\(");
    @Test
    public void hidePhoneTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(cvFileString);
        cv.hidePhone("999 123-45-67 ext.2345");
        cv.hidePhone("(916)700-6964");
        cv.hidePhone("256-1004");

        Field field = cv.getClass().getDeclaredField("text");
        field.setAccessible(true);
        String text = (String) field.get(cv);
        Assert.assertTrue(text.equals(readFile("modules/TextHandling/CurriculumVitae/test/resource/hidePhone.txt")));

        field = cv.getClass().getDeclaredField("hiddenInfo");
        field.setAccessible(true);
        Map<String, String> map = (Map<String, String>) field.get(cv);
        Assert.assertTrue(map.containsValue("999 123-45-67 ext.2345"));
        Assert.assertTrue(map.containsValue("256-1004"));
        Assert.assertTrue(map.containsValue("(916)700-6964"));

    }

    @Test
    public void unhideAllTest() throws Exception {
        CurriculumVitae cv = new CurriculumVitaeImp(cvFileString);

        Field field = cv.getClass().getDeclaredField("text");
        field.setAccessible(true);
        field.set(cv, readFile("modules/TextHandling/CurriculumVitae/test/resource/forUnhideAllTest.txt"));

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

        Assert.assertTrue(text.equals(cvFileString));
        Assert.assertEquals(3, counter);
    }
}

