import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {


    public static void main(String[] args) {
        sudoku sudokuPuzzleGenerator = new sudoku();
        long start = System.currentTimeMillis();
        int numOfSudokuMatrix = 100;
        List<int[][]> sudoku = new ArrayList<int[][]>();
        for (int count = 1; count <= numOfSudokuMatrix; count++) {
            int[][] randomMatrix = sudokuPuzzleGenerator.generatePuzzleMatrix();
            int hole = 50;
            while (hole > 0) {
                Random randomGenerator = new Random();
                int x = randomGenerator.nextInt(16);
                int y = randomGenerator.nextInt(16);
                if (randomMatrix[x][y] != -1) {
                    randomMatrix[x][y] = -1;
                    hole--;
                }
            }
           /*for(int i=0;i<16;i++){
                for(int j=0;j<16;j++){
                    System.out.print(randomMatrix[i][j] + " ");
                }
                System.out.println();
            }*/
            sudoku.add(randomMatrix);
        }

        System.out.println();
        List<int[][]> sudoku1= new ArrayList<int[][]>();
        List<int[][]> sudoku2= new ArrayList<int[][]>();
        List<int[][]> sudoku3= new ArrayList<int[][]>();

        for(int[][] i : sudoku){
            int tmp[][]=new int[16][16];
            for (int k=0;k<16;k++){
                for (int j=0;j<16;j++){
                    tmp[k][j]=i[k][j];
                }
            }
            sudoku1.add(tmp);
        }
        for(int[][] i : sudoku){
            int tmp[][]=new int[16][16];
            for (int k=0;k<16;k++){
                for (int j=0;j<16;j++){
                    tmp[k][j]=i[k][j];
                }
            }
            sudoku2.add(tmp);
        }
        for(int[][] i : sudoku){
            int tmp[][]=new int[16][16];
            for (int k=0;k<16;k++){
                for (int j=0;j<16;j++){
                    tmp[k][j]=i[k][j];
                }
            }
            sudoku3.add(tmp);
        }
        long start3 = System.currentTimeMillis();
        for (int[][] p:sudoku3) {

            DLX s = new DLX();
            s.DLX(p);
            s.solve();
          /*  for (int rowNum = 0; rowNum < 16; rowNum++) {
                for (int colNum = 0; colNum < 16; colNum++) {
                    System.out.print(p[rowNum][colNum] + " ");
                }
                System.out.println();
            }*/
        }
        Long end3 = System.currentTimeMillis();
        Long time3 = end3 - start3;
        System.out.println("Dancing links took: " + time3  + " milliseconds.");
        System.out.println();
        long start2 = System.currentTimeMillis();
        for (int[][] p:sudoku2) {

            Dfs d=new Dfs();
            d.dfs(p,0,0);
         /*   for (int rowNum = 0; rowNum < 16; rowNum++) {
                for (int colNum = 0; colNum < 16; colNum++) {
                    System.out.print(p[rowNum][colNum] + " ");
                }
                System.out.println();
            }
            System.out.println();*/
        }
        Long end2 = System.currentTimeMillis();
        Long time2 = end2 - start2;
        System.out.println("DFS took: " + time2  + " milliseconds.");

        System.out.println();



        long start1 = System.currentTimeMillis();
        for (int[][] p:sudoku1) {
            backTracking b=new backTracking();
            int[][] s = b.backTrack(p);
   /*         for (int rowNum = 0; rowNum < 16; rowNum++) {
                for (int colNum = 0; colNum < 16; colNum++) {
                    System.out.print(p[rowNum][colNum] + " ");
                }
                System.out.println();
            }*/
        }
        System.out.println();
        Long end1 = System.currentTimeMillis();
        Long time1 = end1 - start1;
        System.out.println("Backtracking took: " + time1  + " milliseconds.");

        System.out.println();




    }
}




