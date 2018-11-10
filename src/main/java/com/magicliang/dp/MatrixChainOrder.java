package com.magicliang.dp;

import java.io.Serializable;

/**
 * 解决矩阵链相乘问题
 *
 * @author magicliang
 * @version $Id: MatrixChainOrder.java, v 0.1 2018年11月10日 21:35 magicliang Exp $
 */
public class MatrixChainOrder implements Serializable {

    private static final long serialVersionUID = 6994787893092877314L;

    private int[][] minCost;
    private int[][] kPosition;

    /**
     * Get min cost int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getMinCost() {
        return minCost;
    }

    /**
     * Sets set min cost.
     *
     * @param minCost the min cost
     */
    public void setMinCost(int[][] minCost) {
        this.minCost = minCost;
    }

    /**
     * Get kPosition int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getkPosition() {
        return kPosition;
    }

    /**
     * Sets set kPosition.
     *
     * @param kPosition the kPosition
     */
    public void setkPosition(int[][] kPosition) {
        this.kPosition = kPosition;
    }

    /**
     *
     */
    public static MatrixChainOrder matrixChainOrder(int[] pArr) {

        int pCount = pArr.length;

        if (0 == pCount) {
            return null;
        }

        int matrixCount = pCount - 1;
        MatrixChainOrder result = new MatrixChainOrder();

        /**
         * 矩阵点的数量比矩阵的数量多1，这样就可以实现 1 based的矩阵
         */
        int[][] minCost = getMatrixMemo(pCount);
        result.setMinCost(minCost);
        int[][] kPosition = getMatrixMemo(pCount);
        result.setkPosition(kPosition);

        // 解决长度为0的矩阵链问题，为更高级问题做准备
        for (int i = 0; i <= matrixCount; i++) {
            minCost[i][i] = 0;
        }

        // 首先定义解的长度范畴，从长度2开始计算，一直到长度 matrixCount
        for (int chainLength = 2; chainLength <= matrixCount; chainLength++) {
            // 定义链的起点
            for (int i = 1; i <= matrixCount - chainLength + 1; i++) {
                // 定义链的终点
                int j = i + chainLength - 1;
                minCost[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int q = minCost[i][k] + minCost[k + 1][j] + pArr[i - 1] * pArr[k] * pArr[j];
                    if (q < minCost[i][j]) {
                        minCost[i][j] = q;
                    }
                }
            }
        }

        return result;
    }

    private static int[][] getMatrixMemo(int pCount) {
        int[][] memo = new int[pCount][];
        for (int i = 0; i < pCount; i++) {
            int[] row = new int[pCount];
            memo[i] = row;
        }
        return memo;
    }
}