package org.ppa.xmlvalidator.core.validate.matcher;

import static org.ppa.xmlvalidator.util.XmlValidatorStringUtil.*;

import org.ppa.xmlvalidator.core.validate.ValueNode;

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
    public boolean match(ValueNode o) {
        String pattern = o.getAttributes().get(name);
        return equal(pattern, this.pattern);
    }
}
