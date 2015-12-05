package org.ppa.xmlvalidator.core.validate;

/**
 * Nodeの値が正しいか検証する。
 */
public interface Matcher {
    public boolean match(ValueNode o);
}
