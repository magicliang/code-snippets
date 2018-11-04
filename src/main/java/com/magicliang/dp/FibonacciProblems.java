/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.magicliang.dp;

/**
 * �� dp ����쳲���������
 * @author magicliang
 * @version $Id: FibonacciProblems.java, v 0.1 2018��11��04�� 21:33 magicliang Exp $
 */
public class FibonacciProblems {

    public static int[] fib = new int[100];


    /**
     * ��ͨ�ݹ��
     * @param scale �����ģ
     * @return ���е� n λ ֵ
     */
    public static int classicalSolution(int scale){
        if(scale == 1 || scale == 2) {
            return 1;
        }

        return classicalSolution(scale - 1) + classicalSolution(scale - 2);
    }

    /**
     * �����Զ����µĶ�̬�滮�Ľ�
     * @param scale �����ģ
     * @return ���е� n λ ֵ
     */
    public static int topDownSolution(int scale){
        int result;


        for (int i = 0; i < scale; i++) {
            fib[i] = Integer.MIN_VALUE;
        }

        int subProblemScale1 = scale - 1;
        if(scale == 1 || scale == 2) {
            result = 1;
            fib[subProblemScale1] = result;
            return result;
        }

        // scale -1 solution
        int subResult1 = fib[scale - 2];
        if ( subResult1 == Integer.MIN_VALUE) {
            subResult1 = classicalSolution(subProblemScale1);
            fib[scale - 2] = subResult1;
            System.out.print("�����" + subProblemScale1 + "�������" + subResult1);
        }

        int subProblemScale2 = scale - 3;
        int subResult2 = fib[subProblemScale2];
        if ( subResult2 == Integer.MIN_VALUE) {
            subResult2 = classicalSolution(scale - 2);
            fib[subProblemScale2] = subResult2;
            System.out.print("�����" + subProblemScale2 + "�������" + subResult2);
        }

        return subResult1 +  subResult2;
    }
}