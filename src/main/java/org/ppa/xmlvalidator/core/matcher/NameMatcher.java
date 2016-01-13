package org.ppa.xmlvalidator.core.matcher;

import org.ppa.xmlvalidator.core.ValueIOContext;

public class NameMatcher implements Matcher {

    public NameMatcher(String pattern) {
        this.pattern = pattern;
    }
    private String pattern;

    @Override
    public boolean match(Object o, ValueIOContext context) {
        String name = context.getValueNodeReader().getName(o, context);
        return name.equals(pattern);
    }

}
