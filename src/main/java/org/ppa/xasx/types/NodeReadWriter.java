package org.ppa.xasx.types;

import java.util.List;

/**
 * 特定のオブジェクトから値を読み込むアダプタークラス。
 */
public interface NodeReadWriter {

    public boolean isValue(Object node);

    /**
     * Node名を取得します。
     * @param node 対象オブジェクト
     * @return name
     */
    public String getName(Object node);

    /**
     * Node値を取得します。
     * @param node 対象オブジェクト
     * @return value
     */
    public String getValue(Object node);

    /**
     * 子要素の数を取得します。
     * @param node 対象オブジェクト
     * @return count of children.
     */
    public int getChildrenCount(Object node);

    /**
     * 子要素を取得します。
     * @param node 対象オブジェクト
     * @param index 子要素のインデックス
     * @return child object
     */
    public Object getChild(Object node, int index);

    /**
     * 属性名のリストを取得します。
     * @param node 対象オブジェクト
     * @return list of attribute namse
     */
    public List<String> getAttributeNames(Object node);

    /**
     * 属性を取得します。
     * @param node 対象オブジェクト
     * @param name 属性名
     * @return value of attribute
     */
    public String getAttribute(Object node, String name);

    /**
     * Node名を設定します。
     * @param node 対象のオブジェクト
     * @param value 値
     */
    public void setName(Object node, String value);

    /**
     * Node値を設定します。
     * @param node 対象のオブジェクト
     * @param value 値
     */
    public void setValue(Object node, String value);

    /**
     * 属性を設定します。
     * @param node 対象のオブジェクト
     * @param name 属性名
     * @param value 値
     */
    public void setAttribute(Object node, String name, String value);
}
