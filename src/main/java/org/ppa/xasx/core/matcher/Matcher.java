package org.ppa.xasx.core.matcher;

import org.ppa.xasx.core.ValueNode;
import org.ppa.xasx.core.validate.ValidateContext;

/**
 * ValueNodeが条件に合致しているか検証する。
 */
public interface Matcher {
    public boolean match(ValueNode node);
    public void mapToNode(ValueNode dest, ValidateContext context);
}
