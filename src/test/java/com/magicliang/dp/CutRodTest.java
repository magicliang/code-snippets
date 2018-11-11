package com.magicliang.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试钢条切割问题
 *
 * @author liangchuan
 */
public class CutRodTest {
    int[] price = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    @Test
    public void cutRodWithMemoized() {
        Assert.assertEquals(1, CutRod.topDownCutRodWithMemoized(price, 1));
        Assert.assertEquals(5, CutRod.topDownCutRodWithMemoized(price, 2));
        Assert.assertEquals(8, CutRod.topDownCutRodWithMemoized(price, 3));
        Assert.assertEquals(10, CutRod.topDownCutRodWithMemoized(price, 4));
        Assert.assertEquals(13, CutRod.topDownCutRodWithMemoized(price, 5));
        Assert.assertEquals(17, CutRod.topDownCutRodWithMemoized(price, 6));
        Assert.assertEquals(18, CutRod.topDownCutRodWithMemoized(price, 7));
        Assert.assertEquals(22, CutRod.topDownCutRodWithMemoized(price, 8));
        Assert.assertEquals(25, CutRod.topDownCutRodWithMemoized(price, 9));
        Assert.assertEquals(30, CutRod.topDownCutRodWithMemoized(price, 10));

    }

    @Test
    public void bottomUpCutRodWithMemoized() {
        Assert.assertEquals(1, CutRod.bottomUpCutRodWithMemoized(price, 1));
        Assert.assertEquals(5, CutRod.bottomUpCutRodWithMemoized(price, 2));
        Assert.assertEquals(8, CutRod.bottomUpCutRodWithMemoized(price, 3));
        Assert.assertEquals(10, CutRod.bottomUpCutRodWithMemoized(price, 4));
        Assert.assertEquals(13, CutRod.bottomUpCutRodWithMemoized(price, 5));
        Assert.assertEquals(17, CutRod.bottomUpCutRodWithMemoized(price, 6));
        Assert.assertEquals(18, CutRod.bottomUpCutRodWithMemoized(price, 7));
        Assert.assertEquals(22, CutRod.bottomUpCutRodWithMemoized(price, 8));
        Assert.assertEquals(25, CutRod.bottomUpCutRodWithMemoized(price, 9));
        Assert.assertEquals(30, CutRod.bottomUpCutRodWithMemoized(price, 10));
    }
}