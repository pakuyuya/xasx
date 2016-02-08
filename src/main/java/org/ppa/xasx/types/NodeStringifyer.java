package org.ppa.xasx.types;

import java.util.Collection;

import org.ppa.xasx.core.NodeDefine;
import org.ppa.xasx.core.validate.ValidateContext;

public interface NodeStringifyer {
    public String convert(Collection<NodeDefine> define, ValidateContext context);
    public String convert(NodeDefine define, ValidateContext context);
}
