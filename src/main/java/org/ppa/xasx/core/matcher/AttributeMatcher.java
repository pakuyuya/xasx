package org.ppa.xasx.core.matcher;

import static org.ppa.xasx.util.XasXStringUtil.*;

import org.ppa.xasx.core.ValueNode;
import org.ppa.xasx.core.validate.ValidateContext;

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
    public boolean match(ValueNode node) {
        String attribute = node.getAttribute(name);
        return equal(attribute, this.pattern);
    }

    @Override
    public void mapToNode(ValueNode dest, ValidateContext context) {
        dest.setAttribute(name, pattern);
    }
}
