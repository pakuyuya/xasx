package org.ppa.xasx.core;

import org.ppa.xasx.core.validate.ValidateContext;


/**
 * 検証ルール
 */
public interface Rule {

    public static ErrorMessage ok() {
        return null;
    }

    public static ErrorMessage error(String name, String message){
        ErrorMessage ruleError = new ErrorMessage();
        ruleError.setName(name);
        ruleError.setMessage(message);

        return ruleError;
    }

    /**
     * 条件に一致するタグが見つかった時に呼ばれる
     * @param value
     * @maram context
     * @return エラーメッセージ。何も返さない場合は空文字またはnull
     */
    public ErrorMessage validateNode(final ValueNode node, final NodeDefine validNode, final ValidateContext context);

    /**
     * スコープから抜けるときに１回だけ呼ばれる
     * @param validNode
     * @param context
     * @return
     */
    public ErrorMessage onLeaveScope(final NodeDefine validNode, final ValidateContext context);
}
