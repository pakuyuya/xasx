package org.ppa.xmlvalidator.core;

import java.util.List;

import org.ppa.xmlvalidator.core.matcher.Matcher;

/**
 * 各項目ごとの検証ルールを保持する
 */
public class NodeDefine {
    /**
     * 子要素のNodeDefineのリスト
     */
    private List<NodeDefine> children;

    /**
     * 自ノードに対する検証ルール
     */
    private List<Rule> rules;

    /**
     * 自ノードに対する事前変換処理
     */
    private List<Translate> translates;

    /**
     * ノードとのマッチングルール
     */
    private List<Matcher> matchers;

    /**
     * 自ノードに対する{@link ValueMaker}の指定。nullの場合、デフォルト値が使われる。
     */
    private ValueMaker valueMaker;

    /**
     * 自ノードに対する{@link ValueNodeReader}の指定。nullの場合、デフォルト値が使われる。
     */
    private ValueNodeReader valueNodeReader;

    /**
     * 自ノードに対する{@link valueNodeWriter}の指定。nullの場合、デフォルト値が使われる。
     */
    private ValueNodeWriter valueNodeWriter;

    public List<NodeDefine> getChildren() {
        return children;
    }
    public void setChildren(List<NodeDefine> children) {
        this.children = children;
    }
    public List<Rule> getRules() {
        return rules;
    }
    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
    public List<Translate> getTranslates() {
        return translates;
    }
    public void setTranslates(List<Translate> translates) {
        this.translates = translates;
    }
    public List<Matcher> getMatchers() {
        return matchers;
    }
    public void setMatchers(List<Matcher> matchers) {
        this.matchers = matchers;
    }
    public ValueMaker getValueMaker() {
        return valueMaker;
    }
    public void setValueMaker(ValueMaker merger) {
        this.valueMaker = merger;
    }
    public ValueNodeReader getValueNodeReader() {
        return valueNodeReader;
    }
    public void setValueNodeReader(ValueNodeReader valueNodeReader) {
        this.valueNodeReader = valueNodeReader;
    }
    public ValueNodeWriter getValueNodeWriter() {
        return valueNodeWriter;
    }
    public void setValueNodeWriter(ValueNodeWriter valueNodeWriter) {
        this.valueNodeWriter = valueNodeWriter;
    }
}
