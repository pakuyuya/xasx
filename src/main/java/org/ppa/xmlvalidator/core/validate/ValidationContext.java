package org.ppa.xmlvalidator.core.validate;

import java.util.Stack;

/**
 * 検証処理のContext
 */
public class ValidationContext {
    private Stack<ValueNode> nodeStack;

    public Stack<ValueNode> getNodeStack() {
        return nodeStack;
    }
    public void setNodeStack(Stack<ValueNode> nodeStack) {
        this.nodeStack = nodeStack;
    }
}
