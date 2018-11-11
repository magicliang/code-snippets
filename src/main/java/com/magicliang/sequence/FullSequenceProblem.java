
package com.magicliang.sequence;

import com.magicliang.util.MathUtil;

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

    /**
     * ����㷨��ʱ�临�Ӷȴ����O(n �� n �η�) �������ָ��ʱ��
     *
     * @param strs ԭ�ַ������
     * @return �ַ�������
     */
    public static List<String> getFullSequanceRecursively(List<String> strs, int[] counter) {

        if (null == counter || counter.length != 1) {
            throw new IllegalArgumentException("There must be only one counter");
        }

        counter[0] = ++counter[0];

        if (null == strs || strs.size() == 0) {
            return Collections.emptyList();
        }

        int length = strs.size();

        if (length == 1) {
            return strs;
        }

        // ���ݳ˷�ԭ��������ĸ��Ӷ���ȫ��������Ҳ�����������ģ�Ľ׳�
        int subProblemScale = MathUtil.caculateFactor(length - 1);
        // ֻ�Գ��ȴ���2���ַ������
        List<String> result = new ArrayList<>(subProblemScale);
        for (String basicString : strs) {
            List<String> others = getOtherStrList(strs, basicString);

            for (String newStr : getFullSequanceRecursively(others, counter)) {
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