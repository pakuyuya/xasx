package org.ppa.xmlvalidator.xml;

import org.ppa.xmlvalidator.core.validate.ValueNode;
import org.ppa.xmlvalidator.core.validate.ValueNodeReader;
import org.ppa.xmlvalidator.util.XmlElementData;
import org.ppa.xmlvalidator.util.XmlValidatorXmlUtil;
import org.w3c.dom.Node;

public class XmlValueNodeReader implements ValueNodeReader<Node> {

    @Override
    public ValueNode convertNode(String name, Node src) {

        XmlElementData elm = XmlValidatorXmlUtil.convertXmlElementData(src);

        ValueNode node = new ValueNode();
        node.setName(name);
        node.getAttributes().putAll(elm.getAttributes());

        String value = "";

        return node;
    }

    @Override
    public Node readChildNode(Node parent, String childName) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}
