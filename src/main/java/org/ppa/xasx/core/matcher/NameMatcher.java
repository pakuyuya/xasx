package org.ppa.xasx.core.matcher;

import org.ppa.xasx.core.ValueNode;
import org.ppa.xasx.core.validate.ValidateContext;

public class NameMatcher implements Matcher {
    private String pattern;

    public NameMatcher(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean match(ValueNode node) {
        final String name = node.getName();
        return pattern.equals(name);
    }

    @Override
    public void mapToNode(ValueNode dest, ValidateContext context) {
        dest.setName(pattern);
    }
}
