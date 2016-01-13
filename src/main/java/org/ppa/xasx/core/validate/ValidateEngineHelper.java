package org.ppa.xasx.core.validate;

import static org.ppa.xasx.util.XasXLangUtil.*;

import java.util.Map;

import org.ppa.xasx.core.ValueIOContext;
import org.ppa.xasx.core.ValueMaker;
import org.ppa.xasx.core.XasXHelper;
import org.ppa.xasx.core.rulefileparser.ConfigsPropKey;

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
