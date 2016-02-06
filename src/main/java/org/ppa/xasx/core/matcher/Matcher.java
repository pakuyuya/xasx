package org.ppa.xasx.core.matcher;

import org.ppa.xasx.types.NodeReadWriter;

/**
 * Nodeの値が正しいか検証する。
 */
public interface Matcher {
    public boolean match(Object o, NodeReadWriter readWriter);
}
