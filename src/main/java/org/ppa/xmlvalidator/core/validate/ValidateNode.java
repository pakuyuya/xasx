package org.ppa.xmlvalidator.core.validate;

import java.util.List;

/**
 * 各項目ごとの検証ルールを保持する
 */
public class ValidateNode {
    private List<ValidateNode> children;
    private List<Rule> rules;
    private List<Translate> translates;
    private List<Matcher> matchers;

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
}
