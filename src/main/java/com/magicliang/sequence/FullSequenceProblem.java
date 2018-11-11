
package com.magicliang.sequence;

import com.magicliang.util.MathUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
     * @param strs 原字符串组合
     * @return 字符串排列
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

        // 根据乘法原理，子问题的复杂度是全排列数，也就是子问题规模的阶乘
        int subProblemScale = MathUtil.caculateFactor(length - 1);
        // 只对长度大于2的字符串求解
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