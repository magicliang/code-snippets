package com.magicliang.dp;

import java.io.Serializable;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 解决矩阵链相乘问题
 *
 * @author magicliang
 * @version $Id: MatrixChainOrder.java, v 0.1 2018年11月10日 21:35 magicliang Exp $
 */
@Slf4j
@Data
public class MatrixChainOrder implements Serializable {

    private static final long serialVersionUID = 6994787893092877314L;

    /**
     * 它的行长度始终是要长于矩阵的数量，恰好等于矩阵的行列数组的长度
     */
    private int[][] minCost;
    /**
     * 它的行长度始终是要长于矩阵的数量，恰好等于矩阵的行列数组的长度
     */
    private int[][] kPosition;

    public void printOptimalOrder(int begin, int end) {
        if (null == minCost || null == kPosition) {
            throw new IllegalArgumentException("unable to print, minCost or kPosition is null");
        }
        int matrixCount = minCost.length - 1;
        if (end > matrixCount) {
            throw new IllegalArgumentException("");
        }

        /**
         * 自顶向下分解
         */

        /**
         * 如果已经到达了链长度的最小值，把当前的矩阵打出来
         */
        if (begin == end) {
            print("A" + begin);

        } else {
            /**
             * 否则
             * 1 先把当前的链框起来
             */
            print("(");

            /**
             *  2 求出分割点
             */
            int k = kPosition[begin][end];

            /**
             * 3 让分割点自己去框自己的链
             */
            printOptimalOrder(begin, k);
            printOptimalOrder(k + 1, end);

            /**
             * 4 先把当前的链框起来
             */
            print(")");
        }
    }

    private void print(String s) {
        log.info(s);
    }

    /**
     * 根据一系列矩阵的行列点数组，输出矩阵的最优排序
     */
    public static MatrixChainOrder optimalOrderBottomUp(int[] pArr) {

        int pointCount = pArr.length;

        if (0 == pointCount) {
            return null;
        }

        int matrixCount = pointCount - 1;
        MatrixChainOrder result = new MatrixChainOrder();

        /**
         * 初始化最优解的值的备忘录
         * 矩阵点的数量比矩阵的数量多1，这样就可以实现 1 based 的备忘录
         */
        int[][] minCost = new int[pointCount][pointCount];
        result.setMinCost(minCost);

        /**
         * 初始化最优解的分割法的备忘录
         */
        int[][] kPosition = new int[pointCount][pointCount];
        result.setKPosition(kPosition);

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
                        kPosition[i][j] = k;
                    }
                }
            }
        }

        return result;
    }

    public static MatrixChainOrder optimalOrderTopDown(int[] pArr) {
        int pointCount = pArr.length;

        if (0 == pointCount) {
            return null;
        }

        MatrixChainOrder result = new MatrixChainOrder();

        /**
         * 矩阵点的数量比矩阵的数量多1，这样就可以实现 1 based的矩阵
         */
        int[][] minCost = new int[pointCount][pointCount];
        result.setMinCost(minCost);
        int[][] kPosition = new int[pointCount][pointCount];
        result.setKPosition(kPosition);

        int matrixCount = pointCount - 1;

        // 如果是自顶向下来递归解这个问题，在解决重叠子问题（overlapping subproblem）以前，要保证重叠子问题被事先初始化好了
        // 使用这种 1-based 的矩阵，index 的起点要特别特别小心
        for (int i = 1; i <= matrixCount; i++) {
            for (int j = i; j <= matrixCount; j++) {
                if (i == j) {
                    // 解决长度为0的矩阵链问题，为更高级问题做准备
                    minCost[i][i] = 0;
                } else {
                    minCost[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        result = optimalOrderTopDownReal(pArr, result, 1, matrixCount);

        return result;
    }

    private static MatrixChainOrder optimalOrderTopDownReal(int[] pArr, MatrixChainOrder result, int beginMatrix,
                                                            int endMatrix) {
        int pointCount = pArr.length;
        int matrixCount = pointCount - 1;

        int[][] minCost = result.getMinCost();
        int[][] kPosition = result.getKPosition();

        if (beginMatrix == endMatrix) {
            return result;
        }

        for (int k = beginMatrix; k < endMatrix; k++) {
            int temp = optimalOrderTopDownReal(pArr, result, beginMatrix, k).getMinCost()[beginMatrix][k]
                + optimalOrderTopDownReal(pArr, result, k + 1, endMatrix).getMinCost()[k + 1][endMatrix]
                + pArr[beginMatrix - 1] * pArr[k] * pArr[endMatrix];
            if (temp < minCost[beginMatrix][endMatrix]) {
                minCost[beginMatrix][endMatrix] = temp;
                kPosition[beginMatrix][endMatrix] = k;
            }
        }
        return result;
    }

}