package org.ppa.xmlvalidator.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Xml要素のParse結果
 */
public class XmlElementData {
    private String name;
    private Map<String, String> attributes = new HashMap<String, String>();
    private List<Object> childs = new ArrayList<Object>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Map<String, String> getAttributes() {
        return attributes;
    }
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
    public List<Object> getChildren() {
        return childs;
    }
    public void setChilds(List<Object> childs) {
        this.childs = childs;
    }
}
