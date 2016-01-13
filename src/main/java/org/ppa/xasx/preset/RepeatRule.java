package org.ppa.xasx.preset;

import static org.ppa.xasx.util.XasXStringUtil.*;

import java.text.MessageFormat;

import org.ppa.xasx.core.ErrorMessage;
import org.ppa.xasx.core.NodeDefine;
import org.ppa.xasx.core.Rule;
import org.ppa.xasx.core.ValueNode;
import org.ppa.xasx.core.validate.ValidateContext;

public class RepeatRule implements Rule {
    private int cnt = 0;

    private String min = null;
    private String max = null;
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
            String name = joinSand(context.getValidateStack(), "<" , ">");

            String mintxt = (isNumeric(min)) ? "" : min;
            String maxtxt = (isNumeric(max)) ? "" : max;
            String range = mintxt + "-" + maxtxt;

            return Rule.error(name, MessageFormat.format(msgTemplate, name, range, cnt));
        }
        return null;
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


}
