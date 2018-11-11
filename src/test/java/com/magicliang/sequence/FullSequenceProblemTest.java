package com.magicliang.sequence;

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

        List<String> result = FullSequenceProblem.getUniqueStrs(FullSequenceProblem.getFullSequanceRecursively(strs));
        log.info(result.size() + "");
        log.info(result.toString());
    }
}