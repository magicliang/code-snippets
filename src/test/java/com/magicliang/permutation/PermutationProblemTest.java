package com.magicliang.permutation;

import com.magicliang.util.TimeWatchUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试全排列问题
 *
 * @author liangchuan
 */
@Slf4j
public class PermutationProblemTest {

    private static final List<String> STRINGS = new ArrayList<>();

    {
        for (int i = 0; i < 3; i++) {
            int characther = 'a' + i;
            STRINGS.add((char)characther + "");
        }
    }

    @Test
    public void getFullSequanceRecursively() {
        TimeWatchUtil.watch(() -> {
            int[] counter = {0};
            List<String> result =
                PermutationProblem.permutationRecursively(STRINGS, counter);
            log.info(String.format("一共调用了%d次", counter[0]));
            log.info(String.format("结果字符串数量一共有：%d", result.size()));
            log.info(result.toString());

        });
        log.info("递归求解字符串全集合结束");
    }

    @Test
    public void dpPermutation() {

        TimeWatchUtil.watch(() -> {
            int[] counter = {0};
            List<String> result =
                PermutationProblem.dpPermutation(STRINGS, counter);
            log.info(String.format("一共调用了%d次", counter[0]));
            log.info(String.format("结果字符串数量一共有：%d", result.size()));
            // log.info(result.toString());

        });

        log.info("动态规划求解字符串全集合结束");

    }

    @Test
    public void switchPermutation() {
        TimeWatchUtil.watch(() -> {
            int[] counter = {0};
            List<String> result =
                PermutationProblem.switchPermutation(STRINGS, counter);
            log.info(String.format("一共调用了%d次", counter[0]));
            log.info(String.format("结果字符串数量一共有：%d", result.size()));
            log.info(result.toString());

        });

        log.info("用字符串反转再分治解子问题的方法求解字符串全集合结束");
    }
}