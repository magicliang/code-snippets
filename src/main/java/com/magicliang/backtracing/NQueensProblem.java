/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.magicliang.backtracing;

import java.util.List;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * n 皇后问题的基类
 * @author magicliang
 * @version $Id: NQueensProblem.java, v 0.1 2018年11月15日 20:25 magicliang Exp $
 */
@Slf4j
public class NQueensProblem {

    @Setter
    protected int num;
    protected List<int[]> solutions;

    protected void printQueens() {
        for (int[] queenSolution : solutions) {
            log.info("打印一种皇后方案：");
            int length = queenSolution.length;
            // 按列遍历
            for (int i = 0; i < length; i++) {
                int column = queenSolution[i];
                StringBuilder row = new StringBuilder(length);
                for (int j = 0; j < length; j++) {
                    if (j != column) {
                        row.append("0");
                    } else {
                        row.append("1");
                    }
                }
                log.info(row.toString());
            }
        }
    }

    protected static void checkQueenNum(int num) {
        // 实际上只有四皇后以上才有解
        if (num <= 0) {
            throw new IllegalArgumentException("num can not be negative");
        }
    }

    protected static boolean noConflict(int[] solution, int row, int column) {
        // 只检查已经赋值的 其他row
        for (int existRow = 0; existRow < row; existRow++) {
            // 存在同行或者同列的值
            int existColumn = solution[existRow];
            if (existRow == row || existColumn == column) {
                return false;
            }

            int columnDelta = column - existColumn;
            //如果存在同一对角线上的皇后
            if (Math.abs(columnDelta) == row - existRow) {
                return false;
            }
        }
        return true;
    }

    protected static void initSolution(int[] solution) {
        int length = solution.length;
        for (int i = 0; i < length; i++) {
            solution[i] = -1;
        }
    }

}