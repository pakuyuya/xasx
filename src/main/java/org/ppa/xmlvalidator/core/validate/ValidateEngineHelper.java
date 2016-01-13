package org.ppa.xmlvalidator.core.validate;

import java.util.Map;

import org.ppa.xmlvalidator.core.ValueMaker;
import org.ppa.xmlvalidator.core.ValueIOContext;
import org.ppa.xmlvalidator.core.XasXHelper;
import org.ppa.xmlvalidator.core.rulefileparser.ConfigsPropKey;

import static org.ppa.xmlvalidator.util.XmlValidatorLangUtil.*;

/**
 * {@code ValudateEngine}ヘルパ
 */
public class ValidateEngineHelper {
    /**
     * {@code ValueReadContext}を初期化します。
     * @param context 初期化対象のValueReadContext
     * @param options プロパティ値
     */
    public static void setupValueReadContext(ValueIOContext context, Map<String, String> options) {
        try {
            if (options.containsKey(ConfigsPropKey.DEFAULT_VALUE_MAKER)) {
                ValueMaker value = cast(Class.forName(options.get(options.get(ConfigsPropKey.DEFAULT_VALUE_MAKER))));
                context.setDefaultValueMaker(value);
            }
        } catch (ClassNotFoundException e) {
            XasXHelper.wrapException(e);
        }
    }

    /**
     * {@code ValidateContext}を初期化します。
     * @param context 初期化対象のValidateContext
     * @param options プロパティ値
     */
    public static void setupValidationContext(ValidateContext context, Map<String, String> options) {
        // do nothing.
    }
}
