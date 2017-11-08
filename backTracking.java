import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mayu8 on 12/11/2016.
 */
public class backTracking {

    public static void main(String[] args) {
        sudoku sudokuPuzzleGenerator = new sudoku();
        
        int numOfSudokuMatrix = 1;
        List<int[][]> sudoku = new ArrayList<int[][]>();
        for (int count = 1; count <= numOfSudokuMatrix; count++) {
            int[][] randomMatrix = sudokuPuzzleGenerator.generatePuzzleMatrix();
            int hole = 81;
            while (hole > 0) {
                Random randomGenerator = new Random();
                int x = randomGenerator.nextInt(16);
                int y = randomGenerator.nextInt(16);
                if (randomMatrix[x][y] != -1) {
                    randomMatrix[x][y] = -1;
                    hole--;
                }
            }
            sudoku.add(randomMatrix);
        }
        

        for (int[][] p:sudoku) {
            for (int rowNum = 0; rowNum < 16; rowNum++) {
                for (int colNum = 0; colNum < 16; colNum++) {
                    if(p[rowNum][colNum]>9 || p[rowNum][colNum]<0)System.out.print(p[rowNum][colNum] + " ");
                    else{System.out.print(p[rowNum][colNum] + "  ");}
                }
                System.out.println();
            }
            long start = System.currentTimeMillis();
            int[][] s = backTrack(p);
            Long end = System.currentTimeMillis();
            Long time = end - start;
            System.out.println("It took: " + time  + " milliseconds.");
            System.out.println();
            
            
            
            System.out.println();
            System.out.println();
            for (int rowNum = 0; rowNum < 16; rowNum++) {
                for (int colNum = 0; colNum < 16; colNum++) {
                    if(s[rowNum][colNum]>9 || s[rowNum][colNum]<0)System.out.print(s[rowNum][colNum] + " ");
                    else{System.out.print(s[rowNum][colNum] + "  ");}
                }
                System.out.println();
            }
            
        }
        
        
    }

    public static int[][] backTrack(int[][] puzzle){
        List<Integer> x = new ArrayList<Integer>();
        List<Integer> y = new ArrayList<Integer>();

        for(int row = 0; row < 16; row++){
            for(int col = 0; col < 16; col++){
                if(puzzle[row][col] == -1){
                    x.add(row);
                    y.add(col);
                }
            }
        }
        int numberOfEmptyCells = x.size();

        for(int index = 0; index < numberOfEmptyCells; index++){
            int row = x.get(index);
            int col = y.get(index);
            if(puzzle[row][col] == -1){
                puzzle[row][col] = 1;
            }
            else{
                puzzle[row][col] += 1;
            }

            while(!checkConflict(puzzle, row, col)){
                puzzle[row][col] += 1;
            }
            if(puzzle[row][col] == 17){
                puzzle[row][col] = -1;
                index -= 2;
            }
        }

        return puzzle;
    }

    private static boolean checkConflict(int[][] candidateMatrix, int row, int col) {
        return checkRow(candidateMatrix, row, col)
                && checkCol(candidateMatrix, row, col);
                //&& checkBox(candidateMatrix, row, col);
    }

    private static boolean checkRow(int[][] candidateMatrix, int row, int col) {
        int currentValue = candidateMatrix[row][col];
        for (int colNum = 0; colNum < 16; colNum++) {
            if(colNum != col){
                if (currentValue == candidateMatrix[row][colNum]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkCol(int[][] candidateMatrix, int row, int col) {
        int currentValue = candidateMatrix[row][col];
        for (int rowNum = 0; rowNum < 16; rowNum++) {
            if(rowNum != row){
                if (candidateMatrix[rowNum][col] == currentValue) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkBox(int[][] candidateMatrix, int row, int col){
        int baseRow = row / 4 * 4;
        int baseCol = col / 4 * 4;

        for (int x = baseRow; x < baseRow+4; x++){
            for(int y = baseCol; y < baseCol+4; y++){
                if(row != x && col != y){
                    if(candidateMatrix[x][y] == candidateMatrix[row][col]) return false;
                }
            }
        }
        return true;
    }
}
