
package com.fantasybaby.algorithm.util;

import java.lang.reflect.Field;

/**
 * @author reid.liu
 * @date 2019-02-25 11:38
 */
public class FiledValueHelper {
    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    public  <T> T getAllFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return  (T)field.get(object);
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param object
     * @return
     *
     */
    public static void setFieldValueByFieldName(String fieldName, String value,Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            field.set(object,value);
        } catch (Exception e) {
        }
    }
    /**
     * 根据属性名获取属性元素，包括各种安全范围和所有父类
     *
     * @param fieldName
     * @param object
     * @return
     */
    public static <T> T getFieldByClasss(String fieldName, Object object) {
        Field field = null;
        Class<?> clazz = object.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return  (T)field.get(object);
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
