package org.ppa.xasx.preset.valuemaker;

import org.ppa.xasx.types.ValueMaker;
import org.ppa.xasx.types.NodeReadWriter;

/**
 * ValueMeakerの実装。<br>
 * 子要素がString型のみの場合にvalueをMergeして返却する。
 */
public class MergeStringChildrenValueMaker implements ValueMaker {
    private boolean nullIfNonStringExists = false;

    @Override
    public String make(Object node, NodeReadWriter readWriter) {
        StringBuffer sb = new StringBuffer();

        int stringCnt = 0;

        int len = ValueMakerHelper.getChildrenCount(node, readWriter);
        for (int i = 0; i < len; ++i) {
            Object o = ValueMakerHelper.getChild(node, readWriter, i);
            if (!(o instanceof String)) {
                if (nullIfNonStringExists) {
                    return null;
                }
                continue;
            }
            sb.append((String)o);
            stringCnt++;
        }

        return (stringCnt > 0) ? sb.toString() : null;
    }

    /**
     * @return nullIfNonStringExists
     */
    public boolean isNullIfNonStringExists() {
        return nullIfNonStringExists;
    }

    /**
     * @param nullIfNonStringExists セットする nullIfNonStringExists
     */
    public void setNullIfNonStringExists(boolean nullIfNonStringExists) {
        this.nullIfNonStringExists = nullIfNonStringExists;
    }
}
