package org.ppa.xasx.core;

public class ValueMakerHelper {

    public static int getChildrenCount(Object node, ValueNodeReader reader, ValueIOContext context) {
        return reader.getChildrenCount(node, context);
    }

    public static Object getChild(Object node, ValueNodeReader reader, int index, ValueIOContext context) {
        return reader.getChild(node, index, context);
    }

}
