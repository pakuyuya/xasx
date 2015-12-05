package org.ppa.xmlvalidator.core.validate;

import java.util.ArrayList;
import java.util.List;

/**
 * 検証のメインメソッド
 */
abstract public class ValidateEngine<T> {
    private ValueNodeReader<T> valueNodeReader;

    public List<String> validateRecursive(final ValueNode target, final ValidateNode validrules, ValidationContext context) {
        List<String> errors = new ArrayList<String>();

        // TODO:

        return errors;
    }

    private List<String> validate(final ValueNode target, final ValidateNode validrules, ValidationContext context) {
        List<String> errors = new ArrayList<String>();

        // TODO:

        return errors;
    }

    public ValueNodeReader<T> getValueNodeReader() {
        return valueNodeReader;
    }

    public void setValueNodeReader(ValueNodeReader<T> valueNodeReader) {
        this.valueNodeReader = valueNodeReader;
    }
}
