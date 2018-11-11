
package com.magicliang.permutation;

import com.magicliang.util.MathUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
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
     * @param strs    ���ַ����б�
     * @param counter ������
     * @return ��Ϲ����ַ������б�
     */
    public static List<String> permutationRecursively(List<String> strs, int[] counter) {

        checkAndIncrease(counter);

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

            for (String newStr : permutationRecursively(others, counter)) {
                result.add(basicString + newStr);
            }
        }

        return result;
    }

    public static List<String> dpPermutation(List<String> strs, int[] counter) {
        Map<String, List<String>> memo = new HashMap<>();
        return dpPermutationReal(strs, counter, memo);
    }

    /**
     * ʹ�� dp �ı���¼�����������
     *
     * @param strs    ���ַ����б�
     * @param counter ������
     * @param memo    ����¼
     * @return ��Ϲ����ַ������б�
     */
    private static List<String> dpPermutationReal(List<String> strs, int[] counter, Map<String, List<String>> memo) {

        checkAndIncrease(counter);

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

            String sortedKey = generateSortedKey(others);

            List<String> smallResult;

            if (memo.containsKey(sortedKey)) {
                smallResult = memo.get(sortedKey);
            } else {
                smallResult = dpPermutationReal(others, counter, memo);
                memo.put(sortedKey, smallResult);
            }

            for (String newStr : smallResult) {
                result.add(basicString + newStr);
            }
        }

        return result;

    }

    /**
     * ����ط�����Ե����ϣ������ʲ��ʺ�ʹ�� dp����Ϊ��ÿһ���ѡ���Ƕ�̬�仯�ġ�
     **/

    private static void checkAndIncrease(int[] counter) {
        if (null == counter || counter.length != 1) {
            throw new IllegalArgumentException("There must be only one counter");
        }

        counter[0] = ++counter[0];
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

    private static String generateSortedKey(List<String> others) {
        others.sort(Comparator.naturalOrder());
        StringJoiner joiner = new StringJoiner(",");

        for (String s : others) {
            joiner.add(s);
        }

        return joiner.toString();
    }

}