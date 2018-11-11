package com.magicliang.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试 fibonacci 问题
 * @author liangchuan
 */
public class FibonacciProblemsTest {

    @Test
    public void classicalSolution() {
//        Assert.assertEquals(1, FibonacciProblems.classicalSolution(1));
//        Assert.assertEquals(1, FibonacciProblems.classicalSolution(2));
//        Assert.assertEquals(2, FibonacciProblems.classicalSolution(3));
//        Assert.assertEquals(3, FibonacciProblems.classicalSolution(4));
//        Assert.assertEquals(5, FibonacciProblems.classicalSolution(5));
//        Assert.assertEquals(8, FibonacciProblems.classicalSolution(6));
//        Assert.assertEquals(13, FibonacciProblems.classicalSolution(7));
        Assert.assertEquals(21, FibonacciProblems.classicalSolution(8));
    }

    /**
     * Assert.assertEquals(1, FibonacciProblems.topDownSolution(1));
     * Assert.assertEquals(1, FibonacciProblems.topDownSolution(2));
     * Assert.assertEquals(2, FibonacciProblems.topDownSolution(3));
     * Assert.assertEquals(3, FibonacciProblems.topDownSolution(4));
     * Assert.assertEquals(5, FibonacciProblems.topDownSolution(5));
     * Assert.assertEquals(8, FibonacciProblems.topDownSolution(6));
     * Assert.assertEquals(13, FibonacciProblems.topDownSolution(7));
     * Assert.assertEquals(21, FibonacciProblems.topDownSolution(8));
     */
    @Test
    public void topDownSolution() {

        long begin = System.currentTimeMillis();
        Assert.assertEquals(102334155, FibonacciProblems.topDownSolution(40));
        long end =  System.currentTimeMillis();
        long elapsed = end - begin;

        System.out.println("用时：" + elapsed);
    }

//    @Test
//    public void topDownSolutionWithThreadLocal() {
//        long begin = System.currentTimeMillis();
//        Assert.assertEquals(102334155, FibonacciProblems.topDownSolutionWithThreadLocal(40));
//        long end = System.currentTimeMillis();
//        long elapsed = end - begin;
//
//        System.out.println("用时：" + elapsed);
//    }

    @Test
    public void bottomUpSolution() {
        long begin = System.currentTimeMillis();
        Assert.assertEquals(102334155, FibonacciProblems.bottomUpSolution(40));
        long end = System.currentTimeMillis();
        long elapsed = end - begin;

        System.out.println("用时：" + elapsed);
    }
}