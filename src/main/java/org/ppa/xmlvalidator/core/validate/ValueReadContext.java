package org.ppa.xmlvalidator.core.validate;

/**
 * 値を読み込む文脈
 */
public class ValueReadContext {

    private ValueMerger valueMerger;

    public ValueMerger getValueMerger() {
        return valueMerger;
    }

    public void setValueMerger(ValueMerger valueMerger) {
        this.valueMerger = valueMerger;
    }
}
