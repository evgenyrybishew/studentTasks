package ru.edu.java.tasks;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CheckerTest {
    Checker checker = new CheckerImp();

    @Test
    public void checkAccordancePLSQLTest() {

        Pattern pattern = checker.getPLSQLNamesPattern();
        boolean result = checker.checkAccordance("user_id", pattern);
        Assert.assertTrue(result);

        result = checker.checkAccordance("_user_id", pattern);
        Assert.assertFalse(result);

        result = checker.checkAccordance("Summ_in_$", pattern);
        Assert.assertTrue(result);
    }


    @Test
    public void checkAccordanceURLTest() {

        Pattern pattern = checker.getHrefURLPattern();
        boolean result = checker.checkAccordance("<a href = google.com>google</a>", pattern);
        Assert.assertTrue(result);

        result = checker.checkAccordance("<a href=\"test.html\">This line contains <strong>bold</strong> text.<a>", pattern);
        Assert.assertTrue(result);

        result = checker.checkAccordance("<img href=\"img.png\"/>", pattern);
        Assert.assertFalse(result);

        result = checker.checkAccordance("<a href=test.html>index.html<a>", pattern);
        Assert.assertTrue(result);

        result = checker.checkAccordance("<A HREF=test.html>index.html<A>", pattern);
        Assert.assertTrue(result);

        result = checker.checkAccordance("<A\tHREF\t=\ttest.html\t>index.html<A>", pattern);
        Assert.assertTrue(result);

        result = checker.checkAccordance("<A  HREF  =  test.html  >index.html<A>", pattern);
        Assert.assertFalse(result);

        result = checker.checkAccordance("<a\nhref=test.html\n>index.html<a>", pattern);
        Assert.assertTrue(result);

        result = checker.checkAccordance("<a\nhref=test.html\n\n>index.html<a>", pattern);
        Assert.assertFalse(result);

    }

    //TODO: ✓ тест если email(аккаунт) более 22 символов
    //TODO: ✓ тесты на негативный исход
    @Test
    public void checkAccordanceEmailTest() {

        Pattern pattern = checker.getEMailPattern();
        boolean result = checker.checkAccordance("user@maiil.spb.ru", pattern);
        Assert.assertTrue(result);

        result = checker.checkAccordance("dev_user-93@mail.com", pattern);
        Assert.assertTrue(result);

        result = checker.checkAccordance("user@number.1th.net", pattern);
        Assert.assertTrue(result);

        result = checker.checkAccordance("user23@mail.a-d.ru", pattern);
        Assert.assertTrue(result);

        result = checker.checkAccordance("my-test-post@mail.java.sber.ru", pattern);
        Assert.assertTrue(result);

        result = checker.checkAccordance("1user@mail.com", pattern);
        Assert.assertTrue(result);

        result = checker.checkAccordance("wrongAmail.q.ru", pattern);
        Assert.assertFalse(result);

        result = checker.checkAccordance("myMail.ru", pattern);
        Assert.assertFalse(result);

        result = checker.checkAccordance("aaaaaaaaaaaaaaaaaaaaaaaaa@mail.org", pattern);
        Assert.assertFalse(result);

    }

    @Test
    public void checkAccordanceNullTest() {
        boolean result = checker.checkAccordance(null, null);
        Assert.assertTrue(result);
    }


    @Test
    public void checkAccordanceIllegalArgumentExceptionTest() {

        boolean ex = false;
        try {
            boolean result = checker.checkAccordance("dev_user-93@mail.com", null);
        } catch (IllegalArgumentException e) {
            ex = true;
        } finally {
            Assert.assertTrue(ex);
        }
    }


    private StringBuffer stringBufferCreater(String path) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(path));
        StringBuffer source = new StringBuffer();
        for (String str : lines)
            source.append("\n " + str);
        return source;
    }

    private List<String> doStringarray(StringBuffer stringBuffer) {

        ArrayList<String> result = new ArrayList<>();

        for (String str : stringBuffer.toString().split("\n")) {
            if (!str.equals(""))
                result.add(str.trim());
        }
        return result;
    }


    @Test
    public void fetchAllTemplatesEmailsTest() throws IOException {

        StringBuffer emails = stringBufferCreater("modules/JavaAPITasks/Checker/src/test/resource/emails.txt");
        StringBuffer emailsMatch = stringBufferCreater("modules/JavaAPITasks/Checker/src/test/resource/emailsMatch.txt");

        ArrayList<String> result = (ArrayList<String>) checker.fetchAllTemplates(emails, checker.getEMailPattern());
        ArrayList<String> match = (ArrayList<String>) doStringarray(emailsMatch);

        Assert.assertArrayEquals(result.toArray(), match.toArray());
    }


    @Test
    public void fetchAllTemplatesPLSQLTest() throws IOException {

        StringBuffer plsql = stringBufferCreater("modules/JavaAPITasks/Checker/src/test/resource/PLSQL.txt");
        StringBuffer plsqlMatch = stringBufferCreater("modules/JavaAPITasks/Checker/src/test/resource/PLSQLMatch.txt");

        ArrayList<String> result = (ArrayList<String>) checker.fetchAllTemplates(plsql, checker.getPLSQLNamesPattern());
        ArrayList<String> match = (ArrayList<String>) doStringarray(plsqlMatch);

        Assert.assertArrayEquals(result.toArray(), match.toArray());
    }

    @Test
    public void fetchAllTemplatesURLTest() throws IOException {
        StringBuffer urls = stringBufferCreater("modules/JavaAPITasks/Checker/src/test/resource/urls.html");
        StringBuffer urlsMatch = stringBufferCreater("modules/JavaAPITasks/Checker/src/test/resource/urlsMatch.txt");
        ArrayList<String> result = (ArrayList<String>) checker.fetchAllTemplates(urls, checker.getHrefURLPattern());
        ArrayList<String> match = (ArrayList<String>) doStringarray(urlsMatch);
        Assert.assertArrayEquals(result.toArray(), match.toArray());

    }
}
