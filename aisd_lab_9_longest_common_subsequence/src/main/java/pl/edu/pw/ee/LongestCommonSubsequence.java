package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class LongestCommonSubsequence {
    private int[][] matrix;
    private char[][] arrows;
    private final char[] firstStr;
    private final char[] secondStr;
    private final int indexI;
    private final int indexJ;

    public LongestCommonSubsequence(String topStr, String leftStr) {
        if (topStr == null || leftStr == null)
            throw new IllegalArgumentException("Input words cannot be null");
        this.indexI = leftStr.length();
        this.indexJ = topStr.length();
        this.firstStr = leftStr.toCharArray();
        this.secondStr = topStr.toCharArray();
        matrix = new int[leftStr.length() + 1][topStr.length() + 1];
        arrows = new char[leftStr.length() + 1][topStr.length() + 1];
        initializeMatrix();
    }

    private void initializeMatrix() {
        for (int i = 0; i <= indexI; i++) {
            for (int j = 0; j <= indexJ; j++) {
                if (i == 0 || j == 0) {
                    matrix[i][j] = 0;
                    arrows[i][j] = '0';
                } else if (firstStr[i - 1] == secondStr[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                    arrows[i][j] = '\\';
                } else if (matrix[i][j - 1] > matrix[i - 1][j]) {
                    matrix[i][j] = matrix[i][j - 1];
                    arrows[i][j] = '<';
                } else {
                    matrix[i][j] = matrix[i - 1][j];
                    arrows[i][j] = '^';
                }
            }
        }

    }

    public String findLCS() {
        List<Integer> significantIndexesI = new ArrayList<>();
        List<Integer> significantIndexesJ = new ArrayList<>();
        checkIndex(indexI, indexJ, matrix[indexI][indexJ], significantIndexesI, significantIndexesJ);
        if (significantIndexesI.size() != significantIndexesJ.size())
            throw new IllegalStateException("These ArrayLists have to be the same size.");
        Collections.reverse(significantIndexesI);
        Collections.reverse(significantIndexesJ);
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < significantIndexesI.size(); i++) {
            if (arrows[significantIndexesI.get(i)][significantIndexesJ.get(i)] == '\\') {
                string.append(firstStr[significantIndexesI.get(i) - 1]);
            }
        }
        return string.toString();
    }

    private void checkIndex(int i, int j, int max, List<Integer> significantIndexesI,
            List<Integer> significantIndexesJ) {
        if (arrows[i][j] == '\\') {
            max = matrix[i][j];
            significantIndexesI.add(i);
            significantIndexesJ.add(j);
            checkIndex(i - 1, j - 1, max, significantIndexesI, significantIndexesJ);
        } else if (arrows[i][j] == '^') {
            significantIndexesI.add(i);
            significantIndexesJ.add(j);
            checkIndex(i - 1, j, max, significantIndexesI, significantIndexesJ);
        } else if (arrows[i][j] == '<') {
            significantIndexesI.add(i);
            significantIndexesJ.add(j);
            checkIndex(i, j - 1, max, significantIndexesI, significantIndexesJ);
        }
    }

    public void display() {
        List<Integer> significantIndexesI = new ArrayList<>();
        List<Integer> significantIndexesJ = new ArrayList<>();
        checkIndex(indexI, indexJ, matrix[indexI][indexJ], significantIndexesI, significantIndexesJ);
        if (significantIndexesI.size() != significantIndexesJ.size())
            throw new IllegalStateException("These ArrayLists have to be the same size.");
        int checker = significantIndexesI.size() - 1;
        for (int i = 0; i <= indexI + 1; i++) {
            for (int j = 0; j <= indexJ + 1; j++) {
                if (checker >= 0 && significantIndexesI.get(checker) == i - 1
                        && significantIndexesJ.get(checker) == j - 1) {
                    checker--;
                    if (arrows[i - 1][j - 1] == '\\')
                        System.out.print(" " + arrows[i - 1][j - 1] + "   ");
                    else if (arrows[i - 1][j - 1] == '<')
                        checker++;
                    else
                        System.out.print("   " + arrows[i - 1][j - 1] + " ");
                } else
                    System.out.print("     ");
                if (j == indexJ + 1)
                    System.out.println("");
            }
            for (int j = 0; j <= indexJ + 1; j++) {
                if (i == 0 && j == 0)
                    System.out.print("     ");
                else if (i == 0)
                    printChars(j, secondStr);
                else if (j == 0)
                    printChars(i, firstStr);
                else {
                    if (arrows[i - 1][j - 1] == '<' && checker >= 0 && significantIndexesI.get(checker) == i - 1
                            && significantIndexesJ.get(checker) == j - 1) {
                        if (matrix[i - 1][j - 1] > 9)
                            System.out.print(" " + arrows[i - 1][j - 1] + " " + matrix[i - 1][j - 1]);
                        else
                            System.out.print(" " + arrows[i - 1][j - 1] + " " + matrix[i - 1][j - 1] + " ");
                        checker--;
                    } else {
                        if (matrix[i - 1][j - 1] > 9)
                            System.out.print("   " + matrix[i - 1][j - 1]);
                        else
                            System.out.print("   " + matrix[i - 1][j - 1] + " ");
                    }
                }
                if (j == indexJ + 1)
                    System.out.println("");
            }
        }

    }

    private void printChars(int index, char[] str) {
        if (index < 2)
            System.out.print("     ");
        else if (str[index - 2] == '\n')
            System.out.print("   \\n");
        else if (str[index - 2] == '\t')
            System.out.print("   \\t");
        else if (str[index - 2] == ' ')
            System.out.print("   \\s");
        else if (str[index - 2] == '\r')
            System.out.print("   \\r");
        else
            System.out.print("   " + str[index - 2] + " ");
    }
}