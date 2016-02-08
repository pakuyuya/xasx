package org.ppa.xasx.preset.rule;

import static org.ppa.xasx.util.XasXStringUtil.*;

import org.ppa.xasx.core.ErrorMessage;
import org.ppa.xasx.core.NodeDefine;
import org.ppa.xasx.core.ValueNode;
import org.ppa.xasx.core.message.MessageResolver;
import org.ppa.xasx.core.message.MessageResolverHelper;
import org.ppa.xasx.core.validate.ValidateContext;
import org.ppa.xasx.types.Rule;

public class LengthRule implements Rule {

    final public String ON_EMPTY_YES = "yes";
    final public String ON_EMPTY_NO = "no";

    /**
     * 最小文字数です。指定無しの場合、下限値はありません。
     */
    private String min = null;
    /**
     * 最大文字数です。指定ありの場合、上限値はありません。
     */
    private String max = null;
    /**
     * メッセージのテンプレートです。
     */
    private String msgTemplate = "{0}は{1}文字の範囲で入力してください。（{2}字）";

    /**
     * {@code "yes"} を設定すると、値が空文字・nullの場合でも検証を行います。
     */
    private String onEmpty = ON_EMPTY_NO;


    public String getMin() {
        return min;
    }
    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }
    public void setMax(String max) {
        this.max = max;
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
        String value = node.getValue();

        if (isEmpty(value)) {
            return Rule.ok();
        }

        int len = isNotEmpty(value) ? value.length() : 0;
        if (    (isNumeric(min) && (len < Integer.valueOf(min)))
             || (isNumeric(max) && (len > Integer.valueOf(max)))) {
            String name = context.getNodeStringifyer().convert(context.getValidateStack(), context);

            String mintxt = (isNumeric(min)) ? "" : min;
            String maxtxt = (isNumeric(max)) ? "" : max;
            String range = mintxt + "-" + maxtxt;

            MessageResolver resolver = context.getMessageResolver();
            String message = MessageResolverHelper.resolveMessage(resolver, msgTemplate, name, range, len);

            return Rule.error(name, message);
        }

        return Rule.ok();
    }

    @Override
    public ErrorMessage onLeaveScope(NodeDefine validNode, ValidateContext context) {
        return Rule.ok();
    }
}
