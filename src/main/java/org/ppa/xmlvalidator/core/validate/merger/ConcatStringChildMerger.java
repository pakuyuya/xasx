package org.ppa.xmlvalidator.core.validate.merger;

import org.ppa.xmlvalidator.core.validate.ValueMerger;
import org.ppa.xmlvalidator.core.validate.ValueNode;

public class ConcatStringChildMerger implements ValueMerger {
    @Override
    public String mergeValue(ValueNode src) {
        StringBuilder sb = new StringBuilder();
        for (Object o : src.getChidren()) {
            if (o instanceof String) {
                sb.append((String)o);
            }
        }
        return sb.toString();
    }

}
