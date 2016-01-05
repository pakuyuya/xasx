package org.ppa.xmlvalidator.xml;

import org.ppa.xmlvalidator.core.validate.ValueNode;
import org.ppa.xmlvalidator.core.validate.ValueNodeReader;
import org.ppa.xmlvalidator.core.validate.ValueReadContext;
import org.ppa.xmlvalidator.util.XmlElementData;
import org.ppa.xmlvalidator.util.XmlValidatorXmlUtil;
import org.w3c.dom.Node;

public class XmlValueNodeReader implements ValueNodeReader<Node> {

    @Override
    public ValueNode convertNode(String name, Node src, ValueReadContext context) {

        if (src.getNodeType() != Node.ELEMENT_NODE) {
            return null;
        }

        XmlElementData elm = XmlValidatorXmlUtil.convertXmlElementData(src);

        ValueNode node = new ValueNode();
        node.setName(name);
        node.getAttributes().putAll(elm.getAttributes());

        for(int i=0; i<src.getChildNodes().getLength(); i++) {
           Node child = src.getChildNodes().item(i);
           ValueNode childNode = convertNode(child.getNodeName(), child, context);
           if (childNode != null) {
               node.getChidren().add(childNode);
           }
        }

        String value = context.getValueMerger().mergeValue(node);
        node.setValue(value);

        return node;
    }
}
