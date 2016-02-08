package org.ppa.xasx.core;

import java.util.ArrayList;
import java.util.List;

import org.ppa.xasx.core.matcher.Matcher;
import org.ppa.xasx.types.Rule;
import org.ppa.xasx.types.Translate;
import org.ppa.xasx.types.ValueMaker;
import org.ppa.xasx.types.NodeReadWriter;

/**
 * 各項目ごとの検証ルールを保持する
 */
public class NodeDefine {
    /**
     * 子要素のNodeDefineのリスト
     */
    private List<NodeDefine> children = new ArrayList<>();

    /**
     * 自ノードに対する検証ルール
     */
    private List<Rule> rules = new ArrayList<>();

    /**
     * 自ノードに対する事前変換処理
     */
    private List<Translate> translates = new ArrayList<>();

    /**
     * ノードとのマッチングルール
     */
    private List<Matcher> matchers = new ArrayList<>();

    /**
     * 自ノードに対する{@link ValueMaker}の指定。nullの場合、デフォルト値が使われる。
     */
    private ValueMaker valueMaker;

    /**
     * 自ノードに対する{@link NodeReadWriter}の指定。nullの場合、デフォルト値が使われる。
     */
    private NodeReadWriter valueNodeReader;


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
    public NodeReadWriter getValueNodeReader() {
        return valueNodeReader;
    }
    public void setValueNodeReader(NodeReadWriter valueNodeReader) {
        this.valueNodeReader = valueNodeReader;
    }
}
