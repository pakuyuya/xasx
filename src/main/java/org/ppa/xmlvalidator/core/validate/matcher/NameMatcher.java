package org.ppa.xmlvalidator.core.validate.matcher;

import org.ppa.xmlvalidator.core.validate.ValueNode;

public class NameMatcher implements Matcher {

    public NameMatcher(String pattern) {
        this.pattern = pattern;
    }
    private String pattern;

    @Override
    public boolean match(ValueNode o) {
        return o.equals(pattern);
    }

}
