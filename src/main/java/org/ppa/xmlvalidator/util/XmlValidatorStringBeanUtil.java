package org.ppa.xmlvalidator.util;

import java.lang.reflect.InvocationTargetException;

/**
 * XML
 */
public class XmlValidatorStringBeanUtil {

    /**
     * String型のプロパティを取得します。
     * @param bean
     * @param name
     * @return
     */
    public static String getStringtProperty(Object bean, String name)  {
        String methodName1 = "is" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
        String methodName2 = "get" + Character.toUpperCase(name.charAt(0)) + name.substring(1);

        Class<?> clazz = bean.getClass();
        try {
            return (String)clazz.getMethod(methodName1).invoke(bean);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            try {
                Object ret = clazz.getMethod(methodName2).invoke(bean);
                return (ret instanceof String) ? (String)ret : null;
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException ex) {
                return null;
            }
        }
    }
   /**
    * String型のプロパティを設定します。
    * @param bean
    * @param name
    * @return
    */
   public static void setStringProperty(Object bean, String name, String value)  {
       String methodName = "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);

       Class<?> clazz = bean.getClass();
       try {
           clazz.getMethod(methodName, String.class).invoke(bean, value);
       } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException e) {
           // ignore
       }
   }
}
