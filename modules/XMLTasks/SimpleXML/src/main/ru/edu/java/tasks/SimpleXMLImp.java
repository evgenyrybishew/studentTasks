package ru.edu.java.tasks;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class SimpleXMLImp implements SimpleXML {
    @Override
    public String createXML(String tagName, String textNode) {
        StringWriter writer = new StringWriter();
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element element = doc.createElement(tagName);
            Node text = doc.createTextNode(textNode);
            element.appendChild(text);
            doc.appendChild(element);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "YES");
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    @Override
    public String parseRootElement(InputStream xmlStream) throws SAXException {
        final String[] root = new String[1];

        DefaultHandler handler = new DefaultHandler() {
            boolean isRoot = false;

            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if (!isRoot) {
                    isRoot = true;
                    root[0] = qName;
                }
            }

        };

        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(xmlStream, handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root[0];
    }
}
