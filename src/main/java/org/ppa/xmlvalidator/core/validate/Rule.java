package org.ppa.xmlvalidator.core.validate;

/**
 * 検証ルール
 */
public interface Rule {
    /**
     * 検証
     * @param value
     * @maram context
     * @return エラーメッセージ
     */
    public String validate(String value, final ValidationContext context);
}
