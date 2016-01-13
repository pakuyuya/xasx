package org.ppa.xmlvalidator.core;

import java.util.List;

/**
 * 特定のオブジェクトから値を読み込むアダプタークラス。
 */
public interface ValueNodeReader {

    /**
     * Node名を取得します。
     * @param node 対象オブジェクト
     * @param context コンテキスト
     * @return name
     */
    public String getName(Object node, ValueIOContext context);

    /**
     * Node値を取得します。
     * @param node 対象オブジェクト
     * @param context コンテキスト
     * @return value
     */
    public String getValue(Object node, ValueIOContext context);

    /**
     * 子要素の数を取得します。
     * @param node 対象オブジェクト
     * @param context コンテキスト
     * @return count of children.
     */
    public int getChildrenCount(Object node, ValueIOContext context);

    /**
     * 子要素を取得します。
     * @param node 対象オブジェクト
     * @param index 子要素のインデックス
     * @param context コンテキスト
     * @return child object
     */
    public Object getChild(Object node, int index, ValueIOContext context);

    /**
     * 属性名のリストを取得します。
     * @param node 対象オブジェクト
     * @param context コンテキスト
     * @return list of attribute namse
     */
    public List<String> getAttributeNames(Object node, ValueIOContext context);

    /**
     * 属性を取得します。
     * @param node 対象オブジェクト
     * @param name 属性名
     * @param context コンテキスト
     * @return value of attribute
     */
    public String getAttribute(Object node, String name, ValueIOContext context);
}
