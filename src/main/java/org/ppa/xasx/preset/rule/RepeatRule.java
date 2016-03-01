package org.ppa.xasx.preset.rule;

import static org.ppa.xasx.util.XasXStringUtil.*;

import java.text.MessageFormat;

import org.ppa.xasx.core.ErrorMessage;
import org.ppa.xasx.core.NodeDefine;
import org.ppa.xasx.core.ValueNode;
import org.ppa.xasx.core.XasXException;
import org.ppa.xasx.core.message.MessageResolver;
import org.ppa.xasx.core.message.MessageResolverParam;
import org.ppa.xasx.core.validate.ValidateContext;
import org.ppa.xasx.types.ParameterCheckable;
import org.ppa.xasx.types.Rule;

/**
 * タグの出現回数のルールです。<br>
 * <h3>使用例</h3>
 * <pre>
 * &lt;rules&gt;
 *   &lt;rule name="repeat" class="org.ppa.xsax.preset.rule.RepeatRule" /&gt;
 * &lt;/rules&gt;
 * &lt;validation&gt;
 *   &lt;authors&gt;
 *     &lt;author&gt;
 *       &lt;rule:repeat max="5" /&gt;
 *     &lt;/author&gt;
 *   &lt;/authors&gt;
 * &lt;/validation&gt;
 * </pre>
 */
public class RepeatRule implements Rule, ParameterCheckable {
    /**
     * 内部用パラメータ。タグの出現カウントです。
     */
    private int cnt = 0;

    /**
     * 繰り返し回数範囲の下限です。指定無しの場合、下限はありません。
     */
    private String min = null;

    /**
     * 繰り返し回数範囲の上限です。指定無しの場合、上限はありません。
     */
    private String max = null;

    /**
     * メッセージテンプレートです。
     */
    private String msgTemplate = "{0}は{1}個の範囲で入力してください。（{2}個）";

    @Override
    public ErrorMessage validateNode(ValueNode node, NodeDefine validNode, ValidateContext context) {
        ++cnt;
        return Rule.ok();
    }

    @Override
    public ErrorMessage onLeaveScope(NodeDefine validNode, ValidateContext context) {
        if (    (isNumeric(min) && (cnt < Integer.valueOf(min)))
             || (isNumeric(max) && (cnt > Integer.valueOf(max)))) {
            String name = context.getNodeStringifyer().convert(context.getValidateStack(), context);

            String mintxt = (isNumeric(min)) ? "" : min;
            String maxtxt = (isNumeric(max)) ? "" : max;
            String range = mintxt + "-" + maxtxt;

            MessageResolver resolver = context.getMessageResolver();
            MessageResolverParam resolveParam = new MessageResolverParam();
            resolveParam.setTemplate(msgTemplate);
            resolveParam.addParam(name, range, cnt);

            return Rule.error(name, resolver.resolve(resolveParam));
        }
        return Rule.ok();
    }

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

    @Override
    public void checkParameter(ValidateContext context) throws XasXException {
        if (isEmpty(min) && isEmpty(max)) {
            throw new XasXException("require either min or max. at" + context.getValidateStackText());
        }

        if (isNotEmpty(min) && (!isNumeric(min) || Integer.valueOf(min) < 0)) {
            final String msg = MessageFormat.format("`{0}` is invalid value for {1}. at `{2}`",
                    min, "min", context.getValidateStackText());
            throw new XasXException(msg);
        }

        if (isNotEmpty(max) && (!isNumeric(max) || Integer.valueOf(max) < 0)) {
            final String msg = MessageFormat.format("`{0}` is invalid value for {1}. at `{2}`",
                    max, "max", context.getValidateStackText());
            throw new XasXException(msg);
        }

        if (isNumeric(min) && isNumeric(max) && Integer.valueOf(min) > Integer.valueOf(max)) {
            final String msg = MessageFormat.format("`{0}`(min) is larger than `{1}`(max). at `{2}`",
                    min, max, context.getValidateStackText());
            throw new XasXException(msg);
        }
    }
}
