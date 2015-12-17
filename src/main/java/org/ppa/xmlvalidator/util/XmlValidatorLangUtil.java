package org.ppa.xmlvalidator.util;

import java.util.Arrays;

public class XmlValidatorLangUtil {

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

    /**
     * フィールドを全てShallow copyしたフィールドを返却
     * @param o
     * @return
     */
    public static <T> T clone(T o) {
        try {
            Class<?> clazz = o.getClass();

            @SuppressWarnings("unchecked")
            T ret = (T) (clazz.newInstance());

            Arrays.asList(clazz.getDeclaredFields())
                .stream()
                .forEach(f ->{
                    try {
                        f.set(ret, f.get(o));
                    } catch (IllegalArgumentException | IllegalAccessException e) {}
                });

            return ret;
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
            return null;
        }
    }
}
