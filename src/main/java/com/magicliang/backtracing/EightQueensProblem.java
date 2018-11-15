package com.magicliang.backtracing;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 解决八皇后问题
 *
 * @author magicliang
 * @version $Id: EightQueensProblem.java, v 0.1 2018年11月14日 20:12 magicliang Exp $
 */
@Slf4j
@NoArgsConstructor
public class EightQueensProblem {

    private List<int[]> queens;

    private int[][] matrix;

    @Setter
    private int num;

    public static EightQueensProblem buildQueens(int num) {
        // 实际上只有四皇后以上才有解
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

    /**
     * 解决每行的自问题
     *
     * @param row 行号
     */
    private void getQueenReal(int row) {

        // 行号等于矩阵长度，证明此时矩阵已经被推演到底。对矩阵当前的状态进行存储
        if (row == num) {
            takeSnapshot();
            return;
        }

        // 穷举遍本行内所有的列
        for (int column = 0; column < num; column++) {

            // 假设这一个格子是正常的
            if (noConflict(row, column)) {
                matrix[row][column] = 1;
                // 可以试着解决下一行的下一个子问题
                getQueenReal(row + 1);
                // 无论如何，恢复这一矩阵这一位置。回溯也就是在这一步。
                matrix[row][column] = 0;
            }

        }
    }

    private void takeSnapshot() {
        int[] snapshot = new int[num];
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (matrix[i][j] == 1) {
                    // 第 i 行此时的皇后应该在第 j 列
                    snapshot[i] = j;
                }
            }
        }
        queens.add(snapshot);
    }

    private boolean noConflict(int row, int column) {

        // 只做一个嵌套向上查询，看看有没有纵横相关联的点
        for (int i = row; i >= 0; i--) {
            for (int j = 0; j < num; j++) {

                // 同行不同列冲突
                if (matrix[row][j] == 1) {
                    return false;
                }
                // 同列不同行冲突
                if (matrix[i][column] == 1) {
                    return false;
                }
                int rowDelta = row - i;
                int columnDelta = column - j;

                if (columnDelta < 0) {
                    columnDelta = columnDelta * -1;
                }
                // 在同一个斜线上
                if (rowDelta == columnDelta && matrix[i][j] == 1) {
                    return false;
                }
            }

        }

        // 这里不验证下方的数据，是因为目前只有上方有了问题的解
        // 返回成功
        return true;
    }

    private void printQueens() {
        for (int[] queenSolution : queens) {
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

}