package org.ppa.xasx.util;

import java.util.Arrays;

public class XasXLangUtil {

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
    public static <T> T cloneInstance(T o) {
        try {
            Class<?> clazz = o.getClass();

            @SuppressWarnings("unchecked")
            T ret = (T)(clazz.newInstance());

            Arrays.asList(clazz.getDeclaredFields())
                .stream()
                .forEach(f ->{
                    try {
                        f.setAccessible(true);
                        f.set(ret, f.get(o));
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                    }
                });

            return ret;
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * 例外を送出しないClass.forName
     * @param className
     * @return
     */
    public static Object classForNameQuietly(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
