package org.ppa.xasx.preset.translate;

import static org.ppa.xasx.util.XasXLangUtil.*;
import static org.ppa.xasx.util.XasXStringUtil.*;

import java.text.MessageFormat;

import org.ppa.xasx.core.ValueNode;
import org.ppa.xasx.core.XasXException;
import org.ppa.xasx.core.validate.ValidateContext;
import org.ppa.xasx.types.ParameterCheckable;
import org.ppa.xasx.types.Translate;

public class TrimTranslate implements Translate, ParameterCheckable {

    private final String TYPE_ASCII = "ascii";
    private final String TYPE_UNICODE = "unicode";

    private String type = TYPE_ASCII;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public ValueNode translate(ValueNode node, ValidateContext context) {
        String value = node.getValue();

        switch (value) {
        case TYPE_ASCII:
            value = trim(value);
            break;
        case TYPE_UNICODE:
            value = strip(value);
            break;
        }

        node.setValue(value);

        return node;
    }

    @Override
    public void checkParameter(ValidateContext context) throws XasXException {
        if (isNotIn(type, TYPE_ASCII, TYPE_UNICODE)) {
            final String msg = MessageFormat.format("`{0}` is invalid value for {1}. at {2}",
                    type, "type", context.getValidateStackText());
            throw new XasXException(msg);
        }
    }

}
