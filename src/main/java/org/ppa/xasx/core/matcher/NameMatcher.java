package org.ppa.xasx.core.matcher;

import org.ppa.xasx.types.NodeReadWriter;

public class NameMatcher implements Matcher {

    public NameMatcher(String pattern) {
        this.pattern = pattern;
    }
    private String pattern;

    @Override
    public boolean match(Object o, NodeReadWriter readWriter) {
        String name = readWriter.getName(o);
        return name.equals(pattern);
    }

}
