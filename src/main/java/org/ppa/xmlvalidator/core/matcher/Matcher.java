package org.ppa.xmlvalidator.core.matcher;

import org.ppa.xmlvalidator.core.ValueIOContext;

/**
 * Nodeの値が正しいか検証する。
 */
public interface Matcher {
    public boolean match(Object o, ValueIOContext context);
}
