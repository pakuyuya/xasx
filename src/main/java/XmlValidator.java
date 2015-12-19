import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.ppa.xmlvalidator.core.rulefileparser.RulefileParser;
import org.ppa.xmlvalidator.core.rulefileparser.XMLRulefileParser;
import org.ppa.xmlvalidator.core.validate.ValidateEngine;
import org.ppa.xmlvalidator.core.validate.ValidateNode;
import org.ppa.xmlvalidator.core.validate.ValueNode;
import org.ppa.xmlvalidator.core.validate.ValueNodeReader;
import org.ppa.xmlvalidator.xml.XmlValueNodeReader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class XmlValidator {

    private ValidateEngine validateEngine = new ValidateEngine();
    private ValueNodeReader<Node> valueNodeReader = new XmlValueNodeReader();
    private RulefileParser ruleFilePerser = new XMLRulefileParser();

    public Map<String, List<String>> validate(File xmlFile, File ruleFile){

        ValidateNode validateNode = ruleFilePerser.parse(ruleFile);

        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(xmlFile);
            Node node = document.getDocumentElement();

            ValueNode valueNode = valueNodeReader.convertNode(node.getNodeName(), node);

            return validateEngine.validateRecursive(valueNode, validateNode);

        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

    }
}
