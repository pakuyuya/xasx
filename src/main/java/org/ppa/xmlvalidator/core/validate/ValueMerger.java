package org.ppa.xmlvalidator.core.validate;


/**
 * ValueNodeからvalueをmergeし返却する
 */
public interface ValueMerger {
    /**
     * ValueNodeからvalueをmergeする
     * @param src 対象のValueNode
     * @return value
     */
    public String mergeValue(ValueNode src);
}
