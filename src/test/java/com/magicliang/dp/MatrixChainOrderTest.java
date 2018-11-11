package com.magicliang.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试矩阵链乘法问题
 * @author liangchuan
 */
public class MatrixChainOrderTest {

    private static final int[] MATRIX_ROWS = {30, 35, 15, 5, 10, 20, 25};


    @Test
    public void matrixChainOrder() {

        MatrixChainOrder result = MatrixChainOrder.optimalOrderBottomUp(MATRIX_ROWS);

        Assert.assertEquals(0, result.getMinCost()[1][1]);
        Assert.assertEquals(15750, result.getMinCost()[1][2]);
        Assert.assertEquals(7875, result.getMinCost()[1][3]);
        Assert.assertEquals(9375, result.getMinCost()[1][4]);
        Assert.assertEquals(11875, result.getMinCost()[1][5]);
        Assert.assertEquals(15125, result.getMinCost()[1][6]);

        Assert.assertEquals(0, result.getMinCost()[2][2]);
        Assert.assertEquals(2625, result.getMinCost()[2][3]);
        Assert.assertEquals(4375, result.getMinCost()[2][4]);
        Assert.assertEquals(7125, result.getMinCost()[2][5]);
        Assert.assertEquals(10500, result.getMinCost()[2][6]);

        Assert.assertEquals(0, result.getMinCost()[3][3]);
        Assert.assertEquals(750, result.getMinCost()[3][4]);
        Assert.assertEquals(2500, result.getMinCost()[3][5]);
        Assert.assertEquals(5375, result.getMinCost()[3][6]);

        Assert.assertEquals(0, result.getMinCost()[4][4]);
        Assert.assertEquals(1000, result.getMinCost()[4][5]);
        Assert.assertEquals(3500, result.getMinCost()[4][6]);

        Assert.assertEquals(0, result.getMinCost()[5][5]);
        Assert.assertEquals(5000, result.getMinCost()[5][6]);

        Assert.assertEquals(0, result.getMinCost()[6][6]);

        result.printOptimalOrder(1, 6);
    }

    @Test
    public void optimalOrderTopDown() {

        MatrixChainOrder result = MatrixChainOrder.optimalOrderTopDown(MATRIX_ROWS);

        Assert.assertEquals(0, result.getMinCost()[1][1]);
        Assert.assertEquals(15750, result.getMinCost()[1][2]);
        Assert.assertEquals(7875, result.getMinCost()[1][3]);
        Assert.assertEquals(9375, result.getMinCost()[1][4]);
        Assert.assertEquals(11875, result.getMinCost()[1][5]);
        Assert.assertEquals(15125, result.getMinCost()[1][6]);

        Assert.assertEquals(0, result.getMinCost()[2][2]);
        Assert.assertEquals(2625, result.getMinCost()[2][3]);
        Assert.assertEquals(4375, result.getMinCost()[2][4]);
        Assert.assertEquals(7125, result.getMinCost()[2][5]);
        Assert.assertEquals(10500, result.getMinCost()[2][6]);

        Assert.assertEquals(0, result.getMinCost()[3][3]);
        Assert.assertEquals(750, result.getMinCost()[3][4]);
        Assert.assertEquals(2500, result.getMinCost()[3][5]);
        Assert.assertEquals(5375, result.getMinCost()[3][6]);

        Assert.assertEquals(0, result.getMinCost()[4][4]);
        Assert.assertEquals(1000, result.getMinCost()[4][5]);
        Assert.assertEquals(3500, result.getMinCost()[4][6]);

        Assert.assertEquals(0, result.getMinCost()[5][5]);
        Assert.assertEquals(5000, result.getMinCost()[5][6]);

        Assert.assertEquals(0, result.getMinCost()[6][6]);

        result.printOptimalOrder(1, 6);
    }
}