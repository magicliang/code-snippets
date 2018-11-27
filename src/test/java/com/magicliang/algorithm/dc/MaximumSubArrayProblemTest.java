package com.magicliang.algorithm.dc;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试最大子数组问题
 *
 * @author liangchuan
 */
@Slf4j
public class MaximumSubArrayProblemTest {

    Logger logger = LoggerFactory.getLogger(MaximumSubArrayProblemTest.class);

    private static final Integer[] array = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

    @Test
    public void getMaximumSubArray() {
        Integer[] maximumSubArray = MaximumSubArrayProblem.getMaximumSubArray(array);
        logger.info(maximumSubArray.toString());
    }
}