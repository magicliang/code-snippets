/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.magicliang.algorithm.backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * 完全依靠堆栈和迭代进行求解的 n 皇后问题 本算法未完成
 *
 * @author magicliang
 * @version $Id: NQueensProblemWithIteration.java, v 0.1 2018年11月15日 20:31 magicliang Exp $
 */
public class NQueensProblemWithIteration extends NQueensProblem {

    public static List<int[]> buildQueens(int num) {

        checkQueenNum(num);

        List<int[]> solutions = new ArrayList<>(num);
        int[] solution = new int[num];

        initSolution(solution);

        // 开始推动行
        int row = 0;
        int column = 0;

        // 逐渐推进行
        outer:
        while (row < num) {
            inner:
            while (column < num) {
                // 如果本搜索可以下进行下去
                if (noConflict(solution, row, column)) {
                    solution[row] = column;

                    // 如果这一行没有冲突，直接走入下一行。
                    row++;
                    // 下一行也是从0开始
                    column = 0;

                    // 如果下一行到达棋盘之外
                    if (row == num) {
                        solutions.add(solution.clone());
                        // 重做解
                        initSolution(solution);
                    }

                    continue outer;
                } else {
                    // 否则，尝试回溯上一行，从上一行的下一列开始
                    row--;
                    column = solution[row] + 1;
                }
            }

        }

        return solutions;
    }

}
