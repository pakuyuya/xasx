package org.ppa.xasx.simple;

import java.util.HashMap;
import java.util.Map;

public class SimpleNode {
    private String name;
    private Map<String, String> attributes = new HashMap<String, String>();
    private String value;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Map<String, String> getAttributes() {
        return attributes;
    }
    public void setAttributes(Map<String, String> node) {
        this.attributes = node;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
