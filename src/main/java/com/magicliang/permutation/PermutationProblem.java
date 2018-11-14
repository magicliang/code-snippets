
package com.magicliang.permutation;

import com.magicliang.util.CollectionUtil;
import com.magicliang.util.MathUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * 全排列问题
 *
 * @author magicliang
 * @version $Id: PermutationProblem.java, v 0.1 2018年11月11日 18:55 magicliang Exp $
 */
@Slf4j
public class PermutationProblem {

    public static void main(String[] args) {
    }

    public static List<String> getUniqueStrs(List<String> strs) {

        return strs.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 这个算法的时间复杂度大概是O(n 的 n 次方) 超级大的指数时间
     * 如何让代码原地工作？
     * @param strs    待字符串列表
     * @param counter 计数器
     * @return 组合过的字符串的列表
     */
    public static List<String> permutationRecursively(List<String> strs, int[] counter) {

        if (null == strs || strs.size() == 0) {
            return Collections.emptyList();
        }

        checkAndIncrease(counter);

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
        if (null == strs || strs.size() == 0) {
            return Collections.emptyList();
        }

        Map<String, List<String>> memo = new HashMap<>(MathUtil.caculateFactor(strs.size()));
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
     * 这个方案其实等价于全递归，因为涉及到了重复的解。 优点是，不用构造 other string 数组，空间上更加优越。
     *
     * @param strs    待字符串列表
     * @param counter 计数器
     * @return 组合过的字符串的列表
     */
    public static List<String> switchPermutation(List<String> strs, int[] counter) {
        if (null == strs || 0 == strs.size()) {
            return Collections.EMPTY_LIST;
        }

        checkAndIncrease(counter);

        int length = strs.size();

        if (length == 1) {
            return strs;
        }

        List<String> result = new ArrayList<>(MathUtil.caculateFactor(length));
        // 先把问题简化一下，把所有待排列的第一位先排好
        for (int i = 0; i < length; i++) {
            // 这里这个交换，和反交换，实质上就起到了 getOtherStrList 的作用
            // 交换当前的头结点和后续的几个结点
            CollectionUtil.swapListItem(strs, 0, i);

            List<String> subResult = switchPermutation(strs.subList(1, length), counter);
            for (String subString : subResult) {
                result.add(strs.get(0) + subString);
            }

            //反转交换，再下一轮再交换一次
            CollectionUtil.swapListItem(strs, 0, i);
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