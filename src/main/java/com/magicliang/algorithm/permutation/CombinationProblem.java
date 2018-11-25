package com.magicliang.algorithm.permutation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ����ַ����������
 *
 * @author LC
 * @version $Id: CombinationProblem.java, v 0.1 2018��11��12�� 23:42 LC Exp $
 */
public class CombinationProblem {

    public static List<String> combination(List<String> args, int combinationSize) {
        // �ж��ٸ����������ж��ٸ��ط�Ҫ�������Ա�̼��
        if (null == args || 0 == args.size()) {
            return Collections.EMPTY_LIST;
        }

        int length = args.size();
        if (combinationSize > length) {
            throw new IllegalArgumentException("combinationSize can not be greater than args size.");
        }

        // �ж��ٸ����������ж��ٸ��ط�Ҫ��ǰ��ֹ
        // ��������ߵ���ͷ�������������ߵ���ͷ
        if (1 == length || combinationSize == 1) {
            return args;
        }

        List<String> result = new ArrayList<>(combinationSize);
        String head = args.get(0);
        // �����ʵ���޷ŻصĹ��̣�����ǧ��Ҫ������ for ѭ������һ������������и�Ǳ�Ȼ�����ص������⡣
        // ������Ϸ�ʽ��Ϊ���ص������⣬����ʵ�������޷��� dp ����ġ�
        List<String> combination = combination(args.subList(1, length), combinationSize - 1);
        for (String subStr : combination) {
            result.add(head + subStr);
        }
        if (length - 1 >= combinationSize) {
            List<String> otherCombination = combination(args.subList(1, length), combinationSize);
            result.addAll(otherCombination);
        }

        return result;
    }
}