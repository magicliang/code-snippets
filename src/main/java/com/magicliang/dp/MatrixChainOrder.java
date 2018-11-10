package com.magicliang.dp;

import java.io.Serializable;

/**
 * ����������������
 *
 * @author magicliang
 * @version $Id: MatrixChainOrder.java, v 0.1 2018��11��10�� 21:35 magicliang Exp $
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
         * �����������Ⱦ����������1�������Ϳ���ʵ�� 1 based�ľ���
         */
        int[][] minCost = getMatrixMemo(pCount);
        result.setMinCost(minCost);
        int[][] kPosition = getMatrixMemo(pCount);
        result.setkPosition(kPosition);

        // �������Ϊ0�ľ��������⣬Ϊ���߼�������׼��
        for (int i = 0; i <= matrixCount; i++) {
            minCost[i][i] = 0;
        }

        // ���ȶ����ĳ��ȷ��룬�ӳ���2��ʼ���㣬һֱ������ matrixCount
        for (int chainLength = 2; chainLength <= matrixCount; chainLength++) {
            // �����������
            for (int i = 1; i <= matrixCount - chainLength + 1; i++) {
                // ���������յ�
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