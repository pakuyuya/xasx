package org.ppa.xasx.preset.rule;

import static org.ppa.xasx.util.XasXStringUtil.*;

import java.text.MessageFormat;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.ppa.xasx.core.ErrorMessage;
import org.ppa.xasx.core.NodeDefine;
import org.ppa.xasx.core.ValueNode;
import org.ppa.xasx.core.XasXException;
import org.ppa.xasx.core.message.MessageResolver;
import org.ppa.xasx.core.message.MessageResolverHelper;
import org.ppa.xasx.core.validate.ValidateContext;
import org.ppa.xasx.types.Rule;
import org.ppa.xasx.types.ParameterCheckable;
import org.ppa.xasx.util.XasXUtil;

/**
 * 正規表現ルール
 */
public class RegexRule implements Rule, ParameterCheckable {
    /**
     * 正規表現のパターンです。
     */
    private String pattern = "";

    /**
     * メッセージのテンプレートです。
     */
    private String msgTemplate = "{0}の形式が不正です。";

    /**
     * {@code "no"} を設定すると、値が空文字・nullの場合でも検証を行います。
     */
    private String ignoreEmpty = "yes";


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

    public String getIgnoreEmpty() {
        return ignoreEmpty;
    }
    public void setIgnoreEmpty(String ignoreEmpty) {
        this.ignoreEmpty = ignoreEmpty;
    }
    @Override
    public ErrorMessage validateNode(ValueNode node, NodeDefine validNode, ValidateContext context) {
        String name = context.getNodeStringifyer().convert(context.getValidateStack(), context);
        final String value = node.getValue();
        final MessageResolver resolver = context.getMessageResolver();

        if (isEmpty(value) && XasXUtil.isTrueText(ignoreEmpty)) {
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
    @Override
    public void checkParameter(ValidateContext context) throws XasXException {
        if (isEmpty(pattern)) {
            final String msg = MessageFormat.format("required `{0}` attribute. at {1}",
                    "pattern", context.getValidateStackText());
            throw new XasXException(msg);
        }

        try {
            Pattern.compile(pattern);
        } catch (PatternSyntaxException ex){
            final String msg = MessageFormat.format("`{0}` is invalid regex string for {1}. at {2}",
                    pattern, "pattern", context.getValidateStackText());
            throw new XasXException(msg);
        }
    }
}
