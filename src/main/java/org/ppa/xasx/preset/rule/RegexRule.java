package org.ppa.xasx.preset.rule;

import org.ppa.xasx.core.ErrorMessage;
import org.ppa.xasx.core.NodeDefine;
import org.ppa.xasx.core.ValueNode;
import org.ppa.xasx.core.message.MessageResolver;
import org.ppa.xasx.core.message.MessageResolverHelper;
import org.ppa.xasx.core.validate.ValidateContext;
import org.ppa.xasx.types.Rule;

import static org.ppa.xasx.util.XasXStringUtil.*;

/**
 * 正規表現ルール
 */
public class RegexRule implements Rule {

    final public String ON_EMPTY_YES = "yes";
    final public String ON_EMPTY_NO = "no";

    /**
     * 正規表現のパターンです。
     */
    private String pattern = "";

    /**
     * メッセージのテンプレートです。
     */
    private String msgTemplate = "{0}の形式が不正です。";

    /**
     * {@code "yes"} を設定すると、値が空文字・nullの場合でも検証を行います。
     */
    private String onEmpty = ON_EMPTY_NO;


    public String getPattern() {
        return pattern;
    }
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getMsgTemplate() {
        return msgTemplate;
    }
    public void setMsgTemplate(String msgTemplate) {
        this.msgTemplate = msgTemplate;
    }

    public String getOnEmpty() {
        return onEmpty;
    }
    public void setOnEmpty(String onEmpty) {
        this.onEmpty = onEmpty;
    }


    @Override
    public ErrorMessage validateNode(ValueNode node, NodeDefine validNode, ValidateContext context) {
        String name = context.getNodeStringifyer().convert(context.getValidateStack(), context);
        final String value = node.getValue();
        final MessageResolver resolver = context.getMessageResolver();

        if (!onEmpty.equals(ON_EMPTY_YES) && isEmpty(value)) {
            return Rule.ok();
        }

        if (value.matches(pattern)) {
            String message = MessageResolverHelper.resolveMessage(resolver, msgTemplate, name);
            Rule.error(name, message);
        }

        return Rule.ok();
    }

    @Override
    public ErrorMessage onLeaveScope(NodeDefine validNode, ValidateContext context) {
        return Rule.ok();
    }
}
