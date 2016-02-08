package org.ppa.xasx.core.message;

/**
 * 引数からメッセージを生成するクラスのインタフェース
 * @param <T> パラメータ型
 */
public class MessageResolverHelper {
    static public String resolveMessage(MessageResolver resolver, String template, Object ... params){
        MessageResolverParam param = new MessageResolverParam();
        param.setTemplate(template);
        param.addParam(params);

        return resolver.resolve(param);
    }
}
