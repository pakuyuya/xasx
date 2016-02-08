package org.ppa.xasx.preset.valuemaker;

import org.ppa.xasx.types.ValueMaker;
import org.ppa.xasx.types.NodeReadWriter;

/**
 * ValueMeakerの実装。<br>
 * valueをそのまま返却する。
 */
public class SimpleValueMaker implements ValueMaker {
    @Override
    public String make(Object node, NodeReadWriter readWriter) {
        return readWriter.getValue(node);
    }
}
