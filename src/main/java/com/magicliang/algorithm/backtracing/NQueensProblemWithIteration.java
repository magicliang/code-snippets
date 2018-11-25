/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.magicliang.algorithm.backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ȫ������ջ�͵����������� n �ʺ����� ���㷨δ���
 *
 * @author magicliang
 * @version $Id: NQueensProblemWithIteration.java, v 0.1 2018��11��15�� 20:31 magicliang Exp $
 */
public class NQueensProblemWithIteration extends NQueensProblem {

    public static List<int[]> buildQueens(int num) {

        checkQueenNum(num);

        List<int[]> solutions = new ArrayList<>(num);
        int[] solution = new int[num];

        initSolution(solution);

        // ��ʼ�ƶ���
        int row = 0;
        int column = 0;

        // ���ƽ���
        outer:
        while (row < num) {
            inner:
            while (column < num) {
                // ��������������½�����ȥ
                if (noConflict(solution, row, column)) {
                    solution[row] = column;

                    // �����һ��û�г�ͻ��ֱ��������һ�С�
                    row++;
                    // ��һ��Ҳ�Ǵ�0��ʼ
                    column = 0;

                    // �����һ�е�������֮��
                    if (row == num) {
                        solutions.add(solution.clone());
                        // ������
                        initSolution(solution);
                    }

                    continue outer;
                } else {
                    // ���򣬳��Ի�����һ�У�����һ�е���һ�п�ʼ
                    row--;
                    column = solution[row] + 1;
                }
            }

        }

        return solutions;
    }

}
