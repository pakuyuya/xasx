package org.ppa.xasx.xml;

import java.util.ArrayList;
import java.util.List;

import org.ppa.xasx.types.NodeReadWriter;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * XmlNodeからAttributeを取得する。
 */
public class XmlNodeReadWriter implements NodeReadWriter {

    @Override
    public String getName(Object node) {
        Node n = (Node)node;

        if (n.getNodeType() == Node.ELEMENT_NODE) {
            return n.getNodeName();
        }
        return null;
    }

    @Override
    public String getValue(Object node) {
        Node n = (Node)node;
        return n.getNodeValue();
    }

    @Override
    public int getChildrenCount(Object node) {
        Node n = (Node)node;
        return n.getChildNodes().getLength();
    }

    @Override
    public Object getChild(Object node, int index) {
        Node n = (Node)node;
        return n.getChildNodes().item(index);
    }

    @Override
    public List<String> getAttributeNames(Object node) {
        NamedNodeMap attributes = ((Node)node).getAttributes();
        int len = attributes.getLength();
        List<String> names = new ArrayList<String>(len);

        for (int i = 0; i < len; i++) {
            names.add(i, attributes.item(i).getNodeName());
        }
        return names;
    }

    @Override
    public String getAttribute(Object node, String name) {
        // TODO 自動生成されたメソッド・スタブ
        return ((Node)node).getAttributes().getNamedItem(name).getNodeValue();
    }
    @Override
    public void setName(Object node, String value) {
        // do nothing.
    }

    @Override
    public void setValue(Object node, String value) {
        // do nothing.
    }

    @Override
    public void setAttribute(Object node, String name, String value) {
        // TODO 自動生成されたメソッド・スタブ
        ((Node)node).getAttributes().getNamedItem(name).getNodeValue();
    }
}
