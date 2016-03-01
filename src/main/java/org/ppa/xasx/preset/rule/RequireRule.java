package org.ppa.xasx.preset.rule;

import static org.ppa.xasx.util.XasXStringUtil.*;

import org.ppa.xasx.core.ErrorMessage;
import org.ppa.xasx.core.NodeDefine;
import org.ppa.xasx.core.ValueNode;
import org.ppa.xasx.core.message.MessageResolver;
import org.ppa.xasx.core.message.MessageResolverHelper;
import org.ppa.xasx.core.validate.ValidateContext;
import org.ppa.xasx.types.Rule;

public class RequireRule implements Rule {

    private String msgTemplate = "{0}は必須です。";

    private boolean exists = false;


    /**
     * @return msgTemplate
     */
    public String getMsgTemplate() {
        return msgTemplate;
    }

    /**
     * @param msgTemplate セットする msgTemplate
     */
    public void setMsgTemplate(String msgTemplate) {
        this.msgTemplate = msgTemplate;
    }


    @Override
    public ErrorMessage validateNode(ValueNode node, NodeDefine validNode, ValidateContext context) {
        if (isNotEmpty(node.getValue())) {
            exists = true;
        }
        return Rule.ok();
    }

    @Override
    public ErrorMessage onLeaveScope(NodeDefine validNode, ValidateContext context) {
        if (!exists) {
            return createErrorMessage(context);
        }
        return Rule.ok();
    }

    private ErrorMessage createErrorMessage(ValidateContext context) {
        String name = context.getNodeStringifyer().convert(context.getValidateStack(), context);
        final MessageResolver resolver = context.getMessageResolver();
        final String message = MessageResolverHelper.resolveMessage(resolver, msgTemplate, name);

        return Rule.error(name, message);
    }
}
