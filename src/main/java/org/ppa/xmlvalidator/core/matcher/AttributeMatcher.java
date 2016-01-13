package org.ppa.xmlvalidator.core.matcher;

import static org.ppa.xmlvalidator.util.XmlValidatorStringUtil.*;

import org.ppa.xmlvalidator.core.ValueIOContext;

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
    public boolean match(Object o, ValueIOContext context) {
        String attribute = context.getValueNodeReader().getAttribute(o, name, context);
        return equal(attribute, this.pattern);
    }
}
