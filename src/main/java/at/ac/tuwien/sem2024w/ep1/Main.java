package at.ac.tuwien.sem2024w.ep1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] test1 = {{5}, {5, 7, 9}, {8, 5}, {}};
        int[][] test2 = {{1, 2}, {1, 2, 3}, {1, 2, 3, 4}};
        int[][] test3 = {{}, {1, 2, 3, 4}, {1}};
        int[] seq = {1, 2, -5, 3, -1, 6, -3, 3};

        int[][] result1 = getRectangular(test1);
        System.out.println(Arrays.deepToString(result1));
        assert Arrays.deepToString(result1).equals("[[5, 5, 5], [5, 7, 9], [8, 5, 8], [0, 0, 0]]");

        int[][] result2 = getRectangular(test2);
        System.out.println(Arrays.deepToString(result2));
        assert Arrays.deepToString(result2).equals("[[1, 2, 1, 2], [1, 2, 3, 1], [1, 2, 3, 4]]");

        int[][] result3 = getRectangular(test3);
        System.out.println(Arrays.deepToString(result3));
        assert Arrays.deepToString(result3).equals("[[0, 0, 0, 0], [1, 2, 3, 4], [1, 1, 1, 1]]");

        int[][] result4 = getRectangular(new int[][]{{}});
        System.out.println(Arrays.deepToString(result4));
        assert Arrays.deepToString(result4).equals("[[]]");

        removeEntry(test2, 2);
        System.out.println(Arrays.deepToString(test2));
        assert Arrays.deepToString(test2).equals("[[1, 2], [1, 2], [1, 2, 4]]");

        removeEntry(test3, 0);
        System.out.println(Arrays.deepToString(test3));
        assert Arrays.deepToString(test3).equals("[[], [2, 3, 4], []]");

        assert !isAlternating(seq, 0);
        assert isAlternating(seq, 1);
        assert isAlternating(seq, 6);
        assert isAlternating(seq, 7);

        for (int i = 1; i < seq.length; i++) {
            assert isAlternating(seq, i);
        }
    }

    public static int[][] getRectangular(int[][] input) {
        int maxLength = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i].length > maxLength) {
                maxLength = input[i].length;
            }
        }

        int[][] result = new int[input.length][];

        for (int i = 0; i < input.length; i++) {
            int[] newRow = new int[maxLength];

            int[] row;
            if (input[i].length == 0) {
                row = new int[]{0};
            } else {
                row = input[i];
            }

            for (int j = 0; j < maxLength; j++) {
                newRow[j] = row[j % row.length];
            }

            result[i] = newRow;
        }

        return result;
    }

    public static void removeEntry(int[][] input, int col) {
        for (int i = 0; i < input.length; i++) {
            if (col < input[i].length) {
                int[] row = new int[input[i].length - 1];
                int offset = 0;

                for (int j = 0; j < row.length; j++) {
                    if (j == col) {
                        offset = 1;
                    }
                    row[j] = input[i][j + offset];
                }
                input[i] = row;
            }
        }
    }

    public static boolean isAlternating(int[] seq, int index) {
        if (index + 1 >= seq.length) {
            return true;
        }

        boolean isPos = seq[index] > 0;
        boolean nextIsPos = seq[index + 1] > 0;

        return isPos == !nextIsPos && isAlternating(seq, index + 1);
    }
}