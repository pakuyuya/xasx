package org.ppa.xasx.preset.translate;

import org.ppa.xasx.core.ValueNode;
import org.ppa.xasx.core.validate.ValidateContext;
import org.ppa.xasx.types.Translate;

import static org.ppa.xasx.util.XasXStringUtil.*;

public class TrimTranslate implements Translate {

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

}
