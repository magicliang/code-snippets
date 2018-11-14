package com.magicliang.util;

import lombok.extern.slf4j.Slf4j;

/**
 * ������ع���
 *
 * @author magicliang
 * @version $Id: MatrixUtil.java, v 0.1 2018��11��14�� 17:49 magicliang Exp $
 */
@Slf4j
public class MatrixUtil {
    public static void printStringMatrix(String[][] matrix) {
        if (null == matrix) {
            throw new IllegalArgumentException("�����鲻�ܴ�ӡ");
        }
        int rowCount = matrix.length;
        for (int i = 0; i < rowCount; i++) {
            String[] row = matrix[i];
            if (null == row) {
                int columnCount = row.length;
                StringBuilder result = new StringBuilder(columnCount);
                for (int j = 0; j < columnCount; j++) {
                    if (row[j] == null) {
                        result.append(" ");
                    } else {
                        result.append(row[j]);
                    }
                }
                log.info(result.toString());
            }

        }

    }
}