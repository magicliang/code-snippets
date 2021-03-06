package com.magicliang.algorithm.backtracing;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 用矩阵法解决八皇后问题
 *
 * @author magicliang
 * @version $Id: NQueensProblemWithMatrix.java, v 0.1 2018年11月14日 20:12 magicliang Exp $
 */
@Slf4j
@NoArgsConstructor
public class NQueensProblemWithMatrix extends NQueensProblem {

    private int[][] matrix;

    /**
     * 根据输入参数，解决 n 皇后问题
     *
     * @param num 皇后数量
     * @return 解
     */
    public static NQueensProblemWithMatrix buildQueens(int num) {
        checkQueenNum(num);

        NQueensProblemWithMatrix nQueensProblemWithMatrix = new NQueensProblemWithMatrix();
        nQueensProblemWithMatrix.setNum(num);

        return nQueensProblemWithMatrix;
    }

    /**
     * 根据特殊的矩阵来求解
     */
    public List<int[]> getSolutions() {
        matrix = new int[num][num];
        // 如何预测八皇后问题的解规模？
        solutions = new ArrayList<>(num);

        // 从第 0 行开始解决子问题
        getQueenReal(0);
        printQueens();
        return solutions;
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
                // 可以试着解决下一行的下一个子问题。回溯法的本质，就是 DFS。
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
        solutions.add(snapshot);
    }

    private boolean noConflict(int row, int column) {

        // 从上往下扫行，直至当前行
        for (int i = 0; i <= row; i++) {
            // 从左到右扫列，直至尽头
            for (int j = 0; j < num; j++) {

                // 因为这是一个 dfs，所以并不存在同一行的其他元素，这一个判断步骤可以省略掉
                //// 同行不同列冲突
                //if (matrix[row][j] == 1) {
                //    return false;
                //}
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

}