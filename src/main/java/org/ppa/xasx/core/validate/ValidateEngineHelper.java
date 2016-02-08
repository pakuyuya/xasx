package org.ppa.xasx.core.validate;

import static org.ppa.xasx.util.XasXLangUtil.*;

import java.util.Map;

import org.ppa.xasx.core.ValueIOContext;
import org.ppa.xasx.core.XasXHelper;
import org.ppa.xasx.core.message.MessageResolver;
import org.ppa.xasx.core.rulefileparser.ConfigsPropKey;
import org.ppa.xasx.types.NodeReadWriter;
import org.ppa.xasx.types.NodeStringifyer;
import org.ppa.xasx.types.ValueMaker;

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
            if (options.containsKey(ConfigsPropKey.DEFAULT_VALUE_NODE_READWRITER)) {
                String className = options.get(ConfigsPropKey.DEFAULT_VALUE_NODE_READWRITER);
                NodeReadWriter value = cast(Class.forName(className).newInstance());
                context.setDefaultNodeReadWriter(value);
            }
            if (options.containsKey(ConfigsPropKey.DEFAULT_VALUE_MAKER)) {
                ValueMaker value = cast(Class.forName(options.get(ConfigsPropKey.DEFAULT_VALUE_MAKER)).newInstance());
                context.setDefaultValueMaker(value);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw XasXHelper.wrapException(e);
        }
    }

    /**
     * {@code ValidateContext}を初期化します。
     * @param context 初期化対象のValidateContext
     * @param options プロパティ値
     */
    public static void setupValidationContext(ValidateContext context, Map<String, String> options) {
        try {
            if (options.containsKey(ConfigsPropKey.MESSAGE_RESOLVER)) {
                String className = options.get(ConfigsPropKey.MESSAGE_RESOLVER);
                MessageResolver value = cast(Class.forName(className).newInstance());
                context.setMessageResolver(value);
            }
            if (options.containsKey(ConfigsPropKey.NODE_STRINGIFYER)) {
                String className = options.get(ConfigsPropKey.NODE_STRINGIFYER);
                NodeStringifyer value = cast(Class.forName(className).newInstance());
                context.setNodeStringifyer(value);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw XasXHelper.wrapException(e);
        }
    }
}
