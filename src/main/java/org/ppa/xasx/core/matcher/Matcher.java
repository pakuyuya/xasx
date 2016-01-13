package org.ppa.xasx.core.matcher;

import org.ppa.xasx.core.ValueIOContext;

/**
 * Nodeの値が正しいか検証する。
 */
public interface Matcher {
    public boolean match(Object o, ValueIOContext context);
}
