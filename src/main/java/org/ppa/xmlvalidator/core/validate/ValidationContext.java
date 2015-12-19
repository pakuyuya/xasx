package org.ppa.xmlvalidator.core.validate;

import java.util.Stack;

/**
 * 検証処理のContext
 */
public class ValidationContext {
    private Stack<ValidateNode> validateStack;

    public Stack<ValidateNode> getValidateStack() {
        return validateStack;
    }
    public void setValidateStack(Stack<ValidateNode> validateStack) {
        this.validateStack = validateStack;
    }
}
