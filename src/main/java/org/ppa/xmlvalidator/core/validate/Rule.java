package org.ppa.xmlvalidator.core.validate;

/**
 * 検証ルール
 */
public interface Rule {
    /**
     * 条件に一致するタグが見つかった時に呼ばれる
     * @param value
     * @maram context
     * @return エラーメッセージ。何も返さない場合は空文字またはnull
     */
    public String validateNode(final ValueNode node, final ValidateNode validNode, final ValidationContext context);

    /**
     * スコープから抜けるときに１回だけ呼ばれる
     * @param validNode
     * @param context
     * @return
     */
    public String onLeaveScope(final ValidateNode validNode, final ValidationContext context);
}
