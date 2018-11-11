package com.magicliang.sequence;

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
public class FullSequenceProblemTest {

    @Test
    public void getFullSequanceRecursively() {

        List<String> strs = new ArrayList<>(4);
        strs.add("a");
        strs.add("b");
        strs.add("c");
        strs.add("d");
        strs.add("f");
        strs.add("g");

        TimeWatchUtil.watch(() -> {
            int[] counter = {0};
            List<String> result = FullSequenceProblem.getUniqueStrs(
                FullSequenceProblem.getFullSequanceRecursively(strs, counter));
            log.info(String.format("一共递归调用了%d次", counter[0]));
            log.info(String.format("结果字符串数量一共有：%d", result.size()));
            log.info(result.toString());

        });

    }
}