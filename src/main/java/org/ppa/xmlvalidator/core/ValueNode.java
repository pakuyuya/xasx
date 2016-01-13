package org.ppa.xmlvalidator.core;

import java.util.List;

/**
 * ValueNodeのインタフェース
 */
public interface ValueNode {
    public String getName(ValueIOContext context);
    public void setName(String name, ValueIOContext context);

    public String getValue(ValueIOContext context);
    public void setValue(String value, ValueIOContext context);

    public List<String> getAttributeNames(ValueIOContext context);
    public int getAttributesCount(ValueIOContext context);

    public String getAttribute(String name, ValueIOContext context);
    public void setAttribute(String name, String value, ValueIOContext context);

    public Object getChild(int index, ValueIOContext context);
    public int getChildrenCount(ValueIOContext context);

}
