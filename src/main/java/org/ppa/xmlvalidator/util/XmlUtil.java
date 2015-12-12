package org.ppa.xmlvalidator.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 */
public class XmlUtil {

    static public XmlElementData readXmlElementData(Node node) {

        XmlElementData element = new XmlElementData();

        element.setName(node.getNodeName());

        NamedNodeMap attributes = node.getAttributes();
        for (int i=0, len=attributes.getLength(); i < len; ++i) {
            Node attr = attributes.item(i);
            element.getAttributes().put(attr.getNodeName(), attr.getNodeValue());
        }

        Node child = node.getFirstChild();
        while (child != null) {

            short type = child.getNodeType();
            switch(type) {
            case Node.CDATA_SECTION_NODE :
                element.getChildren().add(child.getNodeValue());
                break;
            case Node.ELEMENT_NODE :
                element.getChildren().add(readXmlElementData(child));
                break;
            case Node.TEXT_NODE:
                if (XmlValidatorStringUtil.isNotBlankAscii(child.getNodeValue())) {
                    element.getChildren().add(child.getNodeValue().trim());
                }
                break;
            }

            child = child.getNextSibling();
        }
        return element;
    }

    static public XmlElementData parseHierarchical(File file) throws SAXException, IOException, ParserConfigurationException {
        Document document = DocumentBuilderFactory
                   .newInstance()
                   .newDocumentBuilder()
                   .parse(file);

        Node node = document.getDocumentElement();
        return readXmlElementData(node);
    }
    static public XmlElementData parseHierarchical(InputStream is) throws SAXException, IOException, ParserConfigurationException {
        Document document = DocumentBuilderFactory
                   .newInstance()
                   .newDocumentBuilder()
                   .parse(is);

        Node node = document.getDocumentElement();
        return readXmlElementData(node);
    }
}
