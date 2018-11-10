package com.magicliang.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * �� dp ����쳲���������
 *
 * @author magicliang
 * @version $Id: FibonacciProblems.java, v 0.1 2018��11��04�� 21:33 magicliang Exp $
 */
public class FibonacciProblems {
    private static final ThreadLocal<List<Integer>> tlFib =
            ThreadLocal.withInitial(() -> new ArrayList<>(0));

    /**
     * ��ͨ�ݹ��
     *
     * @param scale �����ģ
     * @return ���е� n λ ֵ
     */
    public static int classicalSolution(int scale) {
        if (scale == 1 || scale == 2) {
            return 1;
        }

        return classicalSolution(scale - 1) + classicalSolution(scale - 2);
    }

    /**
     * �����Զ����µĶ�̬�滮�Ľ�
     *
     * @param scale �����ģ
     * @return ���е� n λ ֵ
     */
    public static int topDownSolution(int scale) {

        // һ�ֲ�ʹ�� ThreadLocal�ķ���������һ������������ʼ�����飬������ʵ���õĺ������سǷ�յ����顣
        int[] fib = new int[scale];

        for (int i = 0; i < scale; i++) {
            fib[i] = Integer.MIN_VALUE;
        }

        return topDownSolutionReal(scale, fib);
    }

    private static int topDownSolutionReal(int scale, int[] fib) {
        int result;
        int fibIndex = scale - 1;

        if (scale == 1 || scale == 2) {
            result = 1;
            fib[fibIndex] = result;
            // System.out.println("�����" + scale + "�������" + result);
            return result;
        }

        // scale - 1 solution
        int subProblemScale1 = scale - 1;
        int fibIndex1 = subProblemScale1 - 1;
        int subResult1 = fib[fibIndex1];
        if (subResult1 == Integer.MIN_VALUE) {
            subResult1 = topDownSolutionReal(subProblemScale1, fib);
            fib[fibIndex1] = subResult1;
        }

        // scale - 2 solution
        int subProblemScale2 = scale - 2;
        int fibIndex2 = subProblemScale2 - 1;
        int subResult2 = fib[fibIndex2];
        if (subResult2 == Integer.MIN_VALUE) {
            subResult2 = topDownSolutionReal(subProblemScale2, fib);
            fib[fibIndex2] = subResult2;
        }

        result = subResult1 + subResult2;
        // System.out.println("�����" + scale + "�������" + result);
        return result;
    }

    public static int topDownSolutionWithThreadLocal(int scale) {

        // һ�ֲ�ʹ�� ThreadLocal�ķ���������һ������������ʼ�����飬������ʵ���õĺ������سǷ�յ����顣
        List<Integer> fib = tlFib.get();
        if (fib.isEmpty()) {
            for (int i = 0; i < scale; i++) {
                fib.add(i, Integer.MIN_VALUE);
            }
        }

        int result;
        int fibIndex = scale - 1;

        if (scale == 1 || scale == 2) {
            result = 1;
            fib.set(fibIndex, result);
            System.out.println("�����" + scale + "�������" + result);
            return result;
        }

        // scale - 1 solution
        int subProblemScale1 = scale - 1;
        int fibIndex1 = subProblemScale1 - 1;
        int subResult1 = fib.get(fibIndex1);
        if (subResult1 == Integer.MIN_VALUE) {
            subResult1 = topDownSolutionWithThreadLocal(subProblemScale1);
            fib.set(fibIndex1, subResult1);
        }

        // scale - 2 solution
        int subProblemScale2 = scale - 2;
        int fibIndex2 = subProblemScale2 - 1;
        int subResult2 = fib.get(fibIndex2);
        if (subResult2 == Integer.MIN_VALUE) {
            subResult2 = topDownSolutionWithThreadLocal(subProblemScale2);
            fib.set(fibIndex2, subResult2);
        }

        result = subResult1 + subResult2;
        System.out.println("�����" + scale + "�������" + result);

        // TODO: ʲôʱ��clear threadLocal
        return result;
    }

}