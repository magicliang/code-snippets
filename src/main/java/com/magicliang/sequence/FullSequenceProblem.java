/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.magicliang.sequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ȫ��������
 *
 * @author magicliang
 * @version $Id: FullSequenceProblem.java, v 0.1 2018��11��11�� 18:55 magicliang Exp $
 */
public class FullSequenceProblem {

    public static List<String> getUniqueStrs(List<String> strs) {
        return strs.stream().distinct().collect(Collectors.toList());
    }

    public static List<String> getFullSequanceRecursively(List<String> strs) {

        if (null == strs || strs.size() == 0) {
            return Collections.emptyList();
        }

        int length = strs.size();

        if (length == 1) {
            return strs;
        }

        // ֻ�Գ��ȴ���2���ַ������
        List<String> result = new ArrayList<>();
        for (String basicString : strs) {
            List<String> others = getOtherStrList(strs, basicString);

            for (String newStr : getFullSequanceRecursively(others)) {
                result.add(basicString + newStr);
            }
        }

        return result;

    }

    private static List<String> getOtherStrList(List<String> strs, String basicString) {
        List<String> result = new ArrayList<>(strs.size() - 1);

        for (String s : strs) {
            if (!s.equals(basicString)) {
                result.add(s);
            }
        }

        return result;
    }

}