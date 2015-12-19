package org.ppa.xmlvalidator.core.validate;

import java.util.List;
import java.util.Map;

/**
 * 検証対象をparseした結果を保持
 */
public class ValueNode {
    private String name;
    private String value;
    List<ValueNode> chidren;
    private Map<String, String> attributes;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public List<ValueNode> getChidren() {
        return chidren;
    }
    public void setChidren(List<ValueNode> chidren) {
        this.chidren = chidren;
    }
    public Map<String, String> getAttributes() {
        return attributes;
    }
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
