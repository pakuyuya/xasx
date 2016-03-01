package org.ppa.xasx.types;

import org.ppa.xasx.core.XasXException;
import org.ppa.xasx.core.validate.ValidateContext;

public interface ParameterCheckable {
    public void checkParameter(ValidateContext context) throws XasXException;
}
