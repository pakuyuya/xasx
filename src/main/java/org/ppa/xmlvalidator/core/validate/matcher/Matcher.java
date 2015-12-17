package org.ppa.xmlvalidator.core.validate.matcher;

import org.ppa.xmlvalidator.core.validate.ValueNode;

/**
 * Nodeの値が正しいか検証する。
 */
public interface Matcher {
    public boolean match(ValueNode o);
}
