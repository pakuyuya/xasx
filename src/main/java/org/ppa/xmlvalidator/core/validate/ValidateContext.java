package org.ppa.xmlvalidator.core.validate;

import java.util.Stack;

import org.ppa.xmlvalidator.core.NodeDefine;

/**
 * 検証処理のContext
 */
public class ValidateContext {
    private Stack<NodeDefine> validateStack;

    public Stack<NodeDefine> getValidateStack() {
        return validateStack;
    }
    public void setValidateStack(Stack<NodeDefine> validateStack) {
        this.validateStack = validateStack;
    }
}
