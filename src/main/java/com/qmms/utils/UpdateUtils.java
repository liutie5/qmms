package com.qmms.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateUtils {

    public static boolean updateNotNullField(Object rawObject, Object newObject) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //如果连个对象不一致。不进行更新字段值的操作
        if (rawObject.getClass().getName() != newObject.getClass().getName()) {
            return false;
        }
        //获取原始对象中的所有public方法
        Method[] methods = rawObject.getClass().getDeclaredMethods();
        //用于提取不包含指定关键词的方法
        String regExpression = "^(get)(?!Id|AddTime)(\\w+)";
        Pattern pattern = Pattern.compile(regExpression);
        Matcher m;
        for (Method method : methods) {
            m = pattern.matcher(method.getName());
            //正则匹配以get开头，后面不能匹配Id、CreateTime这两个单词的方法
            if (m.find()) {
                Object o = method.invoke(newObject, null);
                //忽略值为空的字段
                if (o == null) {
                    continue;
                }
                //取出get方法名后面的字段名
                String fieldName = m.group(2);
                //找到该字段名的set方法
                Method rawMethod = rawObject.getClass().getMethod("set" + fieldName, method.getReturnType());
                //调用实体对象的set方法更新字段值
                rawMethod.invoke(rawObject, o);
            }
        }
        return true;
    }
}
