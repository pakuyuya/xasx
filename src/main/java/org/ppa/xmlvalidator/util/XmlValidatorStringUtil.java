package org.ppa.xmlvalidator.util;

/**
 * common-lang StringUtils相当のオレオレUtil
 * 下手に依存関係作りたくなかったので手実装。
 */
public class XmlValidatorStringUtil {

    final static public String EMPTY = "";

    /**
     * StringUtils.isEmpty()相当の機能
     * @param cs
     * @return
     */
    final static public boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * StringUtils.isBlank()相当の機能。というかパクリ
     *
     * @param cs
     * @return {@code true} 文字列が null || "" || 全角含む空白文字のみで構成されている場合true
     */
    final static public boolean isBlankUnicode(final CharSequence cs) {
        if (cs == null) {
            return false;
        }

        int len = cs.length();
        if (len == 0) {
            return false;
        }

        for (int i = 0; i < len; ++i) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * StringUtils.isNotBlank()相当の機能。というかパクリ
     *
     * @param cs
     * @return {@code false} 文字列が null || "" || 全角含む空白文字のみで構成されている場合true
     */
    final static public boolean isNotBlankUnicode(final CharSequence cs) {
        return !isBlankUnicode(cs);
    }

    /**
     * StringUtils.isBlank() の全角スペース除く版
     *
     * @param cs
     * @return {@code true} 文字列が null || "" || 半角空白文字のみで構成されている場合true
     */
    final static public boolean isBlankAscii(final CharSequence cs) {
        if (cs == null) {
            return false;
        }

        int len = cs.length();
        if (len == 0) {
            return false;
        }

        for (int i = 0; i < len; ++i) {
            char c = cs.charAt(i);
            if (!Character.isWhitespace(c) && c != '　') {
                return false;
            }
        }
        return true;
    }

    /**
     * StringUtils.isNotBlank() の全角スペース除く版
     *
     * @param cs
     * @return {@code false} 文字列が null || "" || 半角空白文字のみで構成されている場合true
     */
    final static public boolean isNotBlankAscii(final CharSequence cs) {
        return !isBlankAscii(cs);
    }

    /**
     * StringUtils.strip()相当の実装
     *
     * @param str
     * @return
     */
    final static public String strip(final String str) {
        if (str == null) {
            return null;
        }

        int len = str.length();
        int start = -1;
        for(int i = 0; i < len; ++i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                start = i;
                break;
            }
        }

        if (start == -1)
            return EMPTY;

        int end = -1;
        for(int i = len - 1; i >= start; --i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                end = i;
                break;
            }
        }

        return str.substring(start, end - start + 1);
    }

    /**
     * StringUtils.stripToEmpty()相当の実装
     *
     * @param str
     * @return
     */
    final static public String stripToEmpty(final String str) {
        return (str == null) ? EMPTY : strip(str);
    }

    /**
     * StringUtils.trim()相当の実装
     *
     * @param str
     * @return
     */
    final static public String trim(final String str) {
        return (str == null) ? null : str.trim();
    }

    /**
     * StringUtils.equals()相当の実装
     *
     * @param lhs
     * @param rhs
     * @return
     */
    final static public boolean equal(final String lhs, final String rhs) {
        return lhs != null && rhs != null && lhs.equals(rhs);
    }
}
