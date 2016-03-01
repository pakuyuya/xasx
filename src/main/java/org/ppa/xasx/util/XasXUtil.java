package org.ppa.xasx.util;

public class XasXUtil {
    public static boolean isTrueText(final String text) {
        return text != null && (text.equals("true") || text.equals("yes"));
    }
}
