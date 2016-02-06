package org.ppa.xasx.core;

import java.util.List;

/**
 * ValueNodeのインタフェース
 */
public interface ValueNode {
    public String getName();
    public void setName(String name);

    public String getValue();
    public void setValue(String value);

    public List<String> getAttributeNames();
    public int getAttributesCount();

    public String getAttribute(String name);
    public void setAttribute(String name, String value);

    public Object getChild(int index);
    public int getChildrenCount();

}
