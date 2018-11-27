
package com.magicliang.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author magicliang
 * @version $Id: ArrayUtil.java, v 0.1 2018Äê11ÔÂ16ÈÕ 20:35 magicliang Exp $
 */
@Slf4j
public class ArrayUtil {

    public static <T> boolean isEmpty(T[] array) {
        return null == array || array.length == 0;
    }

    public static <T> void printArray(T[] array) {

        if (isEmpty(array)) {
            return;
        }

        StringBuilder stringBuilder = new StringBuilder(array.length);
        boolean first = true;
        for (T t : array) {
            if (first) {
                stringBuilder.append(t.toString());
            } else {
                stringBuilder.append(",").append(t);
            }
        }
        log.info(stringBuilder.toString());
    }
}