/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.magicliang.dp;

/**
 * 用 dp 来解斐波那契问题
 * @author magicliang
 * @version $Id: FibonacciProblems.java, v 0.1 2018年11月04日 21:33 magicliang Exp $
 */
public class FibonacciProblems {

    public static int[] fib = new int[100];


    /**
     * 普通递归解
     * @param scale 问题规模
     * @return 数列第 n 位 值
     */
    public static int classicalSolution(int scale){
        if(scale == 1 || scale == 2) {
            return 1;
        }

        return classicalSolution(scale - 1) + classicalSolution(scale - 2);
    }

    /**
     * 基于自顶向下的动态规划的解
     * @param scale 问题规模
     * @return 数列第 n 位 值
     */
    public static int topDownSolution(int scale){
        int result;


        for (int i = 0; i < scale; i++) {
            fib[i] = Integer.MIN_VALUE;
        }

        int subProblemScale1 = scale - 1;
        if(scale == 1 || scale == 2) {
            result = 1;
            fib[subProblemScale1] = result;
            return result;
        }

        // scale -1 solution
        int subResult1 = fib[scale - 2];
        if ( subResult1 == Integer.MIN_VALUE) {
            subResult1 = classicalSolution(subProblemScale1);
            fib[scale - 2] = subResult1;
            System.out.print("计算项：" + subProblemScale1 + "，结果：" + subResult1);
        }

        int subProblemScale2 = scale - 3;
        int subResult2 = fib[subProblemScale2];
        if ( subResult2 == Integer.MIN_VALUE) {
            subResult2 = classicalSolution(scale - 2);
            fib[subProblemScale2] = subResult2;
            System.out.print("计算项：" + subProblemScale2 + "，结果：" + subResult2);
        }

        return subResult1 +  subResult2;
    }
}