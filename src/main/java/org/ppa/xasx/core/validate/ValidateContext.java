package org.ppa.xasx.core.validate;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.ppa.xasx.core.NodeDefine;
import org.ppa.xasx.core.message.MessageResolver;
import org.ppa.xasx.types.NodeStringifyer;

/**
 * 検証処理のContext
 */
public class ValidateContext {
    private Stack<NodeDefine> validateStack = new Stack<NodeDefine>();
    private Map<String, String> userDatas = new HashMap<String, String>();
    private MessageResolver messageResolver;
    private String wildcardDisplay;
    private NodeStringifyer nodeStringifyer;

    public String getValidateStackText() {
        return nodeStringifyer.convert(validateStack, this);
    }

    public Stack<NodeDefine> getValidateStack() {
        return validateStack;
    }
    public void setValidateStack(Stack<NodeDefine> validateStack) {
        this.validateStack = validateStack;
    }
    public String getUserData(String key) {
        return userDatas.get(key);
    }
    public void setUserData(String key, String value) {
        userDatas.put(key, value);
    }
    public boolean hasUserData(String key) {
        return userDatas.containsKey(key);
    }
    public Map<String, String> getUseDatas() {
        return userDatas;
    }
    public MessageResolver getMessageResolver() {
        return messageResolver;
    }
    public void setMessageResolver(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }
    public String getWildcardDisplay() {
        return wildcardDisplay;
    }
    public void setWildcardDisplay(String wildcardDisplay) {
        this.wildcardDisplay = wildcardDisplay;
    }
    public NodeStringifyer getNodeStringifyer() {
        return nodeStringifyer;
    }
    public void setNodeStringifyer(NodeStringifyer nodeStringifyer) {
        this.nodeStringifyer = nodeStringifyer;
    }

}
