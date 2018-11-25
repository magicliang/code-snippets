package com.magicliang.algorithm.backtracing;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author liangchuan
 */
public class NQueensProblemWithMatrixTest {

    @Test
    public void getQueens() {

        //NQueensProblemWithMatrix queensProblemWithMatrix = NQueensProblemWithMatrix.buildQueens(8);
        //Assert.assertEquals(92, queensProblemWithMatrix.getSolutions().size());
        //
        //NQueensProblemWithArray queensProblemWithArray = NQueensProblemWithArray.buildQueens(8);
        //Assert.assertEquals(92, queensProblemWithArray.getSolutions().size());

        Assert.assertEquals(92, NQueensProblemWithIteration.buildQueens(8));
    }
}