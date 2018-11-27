package com.magicliang.algorithm.sorting;/*
 *
 * @author magicliang
 * @version $Id: MergeSort.java, v 0.1 2018年11月27日 21:49 magicliang Exp $
 */

import com.magicliang.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class MergeSort {

    /**
     * 不能 new T 数组，但可以解读 T[] type parameter cannot be instantiated directly
     */
    public static <T extends Comparable<T>> T[] mergeSort(T[] array) {

        if (ArrayUtil.isEmpty(array)) {
            return null;
        }

        int length = array.length;

        return mergeSortReal(array, 0, length - 1);
    }

    private static <T extends Comparable<T>> T[] mergeSortReal(T[] array, int begin, int end) {
        if (ArrayUtil.isEmpty(array)) {
            return null;
        }

        int length = array.length;
        if (begin < 0 || end >= length) {
            throw new IllegalArgumentException(String.format("parameter invalid, begin: %s, end: %s", begin, end));
        }

        if (begin == end) {
            return Arrays.copyOfRange(array, begin, end + 1);
        }

        int mid = begin + (end - begin) / 2;

        T[] lowResult = mergeSortReal(array, begin, mid);
        T[] highResult = mergeSortReal(array, mid + 1, end);

        int i = 0;
        int j = 0;
        List<T> result = new ArrayList<>(length);
        while (true) {
            T lowValue = lowResult[i];
            T highValue = highResult[j];

            if (lowValue.compareTo(highValue) < 0) {
                result.add(lowValue);
                if (i < lowResult.length - 1) {
                    i++;
                }
            } else {
                result.add(highValue);
                if (j < highResult.length - 1) {
                    j++;
                }
            }
            if (i == lowResult.length - 1 && j == highResult.length - 1) {
                break;
            }
        }

        /**
         * T[] 也可以拿来转型
         */
        return (T[])result.toArray();

    }

}