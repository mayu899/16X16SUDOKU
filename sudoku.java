/**
 * Created by mayu8 on 12/11/2016.
 */
import java.util.*;

public class sudoku {
    private Random random = new Random();
    private static final int MAX_CALL_RANDOM_ARRAY_TIMES = 220;
    private int currentTimes = 0;
    public int[][] generatePuzzleMatrix() {
        int[][] randomMatrix = new int[16][16];
        for (int row = 0; row < 16; row++) {
            if (row == 0) {
                currentTimes = 0;
                randomMatrix[row] = buildRandomArray();

            } else {
                int[] tempRandomArray = buildRandomArray();

                for (int col = 0; col < 16; col++) {
                    if (currentTimes < MAX_CALL_RANDOM_ARRAY_TIMES) {
                        if (!isCandidateNmbFound(randomMatrix, tempRandomArray,
                                row, col)) {
                            resetValuesInRowToZero(randomMatrix,row);
                            row -= 1;
                            col = 15;
                            tempRandomArray = buildRandomArray();
                        }
                    } else {
                        row = -1;
                        col = 15;
                        resetValuesToZeros(randomMatrix);
                        currentTimes = 0;
                    }
                }
            }
        }
        return randomMatrix;
    }

    private void resetValuesInRowToZero(int[][] matrix, int row)
    {
        for (int j = 0; j < 16; j++) {
            matrix[row][j] = 0;
        }

    }

    private void resetValuesToZeros(int[][] matrix) {
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                matrix[row][col] = 0;
            }
        }
    }

    private boolean isCandidateNmbFound(int[][] randomMatrix,
                                        int[] randomArray, int row, int col) {
        for (int i = 0; i < randomArray.length; i++) {
            randomMatrix[row][col] = randomArray[i];
            if (noConflict(randomMatrix, row, col)) {
                return true;
            }
        }
        return false;
    }

    private boolean noConflict(int[][] candidateMatrix, int row, int col) {
        return noConflictInRow(candidateMatrix, row, col)
                && noConflictInColumn(candidateMatrix, row, col)
                && noConflictInBlock(candidateMatrix, row, col);
    }

    private boolean noConflictInRow(int[][] candidateMatrix, int row, int col) {
        int currentValue = candidateMatrix[row][col];

        for (int colNum = 0; colNum < col; colNum++) {
            if (currentValue == candidateMatrix[row][colNum]) {
                return false;
            }
        }

        return true;
    }

    private boolean noConflictInColumn(int[][] candidateMatrix, int row, int col) {
        int currentValue = candidateMatrix[row][col];

        for (int rowNum = 0; rowNum < row; rowNum++) {
            if (currentValue == candidateMatrix[rowNum][col]) {
                return false;
            }
        }

        return true;
    }

    private boolean noConflictInBlock(int[][] candidateMatrix, int row, int col) {
        int baseRow = row / 4 * 4;
        int baseCol = col / 4 * 4;

        for (int rowNum = 0; rowNum < 15; rowNum++) {
            if (candidateMatrix[baseRow + rowNum / 4][baseCol + rowNum % 4] == 0) {
                continue;
            }
            for (int colNum = rowNum + 1; colNum < 16; colNum++) {
                if (candidateMatrix[baseRow + rowNum / 4][baseCol + rowNum % 4] == candidateMatrix[baseRow
                        + colNum / 4][baseCol + colNum % 4]) {
                    return false;
                }
            }
        }
        return true;

    }
    private int[] buildRandomArray() {
        currentTimes++;
        int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
        int randomInt = 0;
        for (int i = 0; i < 80; i++) {
            randomInt = random.nextInt(15) + 1;
            int temp = array[0];
            array[0] = array[randomInt];
            array[randomInt] = temp;
        }

        return array;
    }

    public int getCurrentTimes() {
        return currentTimes;
    }

    public void setCurrentTimes(int currentTimes) {
        this.currentTimes = currentTimes;
    }

}