package com.magicliang.dp;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liangchuan
 */
public class FibonacciProblemsTest {

    @Test
    public void classicalSolution() {
        Assert.assertEquals(1, FibonacciProblems.classicalSolution(1));
        Assert.assertEquals(1, FibonacciProblems.classicalSolution(2));
        Assert.assertEquals(2, FibonacciProblems.classicalSolution(3));
        Assert.assertEquals(3, FibonacciProblems.classicalSolution(4));
        Assert.assertEquals(5, FibonacciProblems.classicalSolution(5));
        Assert.assertEquals(8, FibonacciProblems.classicalSolution(6));
        Assert.assertEquals(13, FibonacciProblems.classicalSolution(7));
        Assert.assertEquals(21, FibonacciProblems.classicalSolution(8));
    }

    @Test
    public void topDownSolution() {
        Assert.assertEquals(6765, FibonacciProblems.topDownSolution(20));
    }
}