package com.magicliang.util;

/**
 * һЩ��ѧ����
 *
 * @author magicliang
 * @version $Id: MathUtil.java, v 0.1 2018��11��11�� 19:51 magicliang Exp $
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
}