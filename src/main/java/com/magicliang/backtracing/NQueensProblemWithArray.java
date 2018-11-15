/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.magicliang.backtracing;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用一个数组来解决八皇后问题
 *
 * @author magicliang
 * @version $Id: NQueensProblemWithArray.java, v 0.1 2018年11月15日 19:39 magicliang Exp $
 */
@Slf4j
public class NQueensProblemWithArray extends NQueensProblem {

    private int[] nQueens;

    /**
     * 根据输入参数，解决 n 皇后问题
     *
     * @param num 皇后数量
     * @return 解
     */
    public static NQueensProblemWithArray buildQueens(int num) {

        // 实际上只有四皇后以上才有解
        if (num <= 0) {
            throw new IllegalArgumentException("num can not be negative");
        }

        NQueensProblemWithArray nQueensProblemWithArray = new NQueensProblemWithArray();
        nQueensProblemWithArray.setNum(num);

        return nQueensProblemWithArray;
    }

    public List<int[]> getSolutions() {
        buildQueensReal(0);
        printQueens();
        return solutions;
    }

    private void buildQueensReal(int row) {

        if (nQueens == null) {
            nQueens = new int[num];
        }

        if (solutions == null) {
            solutions = new ArrayList<>(num);
        }

        if (row == num) {
            // 复制当前数组进解里面
            solutions.add(nQueens.clone());
            return;
        }

        // 对本行进行迭代
        for (int column = 0; column < num; column++) {
            // 将要在 row 行放置 column 列，如果没有冲突
            if (noConflict(row, column)) {
                // 在本行中设置一个成功的列
                nQueens[row] = column;
                // 开始 DFS
                buildQueensReal(row + 1);
                // 把数组还原，为下次 DFS 做准备
                nQueens[row] = 0;
            }
        }

    }

    // 现存矩阵是不是有潜在的冲突点
    private boolean noConflict(int row, int column) {
        // 只遍历已经被放置的行
        for (int existRow = 0; existRow < row; existRow++) {
            int existColumn = nQueens[existRow];
            // 存在同行的皇后
            if (row == existRow
                // 存在同列的皇后
                || existColumn == column) {
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

}