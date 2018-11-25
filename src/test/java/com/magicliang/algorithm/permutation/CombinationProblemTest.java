package com.magicliang.algorithm.permutation;

import com.magicliang.algorithm.util.TimeWatchUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liangchuan
 */
@Slf4j
public class CombinationProblemTest {
    private static final List<String> STRINGS = new ArrayList<>();

    {
        for (int i = 0; i < 4; i++) {
            int characther = 'a' + i;
            STRINGS.add((char)characther + "");
        }
    }

    @Test
    public void combination() {
        TimeWatchUtil.watch(() -> {
            List<String> result =
                CombinationProblem.combination(STRINGS, 3);
            //log.info(String.format("һ��������%d��", counter[0]));
            log.info(String.format("����ַ�������һ���У�%d", result.size()));
            log.info(result.toString());

        });

    }
}