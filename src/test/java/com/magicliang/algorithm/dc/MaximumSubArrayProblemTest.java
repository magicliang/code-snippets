package com.magicliang.algorithm.dc;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试最大子数组问题
 *
 * @author liangchuan
 */
@Slf4j
public class MaximumSubArrayProblemTest {

    private static final int[] array = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

    @Test
    public void getMaximumSubArray() {
        log.info(MaximumSubArrayProblem.getMaximumSubArray(array).toString());
    }
}