package com.magicliang.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 用 dp 来解斐波那契问题
 *
 * @author magicliang
 * @version $Id: FibonacciProblems.java, v 0.1 2018年11月04日 21:33 magicliang Exp $
 */
public class FibonacciProblems {
    private static final ThreadLocal<List<Integer>> tlFib =
            ThreadLocal.withInitial(() -> new ArrayList<>(0));

    /**
     * 普通递归解
     *
     * @param scale 问题规模
     * @return 数列第 n 位 值
     */
    public static int classicalSolution(int scale) {
        if (scale == 1 || scale == 2) {
            return 1;
        }

        return classicalSolution(scale - 1) + classicalSolution(scale - 2);
    }

    /**
     * 基于自顶向下的动态规划的解
     *
     * @param scale 问题规模
     * @return 数列第 n 位 值
     */
    public static int topDownSolution(int scale) {

        // 一种不使用 ThreadLocal的方案。即有一个主函数来初始化数组，并给真实调用的函数以县城封闭的数组。
        int[] fib = new int[scale];

        for (int i = 0; i < scale; i++) {
            fib[i] = Integer.MIN_VALUE;
        }

        return topDownSolutionReal(scale, fib);
    }

    private static int topDownSolutionReal(int scale, int[] fib) {
        int result;
        int fibIndex = scale - 1;

        if (scale == 1 || scale == 2) {
            result = 1;
            fib[fibIndex] = result;
            // System.out.println("计算项：" + scale + "，结果：" + result);
            return result;
        }

        // scale - 1 solution
        int subProblemScale1 = scale - 1;
        int fibIndex1 = subProblemScale1 - 1;
        int subResult1 = fib[fibIndex1];
        if (subResult1 == Integer.MIN_VALUE) {
            subResult1 = topDownSolutionReal(subProblemScale1, fib);
            fib[fibIndex1] = subResult1;
        }

        // scale - 2 solution
        int subProblemScale2 = scale - 2;
        int fibIndex2 = subProblemScale2 - 1;
        int subResult2 = fib[fibIndex2];
        if (subResult2 == Integer.MIN_VALUE) {
            subResult2 = topDownSolutionReal(subProblemScale2, fib);
            fib[fibIndex2] = subResult2;
        }

        result = subResult1 + subResult2;
        // System.out.println("计算项：" + scale + "，结果：" + result);
        return result;
    }

    public static int topDownSolutionWithThreadLocal(int scale) {

        // 一种不使用 ThreadLocal的方案。即有一个主函数来初始化数组，并给真实调用的函数以县城封闭的数组。
        List<Integer> fib = tlFib.get();
        if (fib.isEmpty()) {
            for (int i = 0; i < scale; i++) {
                fib.add(i, Integer.MIN_VALUE);
            }
        }

        int result;
        int fibIndex = scale - 1;

        if (scale == 1 || scale == 2) {
            result = 1;
            fib.set(fibIndex, result);
            System.out.println("计算项：" + scale + "，结果：" + result);
            return result;
        }

        // scale - 1 solution
        int subProblemScale1 = scale - 1;
        int fibIndex1 = subProblemScale1 - 1;
        int subResult1 = fib.get(fibIndex1);
        if (subResult1 == Integer.MIN_VALUE) {
            subResult1 = topDownSolutionWithThreadLocal(subProblemScale1);
            fib.set(fibIndex1, subResult1);
        }

        // scale - 2 solution
        int subProblemScale2 = scale - 2;
        int fibIndex2 = subProblemScale2 - 1;
        int subResult2 = fib.get(fibIndex2);
        if (subResult2 == Integer.MIN_VALUE) {
            subResult2 = topDownSolutionWithThreadLocal(subProblemScale2);
            fib.set(fibIndex2, subResult2);
        }

        result = subResult1 + subResult2;
        System.out.println("计算项：" + scale + "，结果：" + result);

        // TODO: 什么时候clear threadLocal
        return result;
    }

}