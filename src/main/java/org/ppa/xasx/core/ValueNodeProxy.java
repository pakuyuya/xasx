package org.ppa.xasx.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ppa.xasx.types.NodeReadWriter;
import org.ppa.xasx.types.ValueMaker;
import org.ppa.xasx.util.ValueCache;

/**
 * valuenodeのProxy。Cache付き
 */
public class ValueNodeProxy implements ValueNode {
    private Object node;
    private NodeReadWriter readWriter;
    private ValueMaker valueMaker;

    private ValueCache<String> nameCache;
    private ValueCache<String> valueCache;
    private Map<String, String> attributesCache;
    private List<Object> childrenCache;

    public ValueNodeProxy(Object node, NodeReadWriter readWriter, ValueMaker valueMaker) {
        this.node = node;
        this.readWriter = readWriter;
        this.valueMaker = valueMaker;
    }

    @Override
    public boolean isValue() {
        return readWriter.isValue(node);
    }

    @Override
    public String getName() {
        if (nameCache == null) {
            nameCache = new ValueCache<String>(readWriter.getName(node));
        }
        return nameCache.get();
    }

    @Override
    public void setName(String value) {
        readWriter.setName(node, value);

        if (nameCache == null) {
            nameCache = new ValueCache<String>();
        }
        nameCache.set(value);
    }

    @Override
    public String getValue() {
        if (valueCache == null) {
            valueCache = new ValueCache<String>(valueMaker.make(node, readWriter));
        }
        return valueCache.get();
    }

    @Override
    public void setValue(String value) {
        readWriter.setValue(node, value);

        if (valueCache == null) {
            valueCache = new ValueCache<String>();
        }
        valueCache.set(value);
    }

    @Override
    public List<String> getAttributeNames() {
        if (attributesCache == null) {
            constructAttributesCache();
        }
        return new ArrayList<String>(attributesCache.keySet());
    }

    @Override
    public int getAttributesCount() {
        if (attributesCache == null) {
            constructAttributesCache();
        }
        return attributesCache.size();
    }

    @Override
    public String getAttribute(String name) {
        if (attributesCache == null) {
            constructAttributesCache();
        }
        return (String) attributesCache.get(name);
    }

    @Override
    public void setAttribute(String name, String value) {
        if (attributesCache == null) {
            constructAttributesCache();
        }
        attributesCache.put(name, value);
        readWriter.setAttribute(node, name, value);
    }

    @Override
    public Object getChild(int index) {
        if (childrenCache == null) {
            constructChildrenCache();
        }
        return childrenCache.get(index);
    }

    @Override
    public int getChildrenCount() {
        if (childrenCache == null) {
            constructChildrenCache();
        }
        return childrenCache.size();
    }

    /**
     * @param valueMaker セットする valueMaker
     */
    public void setValueMaker(ValueMaker valueMaker) {
        this.valueMaker = valueMaker;
    }

    private void constructAttributesCache() {
        attributesCache = new HashMap<String, String>();

        List<String> names = readWriter.getAttributeNames(node);
        for (String name : names) {
            attributesCache.put(name, readWriter.getAttribute(node, name));
        }
    }

    private void constructChildrenCache() {
        childrenCache = new ArrayList<Object>();

        for (int i=0, len=readWriter.getChildrenCount(node); i<len; i++) {
            childrenCache.add(readWriter.getChild(node, i));
        }
    }
}
