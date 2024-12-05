package maingame;

import customeexception.GameException;
import grid.GameGrid;
import userinterface.UserInterface;
import util.Utility;

public class MinesweeperGame {

    private final UserInterface ui;

    public MinesweeperGame( UserInterface ui) {

        this.ui = ui;

    }

    //start a new game
    public void start(){
        boolean playGame = false;
        do{
           playGame =  playGame();
        }while (playGame);


    }

    private boolean playGame(){
        ui.showWelcomeMessage();
        //take user input
        int size = ui.getUserInputAsInt(Utility.MESSAGE_GRID_SIZE);
        int maxMines = (int) (size*size*0.35);
        int totalMines = ui.getUserInputAsInt(Utility.MESSAGE_TOTAL_MINES + maxMines + "): ", 1,maxMines);

        GameGrid grid = new GameGrid(size, totalMines);
        //continue until game is either loss or won
        while (!grid.isGameOver() && !grid.isGameWon()){
            //initlize game with initial grid, place mines and calculate adjacent mines
            ui.displayGrid(grid.getDisplayGrid());
            String input = ui.getUserInput(Utility.MESSAGE_SELECT_SQUARE);

            try {
                grid.revealCell(input);
                if(grid.isGameWon()){
                    //display grid state
                    ui.displayGrid(grid.getDisplayGrid(false));
                    ui.showMessage(Utility.MESSAGE_GAME_WON);
                }
            }catch (GameException ex){
                ui.showMessage(ex.getMessage());
                if(grid.isGameOver()){
                    //show grid with all mines
                    ui.displayGrid(grid.getDisplayGrid(true));
                }
            }
        }

        return promptPlayAgain();
    }

    private boolean promptPlayAgain() {

        String input = ui.getUserInput2(Utility.MESSAGE_CONTINUE_GAME).trim();
        if(input.isEmpty()) return true;
        return !input.equalsIgnoreCase("q");
    }
}
