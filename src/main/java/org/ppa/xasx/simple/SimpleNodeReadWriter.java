package org.ppa.xasx.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ppa.xasx.types.NodeReadWriter;

import static org.ppa.xasx.util.XasXLangUtil.*;

public class SimpleNodeReadWriter implements NodeReadWriter {

    @Override
    public String getName(Object node) {
        SimpleNode n = cast(node);
        return n.getName();
    }

    @Override
    public String getValue(Object node) {
        SimpleNode n = cast(node);
        return n.getValue();
    }

    @Override
    public int getChildrenCount(Object node) {
        return 0;
    }

    @Override
    public Object getChild(Object node, int index) {
        return null;
    }

    @Override
    public List<String> getAttributeNames(Object node) {
        SimpleNode n = cast(node);
        Set<String> keyset = n.getAttributes().keySet();
        return new ArrayList<>(keyset);
    }

    @Override
    public String getAttribute(Object node, String name) {
        SimpleNode n = cast(node);
        return n.getAttributes().get(name);
    }

    @Override
    public void setName(Object node, String value) {
        SimpleNode n = cast(node);
        n.setName(value);
    }

    @Override
    public void setValue(Object node, String value) {
        SimpleNode n = cast(node);
        n.setValue(value);
    }

    @Override
    public void setAttribute(Object node, String name, String value) {
        SimpleNode n = cast(node);
        n.getAttributes().put(name, value);
    }

}
