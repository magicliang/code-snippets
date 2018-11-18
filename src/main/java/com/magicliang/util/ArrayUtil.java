/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.magicliang.util;

/**
 * @author magicliang
 * @version $Id: ArrayUtil.java, v 0.1 2018Äê11ÔÂ16ÈÕ 20:35 magicliang Exp $
 */
public class ArrayUtil {

    public static boolean isEmpty(int[] array) {
        return null == array || array.length == 0;
    }

    public static boolean isEmpty(String[] array) {
        return null == array || array.length == 0;
    }
}