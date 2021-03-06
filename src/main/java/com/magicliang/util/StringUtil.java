
package com.magicliang.util;

/**
 * 字符串工具
 *
 * @author magicliang
 * @version $Id: StringUtil.java, v 0.1 2018年11月16日 20:31 magicliang Exp $
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return null == str || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str) {
        return isEmpty(str) || str.trim().length() == 0;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

}