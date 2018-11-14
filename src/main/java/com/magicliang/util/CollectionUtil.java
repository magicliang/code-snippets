package com.magicliang.util;

import java.util.List;

/**
 * �Լ��ϲ����Ĺ�����
 *
 * @author LC
 * @version $Id: CollectionUtil.java, v 0.1 2018��11��13�� 0:06 LC Exp $
 */
public class CollectionUtil {

    public static <T> List<T> swapListItem(List<T> list, int i, int j) {
        checkBoundaries(list, "i", i);
        checkBoundaries(list, "j", j);
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
        return list;
    }

    private static <T> void checkBoundaries(List<T> list, String indexName, int i) {
        if (i < 0 || i >= list.size()) {
            throw new IllegalArgumentException(String.format("index %s out of bounds: %d", indexName, i));
        }
    }
}