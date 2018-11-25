package com.magicliang.algorithm.util;

import lombok.extern.slf4j.Slf4j;

/**
 * 自动计时的工具
 *
 * @author magicliang
 * @version $Id: TimeWatchUtil.java, v 0.1 2018年11月11日 19:42 magicliang Exp $
 */
@Slf4j
public class TimeWatchUtil {

    public static void watch(Runnable runnable) {
        long begin = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        long ellapsed = end - begin;
        log.info("一共用时：" + ellapsed);
    }
}