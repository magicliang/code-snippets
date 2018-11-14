/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.magicliang.backtracing;

import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 解决八皇后问题
 *
 * @author magicliang
 * @version $Id: EightQueensProblem.java, v 0.1 2018年11月14日 20:12 magicliang Exp $
 */
@Slf4j
@Data
public class EightQueensProblem {

    private List<int[]> queens;

    private int[][] matrix;

    public static void buildQueens(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("num can not be negative");
        }

        EightQueensProblem eightQueensProblem = new EightQueensProblem();
        int[][] matrix = new int[num][num];

    }
}