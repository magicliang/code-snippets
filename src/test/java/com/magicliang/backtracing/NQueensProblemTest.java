package com.magicliang.backtracing;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author liangchuan
 */
public class NQueensProblemTest {

    @Test
    public void getQueens() {

        NQueensProblem queensProblem = NQueensProblem.buildQueens(8);

        Assert.assertEquals(92, queensProblem.getQueens().size());
    }
}