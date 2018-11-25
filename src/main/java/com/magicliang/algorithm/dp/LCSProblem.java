package com.magicliang.algorithm.dp;

import lombok.Data;

/**
 * @author magicliang
 * @version $Id: LCSProblem.java, v 0.1 2018年11月14日 16:42 magicliang Exp $
 */
@Data
public class LCSProblem {

    private int[][] lcsMatrix;
    private String[][] lcsSolutionMatrix;

    private String[] sequenceA;
    private String[] sequenceB;

    /**
     * 输入两个队列，返回 lcs
     *
     * @param sequenceA 序列1
     * @param sequenceB 序列2
     * @return lcs 解答
     */
    public static LCSProblem dpLCSProblem(String[] sequenceA, String[] sequenceB) {

        if (null == sequenceA || null == sequenceB) {
            throw new IllegalArgumentException("sequenceA or sequenceB can not be null");
        }

        int lengthA = sequenceA.length;
        int lengthB = sequenceB.length;

        int matrixRowCount = lengthA + 1;
        int matrixColumnCount = lengthB + 1;

        LCSProblem lcs = new LCSProblem();

        // 初始化结果矩阵
        int[][] lcsMatrix = new int[matrixRowCount][matrixColumnCount];
        // 直接被初始化的二维数组的值总是缺省值，也就是 null
        String[][] lcsSolutionMatrix = new String[matrixRowCount][matrixColumnCount];

        lcs.setSequenceA(sequenceA);
        lcs.setSequenceB(sequenceB);
        lcs.setLcsMatrix(lcsMatrix);
        lcs.setLcsSolutionMatrix(lcsSolutionMatrix);

        // 解决 i == 0 或者 j == 0 的子问题
        for (int i = 0; i < matrixRowCount; i++) {
            // 初始化矩阵中，子问题行为0。
            lcsMatrix[i][0] = 0;
        }
        for (int j = 0; j < matrixColumnCount; j++) {
            // 初始化矩阵中，子问题列为0。
            lcsMatrix[0][j] = 0;
        }

        // 从行开始
        for (int i = 1; i <= lengthA; i++) {
            // 从列开始
            for (int j = 1; j <= lengthB; j++) {
                int sequenceAIndex = i - 1;
                int sequenceBIndex = j - 1;
                if (sequenceA[sequenceAIndex] == sequenceB[sequenceBIndex]) {
                    lcsMatrix[i][j] = 1 + lcsMatrix[i - 1][j - 1];
                    lcsSolutionMatrix[i][j] = "斜上";
                } else if (lcsMatrix[i - 1][j] >= lcsMatrix[i][j - 1]) {
                    lcsMatrix[i][j] = lcsMatrix[i - 1][j];
                    lcsSolutionMatrix[i][j] = "上";
                } else {
                    lcsMatrix[i][j] = lcsMatrix[i][j - 1];
                    lcsSolutionMatrix[i][j] = "左";
                }
            }
        }

        return lcs;
    }

    public String getLCSStr() {
        int rowCount = lcsSolutionMatrix.length;
        int columnCount = lcsSolutionMatrix[0].length;

        int i = rowCount - 1;
        int j = columnCount - 1;

        StringBuilder result = new StringBuilder(i > j ? i : j);
        while (i > 0 && j > 0) {
            if ("斜上".equals(lcsSolutionMatrix[i][j])) {
                // sequence 的底比矩阵的底少1，因为矩阵多了一个0的底。
                // 如矩阵中的7，对应的是sequence 中的6
                result.append(sequenceA[i - 1]);
                i--;
                j--;
            } else if ("上".equals(lcsSolutionMatrix[i][j])) {
                i--;
            } else {
                j--;
            }

        }
        return result.reverse().toString();
    }

}