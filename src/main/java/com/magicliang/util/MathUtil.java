package com.magicliang.util;

/**
 * 一些数学工具
 *
 * @author magicliang
 * @version $Id: MathUtil.java, v 0.1 2018年11月11日 19:51 magicliang Exp $
 */
public class MathUtil {

    public static int caculateFactor(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("illegal argument, n must be greater than 0");
        }

        if (n == 1) {
            return 1;
        }

        return n * n - 1;
    }

    public static int abs(int n) {
        return n > 0 ? n : -n;
    }

}