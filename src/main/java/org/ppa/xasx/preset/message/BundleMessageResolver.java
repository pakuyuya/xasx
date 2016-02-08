package org.ppa.xasx.preset.message;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.ppa.xasx.core.message.MessageResolver;
import org.ppa.xasx.core.message.MessageResolverParam;

/**
 * MessageFormat.formatによりメッセージを生成するMessageResolver
 * @author YuyaPaku
 *
 */
public class BundleMessageResolver implements MessageResolver {
    private ResourceBundle bundle = null;

    public void setResourceBundle(String bundleName) {
        bundle = ResourceBundle.getBundle("bundleName");
    }

    @Override
    public String resolve(MessageResolverParam param) {
        String template = bundle.getString(param.getTemplate());
        return MessageFormat.format(template, param.getParams().toArray());
    }
}
