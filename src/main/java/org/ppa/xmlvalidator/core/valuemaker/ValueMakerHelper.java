package org.ppa.xmlvalidator.core.valuemaker;

import org.ppa.xmlvalidator.core.ValueIOContext;
import org.ppa.xmlvalidator.core.ValueNodeReader;

public class ValueMakerHelper {

    public static int getChildrenCount(Object node, ValueNodeReader reader, ValueIOContext context) {
        return reader.getChildrenCount(node, context);
    }

    public static Object getChild(Object node, ValueNodeReader reader, int index, ValueIOContext context) {
        return reader.getChild(node, index, context);
    }

}
