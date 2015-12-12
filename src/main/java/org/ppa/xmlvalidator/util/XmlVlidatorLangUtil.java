package org.ppa.xmlvalidator.util;

public class XmlVlidatorLangUtil {

    /**
     * 型推論的なキャストを行う。<br>
     * >= Java 8
     * @param o
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object o){
        return (T) o;
    }
}
