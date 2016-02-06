package org.ppa.xasx.core.validate;

import java.util.Stack;

import org.ppa.xasx.core.NodeDefine;
import org.ppa.xasx.core.message.MessageResolver;

/**
 * 検証処理のContext
 */
public class ValidateContext {
    private Stack<NodeDefine> validateStack;
    private MessageResolver messageResolver;

    public Stack<NodeDefine> getValidateStack() {
        return validateStack;
    }
    public void setValidateStack(Stack<NodeDefine> validateStack) {
        this.validateStack = validateStack;
    }
    public MessageResolver getMessageResolver() {
        return messageResolver;
    }
    public void setMessageResolver(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }
}
