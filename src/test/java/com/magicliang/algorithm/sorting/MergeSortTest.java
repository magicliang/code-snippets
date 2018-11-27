package com.magicliang.algorithm.sorting;

import com.magicliang.util.ArrayUtil;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liangchuan
 */
@Slf4j
public class MergeSortTest {

    @Test
    public void mergeSort() {
        Integer[] array = {9, 3, 6, 4, 5, 1};
        array = MergeSort.mergeSort(array);
        ArrayUtil.printArray(array);
    }
}