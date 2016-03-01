package org.ppa.xasx.util;

import java.lang.reflect.Field;

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

            copyAs(ret, o, clazz);

            return ret;
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
            return null;
        }
    }
    /**
     * フィールドを全てShallow copyしたフィールドを返却
     * @param o
     * @return
     */
    private static void copyAs(Object dest, Object src, Class<?> clazz) {
        try {

            for (Field f : clazz.getDeclaredFields()) {
                try {
                    f.setAccessible(true);
                    f.set(dest, f.get(src));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    // do nothing
                }
            }
            Class<?> superClazz = clazz.getSuperclass();
            if (superClazz != Object.class) {
                copyAs(dest, src, superClazz);
            }
        } catch (SecurityException | IllegalArgumentException e) {
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

    /**
     * targetが、続く値のグループに含まれている場合は true を返却する。
     *
     * @param target
     * @param inEquals
     * @return
     */
    public static boolean isIn(Object target, Object ... inEquals) {
        if (target == null) {
            for (Object test : inEquals) {
                if (test == null)
                    return true;
            }
            return false;
        }

        for (Object test : inEquals) {
            if (target.equals(test)) {
                return true;
            }
        }
        return false;
    }
    /**
     * targetが、続く値のグループに含まれている場合は true を返却する。
     *
     * @param target
     * @param inEquals
     * @return
     */
    public static boolean isNotIn(Object target, Object ... inEquals) {
        return !isIn(target, inEquals);
    }
}
