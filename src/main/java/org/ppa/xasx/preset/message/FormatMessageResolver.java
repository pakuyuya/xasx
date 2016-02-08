package org.ppa.xasx.preset.message;

import java.text.MessageFormat;

import org.ppa.xasx.core.message.MessageResolver;
import org.ppa.xasx.core.message.MessageResolverParam;

/**
 * MessageFormat.formatによりメッセージを生成するMessageResolver
 * @author YuyaPaku
 *
 */
public class FormatMessageResolver implements MessageResolver {
    @Override
    public String resolve(MessageResolverParam param) {
        return MessageFormat.format(param.getTemplate(), param.getParams().toArray());
    }
}
