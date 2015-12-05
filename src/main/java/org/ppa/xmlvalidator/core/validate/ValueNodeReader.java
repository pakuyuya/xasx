package org.ppa.xmlvalidator.core.validate;

/**
 * 検証対象のオブジェクトから、ValueNodeを作る。
 *
 * @param <T> 検証対象の型
 */
public interface ValueNodeReader<T> {

    /**
     * 引数で指定したオブジェクトからValueNodeに変換する
     *
     * @param name Node名
     * @param src  変換対象
     * @return
     */
    ValueNode convertNode(String name, T src);

    /**
     * 子ノードを取得する
     *
     * @param childName
     * @param parent
     * @return
     */
    T readChildNode(T parent, String childName);
}
