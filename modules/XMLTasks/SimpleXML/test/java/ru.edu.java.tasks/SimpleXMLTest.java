package ru.edu.java.tasks;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class SimpleXMLTest {

    @Test
    public void createXMTest() {
        SimpleXML xml = new SimpleXMLImp();
        Assert.assertTrue(xml.createXML("name", "Tom").equals("<name>Tom</name>"));
        Assert.assertTrue(xml.createXML("tag", "<body>").equals("<tag>&lt;body&gt;</tag>"));
    }

    @Test
    public void parseRootElementTest() throws FileNotFoundException, SAXException {
        InputStream in = new FileInputStream("modules/XMLTasks/SimpleXML/test/resource/test.xml");
        SimpleXML xml = new SimpleXMLImp();
        Assert.assertTrue(xml.parseRootElement(in).equals("doc"));
    }

    @Test
    public void parseRootElementWrongTest() throws FileNotFoundException {
        InputStream in = new FileInputStream("modules/XMLTasks/SimpleXML/test/resource/wrong.xml");
        boolean fail = false;
        try {
            SimpleXML xml = new SimpleXMLImp();
            xml.parseRootElement(in);
        } catch (SAXException e) {
            fail = true;

        } finally {
            Assert.assertTrue(fail);
        }
    }
}
