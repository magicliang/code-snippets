package com.magicliang.backtracing;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author liangchuan
 */
public class EightQueensProblemTest {

    @Test
    public void getQueens() {

        EightQueensProblem queensProblem = EightQueensProblem.buildQueens(4);

        Assert.assertEquals(92, queensProblem.getQueens().size());
    }
}