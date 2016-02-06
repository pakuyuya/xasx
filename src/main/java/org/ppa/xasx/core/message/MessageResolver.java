package org.ppa.xasx.core.message;

/**
 * 引数からメッセージを生成するクラスのインタフェース
 * @param <T> パラメータ型
 */
public interface MessageResolver {
    public String resolve(MessageResolverParam param);
}
