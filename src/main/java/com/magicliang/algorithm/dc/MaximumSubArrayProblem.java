package com.magicliang.algorithm.dc;

/*
 * @author magicliang
 * @version $Id: MaximumSubArrayProblem.java, v 0.1 2018��11��25�� 20:47 magicliang Exp $
 */

import com.magicliang.util.ArrayUtil;

import java.util.Arrays;

public class MaximumSubArrayProblem {

    public static Integer[] getMaximumSubArray(Integer[] array) {
        if (ArrayUtil.isEmpty(array)) {
            return null;
        }

        int length = array.length;
        if (1 == length) {
            return array;
        }
        //array = generatePaddingArray(array);

        return getMaximumSubArrayReal(array, 1, length);
    }

    private static int[] generatePaddingArray(Integer[] array) {
        int length = array.length;
        int[] result = new int[length + 1];
        result[0] = 0;
        System.arraycopy(array, 0, result, 0, length);
        return result;
    }

    private static Integer[] getMaximumSubArrayReal(Integer[] array, int begin, int end) {
        int delta = end - begin;
        if (0 == delta) {
            return array;
        }
        // 差值 delta / 2 总是偏左的结果
        int mid = begin + delta / 2;
        Integer[] lowResult = getMaximumSubArrayReal(array, begin, mid);
        Integer[] highResult = getMaximumSubArrayReal(array, mid + 1, end);
        Integer[] acrossMidResult = getMaximumSubArrayAcrossMid(array, mid);

        Integer[] result = getHighestValueArray(new Integer[][] {lowResult, highResult, acrossMidResult});
        return result;
    }

    private static Integer[] getHighestValueArray(Integer[][] arrays) {
        int maxValue = Integer.MIN_VALUE;
        Integer[] maxArray = null;
        for (Integer[] array : arrays) {
            int value = getArrayValue(array, 0, array.length - 1);
            if (value > maxValue) {
                maxValue = value;
                maxArray = array;
            }
        }
        return maxArray;
    }

    private static Integer[] getMaximumSubArrayAcrossMid(Integer[] array, int mid) {
        int length = array.length;
        int maxValue = Integer.MIN_VALUE;
        int maxI = mid;
        int maxJ = mid;

        int i = mid;
        int j = mid;

        while (true) {
            int currentValue = getArrayValue(array, i, j);
            if (currentValue > maxValue) {
                maxValue = currentValue;
                maxI = i;
                maxJ = j;
            }

            // 提前终止游标
            if (j < length - 1) {
                j++;
            }

            if (i > 0) {
                i--;
            }

            // 两个游标都越界了，则可以正式终止 while 循环。
            if (i == 0 && j == length - 1) {
                break;
            }
        }

        // 要按照最原始的 maxI， maxJ 来求值。
        return Arrays.copyOfRange(array, maxI, maxJ);
    }

    /**
     *
     * @param array
     * @param i
     * @param j
     * @return
     */
    private static int getArrayValue(Integer[] array, int i, int j) {
        //TODO: defensive programming

        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += array[k];
        }
        return sum;
    }

}