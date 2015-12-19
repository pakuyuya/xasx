package org.ppa.xmlvalidator.preset;

import static org.ppa.xmlvalidator.util.XmlValidatorStringUtil.*;

import java.text.MessageFormat;

import org.ppa.xmlvalidator.core.validate.ErrorMessage;
import org.ppa.xmlvalidator.core.validate.Rule;
import org.ppa.xmlvalidator.core.validate.ValidateNode;
import org.ppa.xmlvalidator.core.validate.ValidationContext;
import org.ppa.xmlvalidator.core.validate.ValueNode;

public class RepeatRule implements Rule {
    private int cnt = 0;

    private String min = null;
    private String max = null;
    private String msgTemplate = "{0}は{1}個の範囲で入力してください。（{2}個）";

    @Override
    public ErrorMessage validateNode(ValueNode node, ValidateNode validNode, ValidationContext context) {
        ++cnt;
        return Rule.ok();
    }

    @Override
    public ErrorMessage onLeaveScope(ValidateNode validNode, ValidationContext context) {

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
