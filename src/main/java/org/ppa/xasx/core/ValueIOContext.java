package org.ppa.xasx.core;

import org.ppa.xasx.types.ValueMaker;
import org.ppa.xasx.types.NodeReadWriter;


/**
 * 値を読み込む文脈
 */
public class ValueIOContext {

    /**
     * 親からもらった名前
     */
    private String givenName;

    /**
     * 値自動設定コンポーネント
     */
    private ValueMaker valueMaker;

    /**
     * デフォルトの値自動設定コンポーネント
     */
    private ValueMaker defaultValueMaker;

    /**
     * 値読み書きアダプタ
     */
    private NodeReadWriter valueNodeReaderWriter;

    /**
     * デフォルトの値読み書きアダプタ
     */
    private NodeReadWriter defaultValueNodeReader;


    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public ValueMaker getValueMaker() {
        return valueMaker;
    }

    public void setValueMaker(ValueMaker valueMerger) {
        this.valueMaker = valueMerger;
    }

    public ValueMaker getDefaultValueMaker() {
        return defaultValueMaker;
    }

    public void setDefaultValueMaker(ValueMaker defaultValueResolver) {
        this.defaultValueMaker = defaultValueResolver;
    }

    public NodeReadWriter getValueNodeReaderWriter() {
        return valueNodeReaderWriter;
    }

    public void setValueNodeReaderWriter(NodeReadWriter valueNodeReader) {
        this.valueNodeReaderWriter = valueNodeReader;
    }

    public NodeReadWriter getDefaultValueNodeReader() {
        return defaultValueNodeReader;
    }

    public void setDefaultValueNodeReader(NodeReadWriter defaultValueNodeReader) {
        this.defaultValueNodeReader = defaultValueNodeReader;
    }
}
