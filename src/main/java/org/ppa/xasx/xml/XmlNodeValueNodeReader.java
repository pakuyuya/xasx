package org.ppa.xasx.xml;

import java.util.ArrayList;
import java.util.List;

import org.ppa.xasx.core.ValueIOContext;
import org.ppa.xasx.core.ValueNodeReader;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * XmlNodeからAttributeを取得する。
 */
public class XmlNodeValueNodeReader implements ValueNodeReader {

    @Override
    public String getName(Object node, ValueIOContext context) {
        Node n = (Node)node;

        if (n.getNodeType() == Node.ELEMENT_NODE) {
            return n.getNodeName();
        }
        return null;
    }

    @Override
    public String getValue(Object node, ValueIOContext context) {
        return (context.getValueMaker() != null) ? context.getValueMaker().make(node, this, context) : null;
    }

    @Override
    public int getChildrenCount(Object node, ValueIOContext context) {
        Node n = (Node)node;
        return n.getChildNodes().getLength();
    }

    @Override
    public Object getChild(Object node, int index, ValueIOContext context) {
        Node n = (Node)node;
        return n.getChildNodes().item(index);
    }

    @Override
    public List<String> getAttributeNames(Object node, ValueIOContext context) {
        NamedNodeMap attributes = ((Node)node).getAttributes();
        int len = attributes.getLength();
        List<String> names = new ArrayList<String>(len);

        for (int i = 0; i < len; i++) {
            names.set(i, attributes.item(i).getNodeName());
        }
        return names;
    }

    @Override
    public String getAttribute(Object node, String name, ValueIOContext context) {
        // TODO 自動生成されたメソッド・スタブ
        return ((Node)node).getAttributes().getNamedItem(name).getNodeValue();
    }
}
