/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.magicliang.backtracing;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 解决八皇后问题
 *
 * @author magicliang
 * @version $Id: EightQueensProblem.java, v 0.1 2018年11月14日 20:12 magicliang Exp $
 */
@Slf4j
public class EightQueensProblem {

    private List<int[]> queens;

    private int[][] matrix;

    @Setter
    private int num;

    public static EightQueensProblem buildQueens(int num) {
        // 实际上只有三皇后以上才有解
        if (num <= 0) {
            throw new IllegalArgumentException("num can not be negative");
        }

        //
        EightQueensProblem eightQueensProblem = new EightQueensProblem();
        eightQueensProblem.setNum(num);

        return eightQueensProblem;
    }

    /**
     * 根据特殊的矩阵来求解
     */
    public List<int[]> getQueens() {
        matrix = new int[num][num];
        // 如何预测八皇后问题的解规模？
        queens = new ArrayList<>(num);

        // 从第 0 行开始解决子问题
        getQueenReal(0);
        printQueens();
        return queens;
    }

    private void printQueens() {

        for (int[] queenSolution : queens) {
            log.info("打印一种皇后方案：");
            int length = queenSolution.length;
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

    /**
     * 解决每行的自问题
     *
     * @param row 行号
     */
    private void getQueenReal(int row) {

        if (row == num) {
            // 行号等于矩阵长度，证明此时矩阵已经被推演到底。对矩阵当前的状态进行存储
            snapshotMatrix();
            return;
        }

        // 穷举遍本行内所有的列
        for (int column = 0; column < num; column++) {
            matrix[row][column] = 1;
            // 假设这一列是正常
            if (noConflict(row, column)) {
                // 可以试着解决下一行的下一个子问题
                getQueenReal(row + 1);
            }
            // 无论如何，恢复这一矩阵这一位置
            matrix[row][column] = 0;
        }
    }

    private void snapshotMatrix() {
        log.info("发现一种解法");
        int[] snapshot = new int[num];
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (matrix[i][j] == 1) {
                    // 第 i 列此时的皇后应该在 i 位置
                    snapshot[j] = i;
                }
            }
        }
        queens.add(snapshot);
    }

    private boolean noConflict(int row, int column) {

        // 同行或者同列有其他皇后
        for (int i = 0; i < num; i++) {
            boolean sameRowConflict = 1 == matrix[row][i] && i != column;
            boolean sameColumnConflict = 1 == matrix[i][column] && row != i;
            if (sameRowConflict || sameColumnConflict) {
                return false;
            }
        }

        if (row - 1 >= 0) {
            // 如果左上角有其他皇后，返回失败
            if (column - 1 >= 0 && matrix[row - 1][column - 1] == 1) {
                return false;
            }
            // 如果右上角有其他皇后，返回失败
            return column + 1 > num - 1 || matrix[row - 1][column + 1] != 1;
        }

        // 这里不验证下方的数据，是因为目前只有上方有了问题的解
        // 返回成功
        return true;
    }
}