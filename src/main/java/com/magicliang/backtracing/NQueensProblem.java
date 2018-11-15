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
}