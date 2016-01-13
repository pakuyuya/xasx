package org.ppa.xmlvalidator.xml;

import org.ppa.xmlvalidator.core.ValueIOContext;
import org.ppa.xmlvalidator.core.ValueNodeWriter;
import org.w3c.dom.Node;

/**
 * XmlNodeに各種値を設定するAdapterクラス。
 */
public class XmlNodeValueNodeWriter implements ValueNodeWriter {

    @Override
    public void setName(Object node, String value, ValueIOContext context) {
        // do nothing.
    }

    @Override
    public void setValue(Object node, String value, ValueIOContext context) {
        // do nothing.
    }

    @Override
    public void setAttribute(Object node, String name, String value, ValueIOContext context) {
        // TODO 自動生成されたメソッド・スタブ
        ((Node)node).getAttributes().getNamedItem(name).getNodeValue();
    }
}
