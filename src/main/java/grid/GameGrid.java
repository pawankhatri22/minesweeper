package grid;

import customeexception.GameException;
import util.Utility;

import java.util.Random;

public class GameGrid {


    private final int size;
    private final int totalMines;
    private final char[][] displayGrid;
    private final int[][] mineGrid;
    private final boolean[][] revealed;
    private int uncoveredCells;
    private boolean gameOver;

    public GameGrid(int size, int totalMines){
        this.size = size;
        this.totalMines = totalMines;
        this.displayGrid = new char[size][size];
        this.mineGrid = new int[size][size];
        this.revealed = new boolean[size][size];
        this.uncoveredCells = 0;
        this.gameOver = false;
        initializeDisplay();
        placeMines();
        calculateAdjacentMines();
     }

    //calculate mines on all possible locations adjacent to each square and add that number to square as value in mineGrid
    private void calculateAdjacentMines() {
        int[] directions = {-1, 0, 1};
        for(int row =0;row<size;row++){
            for(int col = 0;col<size;col++){
                //if square have mine so leave an goto next
                if(mineGrid[row][col] == -1) continue;

                int count = 0;
                //if left , right , up , down or diagonals on both sizes have mines add 1 to count for each
                for(int rowDirection: directions){
                    for (int colDirection: directions){
                        if(rowDirection == 0 && colDirection == 0) continue;
                        int nextRow = row+rowDirection;
                        int nextCol = col + colDirection;
                        if(Utility.isValidCell(nextRow, nextCol,size) && mineGrid[nextRow][nextCol]==Utility.MINE_VALUE){
                            count++;
                        }

                    }
                }

                mineGrid[row][col] = count;

            }
        }

    }


    //Place  totalMines  number of mines  on different random locations
    private void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;

        while (minesPlaced<totalMines){
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            //place mine if not placed already
            if(mineGrid[row][col]!= Utility.MINE_VALUE){
                mineGrid[row][col] = Utility.MINE_VALUE;
                minesPlaced++;
            }
        }
    }

    //intialize display grid with _ when game starts
     private void initializeDisplay(){
         for(int row=0;row<size;row++){
             for(int col = 0;col<size;col++){
                 displayGrid[row][col] = Utility.UNREVEALED;
             }
         }
     }

     //validate input and check for game end condition , if not uncover cell
     public void revealCell(String input){
        //ASCI value of A = 65, if entered A row will become zero, if B(66) row will become 1

         int colNumber = Utility.inputValidation(input, size);

         int row = input.charAt(0) - 'A';
         int col = colNumber - 1;

        if(!Utility.isValidCell(row, col, size)){
            throw new GameException(Utility.MESSAGE_INVALID_INPUT);
        }

        if(revealed[row][col]){
            throw new GameException(Utility.MESSAGE_ALREADY_REVEALED);
        }

        if(mineGrid[row][col] == -1){
            gameOver = true;
            throw new GameException(Utility.MESSAGE_GAME_OVER);
        }

        uncoverCells(row, col);
     }

     //add zero if not adjecent mines to current square other wise number of adjecent mines util all neigbor with mine found
    private void uncoverCells(int row, int col) {
        if(!Utility.isValidCell(row,col,size) || revealed[row][col]) return;
        int[] directions = {-1,0,1};
        revealed[row][col] = true;
        uncoveredCells++;
        //if no adjecent mine to current square add 0 to display
        if(mineGrid[row][col] == 0){
            displayGrid[row][col] = '0';
            //recursively check all adjecent squares of current and mark them with number of adjecent mines accordingly;
            for(int rowDirection: directions){
                for(int colDirection: directions){
                    uncoverCells(row+rowDirection, col+colDirection);
                }
            }
        }else{
            //convert number of adjacent mines to current mine from mineGrid from int to char
            displayGrid[row][col] = (char)('0' + mineGrid[row][col]);
        }
    }

    public char[][] getDisplayGrid(){
        return getDisplayGrid(false);
    }

    //display state of grid , and mines when game is over
    public char[][] getDisplayGrid(boolean revealMines){
        char[][] grid = new char[size][size];
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                //if game over show all the mines
                if(revealMines && mineGrid[row][col] == Utility.MINE_VALUE){
                    grid[row][col] = Utility.MINE;
                }else{
                    //show current state of grid with revealed and unrevealed squares
                    grid[row][col] = revealed[row][col]?displayGrid[row][col]: Utility.UNREVEALED;
                }
            }
        }
        return grid;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    //game is won when uncovered cell become equal to total cell minus cells with mines
    public boolean isGameWon(){
        return uncoveredCells == size*size-totalMines;
    }


}
