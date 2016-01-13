package org.ppa.xasx.core;

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
     * 値読み込みアダプタ
     */
    private ValueNodeReader valueNodeReader;

    /**
     * デフォルトの値読み込みアダプタ
     */
    private ValueNodeReader defaultValueNodeReader;

    /**
     * 値書き込みアダプタ
     */
    private ValueNodeWriter valueNodeWriter;

    /**
     * デフォルトの値書き込みアダプタ
     */
    private ValueNodeWriter defaultValueNodeWriter;


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

    public ValueNodeReader getValueNodeReader() {
        return valueNodeReader;
    }

    public void setValueNodeReader(ValueNodeReader valueNodeReader) {
        this.valueNodeReader = valueNodeReader;
    }

    public ValueNodeReader getDefaultValueNodeReader() {
        return defaultValueNodeReader;
    }

    public void setDefaultValueNodeReader(ValueNodeReader defaultValueNodeReader) {
        this.defaultValueNodeReader = defaultValueNodeReader;
    }

    public ValueNodeWriter getValueNodeWriter() {
        return valueNodeWriter;
    }

    public void setValueNodeWriter(ValueNodeWriter valueNodeWriter) {
        this.valueNodeWriter = valueNodeWriter;
    }

    public ValueNodeWriter getDefaultValueNodeWriter() {
        return defaultValueNodeWriter;
    }

    public void setDefaultValueNodeWriter(ValueNodeWriter defaultValueNodeWriter) {
        this.defaultValueNodeWriter = defaultValueNodeWriter;
    }

}
