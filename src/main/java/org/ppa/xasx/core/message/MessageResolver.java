package org.ppa.xasx.core.message;

/**
 * 引数からメッセージを生成するクラスのインタフェース
 * @param <T> パラメータ型
 */
abstract public class MessageResolver<T> {
    @SuppressWarnings("unchecked")
    public String resolve(Object param){
        return typedResolve((T)param);
    };

    abstract protected String typedResolve(T param);
}
