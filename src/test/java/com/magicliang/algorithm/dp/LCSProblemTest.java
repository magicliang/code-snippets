package com.magicliang.algorithm.dp;

import com.magicliang.algorithm.util.MatrixUtil;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试 lcs 解法
 *
 * @author liangchuan
 */
@Slf4j
public class LCSProblemTest {

    private static final String[] SEQUENCE_A = {"A", "B", "C", "B", "D", "A", "B"};
    private static final String[] SEQUENCE_B = {"B", "D", "C", "A", "B", "A"};

    @Test
    public void dpLCSProblem() {

        LCSProblem lcsProblem = LCSProblem.dpLCSProblem(SEQUENCE_A, SEQUENCE_B);

        MatrixUtil.printStringMatrix(lcsProblem.getLcsSolutionMatrix());
        log.info(lcsProblem.getLCSStr());
    }
}