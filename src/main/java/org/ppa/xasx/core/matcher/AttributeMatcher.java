package org.ppa.xasx.core.matcher;

import static org.ppa.xasx.util.XasXStringUtil.*;

import org.ppa.xasx.types.NodeReadWriter;

/**
 * 属性マッチャー
 */
public class AttributeMatcher implements Matcher {
    private String name;
    private String pattern;

    public AttributeMatcher(String name, String pattern) {
        this.name = name;
        this.pattern = pattern;
    }

    @Override
    public boolean match(Object o, NodeReadWriter readWriter) {
        String attribute = readWriter.getAttribute(o, name);
        return equal(attribute, this.pattern);
    }
}
