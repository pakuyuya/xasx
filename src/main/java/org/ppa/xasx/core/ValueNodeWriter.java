package org.ppa.xasx.core;

/**
 * 対象のオブジェクトに値を書き込むアダプタークラスのインタフェース
 */
public interface ValueNodeWriter {

    /**
     * Node名を設定します。
     * @param node 対象のオブジェクト
     * @param value 値
     * @param context コンテキスト
     */
    public void setName(Object node, String value, ValueIOContext context);

    /**
     * Node値を設定します。
     * @param node 対象のオブジェクト
     * @param value 値
     * @param context コンテキスト
     */
    public void setValue(Object node, String value, ValueIOContext context);

    /**
     * 属性を設定します。
     * @param node 対象のオブジェクト
     * @param name 属性名
     * @param value 値
     * @param context コンテキスト
     */
    public void setAttribute(Object node, String name, String value, ValueIOContext context);
}
