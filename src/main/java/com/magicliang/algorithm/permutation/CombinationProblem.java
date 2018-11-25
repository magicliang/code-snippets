package com.magicliang.algorithm.permutation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 解决字符串组合问题
 *
 * @author LC
 * @version $Id: CombinationProblem.java, v 0.1 2018年11月12日 23:42 LC Exp $
 */
public class CombinationProblem {

    public static List<String> combination(List<String> args, int combinationSize) {
        // 有多少个参数，就有多少个地方要做防御性编程检查
        if (null == args || 0 == args.size()) {
            return Collections.EMPTY_LIST;
        }

        int length = args.size();
        if (combinationSize > length) {
            throw new IllegalArgumentException("combinationSize can not be greater than args size.");
        }

        // 有多少个参数，就有多少个地方要提前终止
        // 如果数组走到尽头，或者排列数走到尽头
        if (1 == length || combinationSize == 1) {
            return args;
        }

        List<String> result = new ArrayList<>(combinationSize);
        String head = args.get(0);
        // 组合其实是无放回的过程，所以千万不要试着用 for 循环来逐一对子问题进行切割，那必然引入重叠子问题。
        // 这种组合方式因为无重叠子问题，所以实际上是无法用 dp 来解的。
        List<String> combination = combination(args.subList(1, length), combinationSize - 1);
        for (String subStr : combination) {
            result.add(head + subStr);
        }
        if (length - 1 >= combinationSize) {
            List<String> otherCombination = combination(args.subList(1, length), combinationSize);
            result.addAll(otherCombination);
        }

        return result;
    }
}