package org.ppa.xasx.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ppa.xasx.util.ValueCache;

/**
 * valuenodeのProxy。Cache付き
 */
public class ValueNodeProxy implements ValueNode {
    private Object node;
    private ValueNodeReader reader;
    private ValueNodeWriter writer;

    private ValueCache<String> nameCache;
    private ValueCache<String> valueCache;
    private Map<String, String> attributesCache;
    private List<Object> childrenCache;

    public ValueNodeProxy(Object node, ValueNodeReader reader, ValueNodeWriter writer) {
        this.node = node;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public String getName(ValueIOContext context) {
        if (nameCache == null) {
            nameCache = new ValueCache<String>(reader.getName(node, context));
        }
        return nameCache.get();
    }

    @Override
    public void setName(String value, ValueIOContext context) {
        writer.setName(node, value, context);

        if (nameCache == null) {
            nameCache = new ValueCache<String>();
        }
        nameCache.set(value);
    }

    @Override
    public String getValue(ValueIOContext context) {
        if (valueCache == null) {
            valueCache = new ValueCache<String>(reader.getValue(node, context));
        }
        return valueCache.get();
    }

    @Override
    public void setValue(String value, ValueIOContext context) {
        writer.setValue(node, value, context);

        if (valueCache == null) {
            valueCache = new ValueCache<String>();
        }
        valueCache.set(value);
    }

    @Override
    public List<String> getAttributeNames(ValueIOContext context) {
        if (attributesCache == null) {
            constructAttributesCache(context);
        }
        return new ArrayList<String>(attributesCache.keySet());
    }

    @Override
    public int getAttributesCount(ValueIOContext context) {
        if (attributesCache == null) {
            constructAttributesCache(context);
        }
        return attributesCache.size();
    }


    @Override
    public String getAttribute(String name, ValueIOContext context) {
        if (attributesCache == null) {
            constructAttributesCache(context);
        }
        return (String) attributesCache.get(name);
    }

    @Override
    public void setAttribute(String name, String value, ValueIOContext context) {
        if (attributesCache == null) {
            constructAttributesCache(context);
        }
        attributesCache.put(name, value);
        writer.setAttribute(node, name, value, context);
    }

    private void constructAttributesCache(ValueIOContext context) {
        attributesCache = new HashMap<String, String>();

        List<String> names = reader.getAttributeNames(node, context);
        for (String name : names) {
            attributesCache.put(name, reader.getAttribute(names, name, context));
        }
    }

    @Override
    public Object getChild(int index, ValueIOContext context) {
        if (childrenCache == null) {
            constructChildrenCache(context);
        }
        return childrenCache.get(index);
    }

    @Override
    public int getChildrenCount(ValueIOContext context) {
        if (childrenCache == null) {
            constructChildrenCache(context);
        }
        return childrenCache.size();
    }

    private void constructChildrenCache(ValueIOContext context) {
        childrenCache = new ArrayList<Object>();

        for (int i=0, len=reader.getChildrenCount(node, context); i<len; i++) {
            childrenCache.add(reader.getChild(context, i, context));
        }
    }
}
