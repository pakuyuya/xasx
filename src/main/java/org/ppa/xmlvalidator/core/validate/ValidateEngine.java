package org.ppa.xmlvalidator.core.validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ppa.xmlvalidator.core.validate.matcher.Matcher;
import org.ppa.xmlvalidator.util.XmlValidatorCommonUtil;

/**
 * 検証のメインロジック
 */
public class ValidateEngine {

    /**
     * 与えられたValueNodeに対し、ValidateNodeによる検証を行います。
     * @param valueNode
     * @param validateNode
     * @return
     */
    public Map<String, List<String>> validateRecursive(final ValueNode valueNode, final ValidateNode validateNode) {
        ValidationContext context  = new ValidationContext();
        Map<String, List<String>> errors = new HashMap<String, List<String>>();

        if (isMatchNode(valueNode, validateNode.getMatchers())) {
            validateRecurciveInner(valueNode, validateNode, context, errors);
        }

        return errors;
    }

    private void validateRecurciveInner(final ValueNode target, final ValidateNode validrules, ValidationContext context, Map<String, List<String>> errors) {
        context.getValidateStack().push(validrules);

        for (Rule rule : validrules.getRules()) {
            ErrorMessage error = rule.validateNode(target, validrules, context);
            if (error != null)
                XmlValidatorCommonUtil.putMapList(errors, error.getName(), error.getMessage());
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
                ErrorMessage error = rules.onLeaveScope(validChild, context);
                if (error != null)
                    XmlValidatorCommonUtil.putMapList(errors, error.getName(), error.getMessage());
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
