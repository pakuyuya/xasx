package org.ppa.xasx.types;

/**
 * ValueNodeからvalueを作成し返却する
 */
public interface ValueMaker {
    /**
     * ValueNodeからvalueをmergeする
     * @param src 対象のValueNode
     * @return value
     */
    public String make(Object node, NodeReadWriter readWriter);
}
