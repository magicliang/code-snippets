
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
 * 全排列问题
 *
 * @author magicliang
 * @version $Id: FullSequenceProblem.java, v 0.1 2018年11月11日 18:55 magicliang Exp $
 */
public class FullSequenceProblem {

    public static List<String> getUniqueStrs(List<String> strs) {
        return strs.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 这个算法的时间复杂度大概是O(n 的 n 次方) 超级大的指数时间
     *
     * @param strs    待字符串列表
     * @param counter 计数器
     * @return 组合过的字符串的列表
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

        // 根据乘法原理，子问题的复杂度是全排列数，也就是子问题规模的阶乘
        int subProblemScale = MathUtil.caculateFactor(length - 1);
        // 只对长度大于2的字符串求解
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
     * 使用 dp 的备忘录来解这个问题
     *
     * @param strs    待字符串列表
     * @param counter 计数器
     * @param memo    备忘录
     * @return 组合过的字符串的列表
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

        // 根据乘法原理，子问题的复杂度是全排列数，也就是子问题规模的阶乘
        int subProblemScale = MathUtil.caculateFactor(length - 1);
        // 只对长度大于2的字符串求解
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
     * 这个地方如果自底向上，到底适不适合使用 dp？因为它每一层的选择都是动态变化的。
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