package org.ppa.xasx.preset.message;

import java.text.MessageFormat;

import org.ppa.xasx.core.message.MessageResolver;
import org.ppa.xasx.preset.message.FormatMessageResolverParam;

/**
 * MessageFormat.formatによりメッセージを生成するMessageResolver
 * @author YuyaPaku
 *
 */
public class BundleMessageResolver extends MessageResolver<FormatMessageResolverParam> {
    @Override
    public String typedResolve(FormatMessageResolverParam param) {
        return MessageFormat.format(param.getTemplate(), param.getParams());
    }

}
