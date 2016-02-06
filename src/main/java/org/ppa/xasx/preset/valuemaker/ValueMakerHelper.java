package org.ppa.xasx.preset.valuemaker;

import org.ppa.xasx.types.NodeReadWriter;

public class ValueMakerHelper {

    public static int getChildrenCount(Object node, NodeReadWriter reader) {
        return reader.getChildrenCount(node);
    }

    public static Object getChild(Object node, NodeReadWriter reader, int index) {
        return reader.getChild(node, index);
    }

}
