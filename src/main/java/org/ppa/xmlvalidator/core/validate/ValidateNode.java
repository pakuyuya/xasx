package org.ppa.xmlvalidator.core.validate;

import java.util.List;

import org.ppa.xmlvalidator.core.validate.matcher.Matcher;

/**
 * 各項目ごとの検証ルールを保持する
 */
public class ValidateNode {
    private List<ValidateNode> children;
    private List<Rule> rules;
    private List<Translate> translates;
    private List<Matcher> matchers;
    private ValueMerger merger;

    public List<ValidateNode> getChildren() {
        return children;
    }
    public void setChildren(List<ValidateNode> children) {
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
    public ValueMerger getMerger() {
        return merger;
    }
    public void setMerger(ValueMerger merger) {
        this.merger = merger;
    }
}
