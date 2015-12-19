package org.ppa.xmlvalidator.core.validate;

import java.util.ArrayList;
import java.util.List;

import org.ppa.xmlvalidator.core.validate.matcher.Matcher;

/**
 * 検証のメインロジック
 */
abstract public class ValidateEngine<T> {

    /**
     * 与えられたValueNodeに対し、ValidateNodeによる検証を行います。
     * @param target
     * @param validrules
     * @return
     */
    public List<String> validateRecursive(final ValueNode target, final ValidateNode validrules) {
        ValidationContext context  = new ValidationContext();
        List<String> errors = new ArrayList<String>();

        if (isMatchNode(target, validrules.getMatchers())) {
            validateRecurciveInner(target, validrules, context, errors);
        }

        return errors;
    }

    private void validateRecurciveInner(final ValueNode target, final ValidateNode validrules, ValidationContext context, List<String> errors) {
        context.getValidateStack().push(validrules);

        for (Rule rule : validrules.getRules()) {
            rule.validateNode(target, validrules, context);
        }

        for (ValueNode child : target.getChidren()) {
            validchildLoop:
            for (ValidateNode validChild : validrules.getChildren()) {
                for (Matcher matcher : validChild.getMatchers()) {
                    if (!matcher.match(child)) {
                        continue validchildLoop;
                    }
                }
                validateRecurciveInner(child, validChild, context, errors);
            }
        }

        for (ValidateNode validChild : validrules.getChildren()) {
            for (Rule rules : validChild.getRules()) {
                rules.onLeaveScope(validChild, context);
            }
        }

        context.getValidateStack().pop();
    }

    private boolean isMatchNode(ValueNode node, List<Matcher> matchers){
        for (Matcher matcher : matchers) {
            if (!matcher.match(node)) {
                return false;
            }
        }
        return true;
    }
}
