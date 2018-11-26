package com.magicliang.algorithm.dc;

/*
 * @author magicliang
 * @version $Id: MaximumSubArrayProblem.java, v 0.1 2018��11��25�� 20:47 magicliang Exp $
 */

import com.magicliang.util.ArrayUtil;

import java.util.Arrays;

public class MaximumSubArrayProblem {

    public static int[] getMaximumSubArray(int[] array) {
        if (ArrayUtil.isEmpty(array)) {
            return null;
        }

        int length = array.length;
        if (1 == length) {
            return array;
        }
        array = generatePaddingArray(array);

        return getMaximumSubArrayReal(array, 1, length);
    }

    private static int[] generatePaddingArray(int[] array) {
        int length = array.length;
        int[] result = new int[length + 1];
        result[0] = 0;
        System.arraycopy(array, 0, result, 0, length);
        return result;
    }

    private static int[] getMaximumSubArrayReal(int[] array, int begin, int end) {
        int length = end - begin;
        if (0 == length) {
            return array;
        }
        // 以 1 为底的数组，mid 总是偏左
        int mid = begin + length / 2;
        int[] lowResult = getMaximumSubArrayReal(array, begin, mid);
        int[] highResult = getMaximumSubArrayReal(array, mid + 1, end);
        int[] acrossMidResult = getMaximumSubArrayAcrossMid(array, mid);

        int[] result = getHighestValueArray(new int[][] {lowResult, highResult, acrossMidResult});
        return result;
    }

    private static int[] getHighestValueArray(int[][] arrays) {
        int maxValue = Integer.MIN_VALUE;
        int[] maxArray = null;
        for (int[] array : arrays) {
            int value = getArrayValue(array, 0, array.length - 1);
            if (value > maxValue) {
                maxValue = value;
                maxArray = array;
            }
        }
        return maxArray;
    }

    private static int[] getMaximumSubArrayAcrossMid(int[] array, int mid) {
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
    private static int getArrayValue(int[] array, int i, int j) {
        //TODO: defensive programming

        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += array[k];
        }
        return sum;
    }

}