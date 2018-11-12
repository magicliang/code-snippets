package com.magicliang.dp;

import lombok.extern.slf4j.Slf4j;

/**
 * 切割钢条问题 动态规划仔细安排求解顺序，对每个子问题只求解一次，并将结果保存下来，也就是 CLRS 中所说的表方法。 这是典型的 time-memory trade-off
 * 的例子。可以把一个指数(exponential)时间的解，转化为一个多项式（polynomial）时间的解。
 * @author magicliang
 */
@Slf4j
public class CutRod {

    /**
     * 自顶向下的备忘录方法。 此方法按照自然的递归形式编写过程。但过程会保存每个子问题的解
     *
     * @param price 每种切割方式的价格数组
     * @param n     要切割的钢条的总长度
     * @return 最大收益值是多少
     */
    public static int topDownCutRodWithMemoized(int[] price, int n) {

        parameterCheck(price, n);

        int[] revenue = getRevenues(n);

        return topDownCutRodWithMemoizedReal(price, n, revenue);
    }

    private static void parameterCheck(int[] price, int n) {
        if (null == price || price.length == 0 || n < 0) {
            throw new IllegalArgumentException(String.format("illegal argument: price %s, n %s", price, n));
        }
    }

    private static int topDownCutRodWithMemoizedReal(int[] price, int n, int[] revenue) {
        if (n == 0) {
            return 0;
        }

        int temp = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            temp = max(temp, price[i] + topDownCutRodWithMemoized(price, n - i));
        }
        revenue[n] = temp;
        return temp;
    }

    /**
     * 自底向上的备忘录方法。 此方法一般要恰当定义子问题的规模的概念。使得任何子问题的求解都只依赖于“更小的”子问题的求解。 因而我们可以将子问题按照规模排序，按由小至大的顺序进行求解。当求解某个子问题时，它所依赖的那些
     * 更小的子问题都已求解完毕。
     *
     * @param price 每种切割方式的价格数组
     * @param n     要切割的钢条的总长度
     * @return 最大收益值是多少
     */
    public static int bottomUpCutRodWithMemoized(int[] price, int n) {

        parameterCheck(price, n);

        int[] revenue = getRevenues(n);

        return bottomUpCutRodWithMemoizedReal(price, n, revenue);
    }

    private static int bottomUpCutRodWithMemoizedReal(int[] price, int n, int[] revenue) {
        if (n == 0) {
            return 0;
        }

        for (int i = 1; i <= n; i++) {
            int temp = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                temp = max(temp, price[j] + revenue[i - j]);
            }
            revenue[i] = temp;
        }
        return revenue[n];
    }

    private static int max(int a, int b) {
        return a > b ? a : b;
    }

    private static int[] getRevenues(int n) {
        int revenueArrayLength = n + 1;

        int[] revenue = new int[revenueArrayLength];

        for (int i = 0; i < revenueArrayLength; i++) {
            revenue[i] = Integer.MIN_VALUE;
        }
        revenue[0] = 0;
        return revenue;
    }
}
