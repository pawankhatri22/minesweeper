package util;

import customeexception.GameException;

// to add constants and helper methods
public class Utility {
    public static final char UNREVEALED = '_';
    public static final char MINE = '*';
    public static final int MINE_VALUE = -1;


    public static final String MESSAGE_INVALID_INPUT = "Invalid input provided";
    public static final String MESSAGE_ALREADY_REVEALED = "Already revealed cell. Select any other.";
    public static final String MESSAGE_GAME_OVER = "Oh no, you detonated a mine! Game over.";
    public static final String MESSAGE_SELECT_SQUARE = "Select a square to reveal (e.g. A1): ";
    public static final String MESSAGE_GAME_WON = "Congratulations, you have won the game! ";
    public static final String MESSAGE_GRID_SIZE = "Enter the size of the grid (e.g., 4 for a 4x4 grid): ";
    public static final String MESSAGE_TOTAL_MINES = "Enter the number of mines to place on the grid (maximum is ";
    public static final String MESSAGE_INVALID_INPUT_LENGTH = "Invalid input length. Please enter a letter (A-Z) followed by a number (e.g., A1).";
    public static final String MESSAGE_INVALID_FIRST_CHARACTER = "Invalid row identifier. Please enter a letter between A and ";
    public static final String MESSAGE_INVALID_SECOND_CHARACTER = "Invalid column number. Please enter a numeric value greater than 0 (e.g., A1). ";
    public static final String MESSAGE_COLUMN_MIN_VALUE = "Column number must be greater than 0 and less than ";
    public static final String MESSAGE_CONTINUE_GAME = "Press any key to play again or q to quit...";
    public static final String MESSAGE_WELCOME_MESSAGE = "Welcome to Minesweeper!";
    //validate range of row and column should in bound
    public static boolean isValidCell(int row, int col,int size){
        return row>=0 && row<size && col>=0 && col<size;
    }

    //public first character validation;

    public static int inputValidation(String input, int size){
        int colNumber  = -1;
        if(input.length()!=2){
            throw new GameException(Utility.MESSAGE_INVALID_INPUT_LENGTH);
        }
        //first character should be between A and character upto size provied
        if (input.charAt(0) < 'A' || input.charAt(0) > ('A'+size-1)) {
            throw new GameException(Utility.MESSAGE_INVALID_FIRST_CHARACTER + (char)('A'+size-1));
        }

        try {
            colNumber = Integer.parseInt(input.substring(1));
        } catch (NumberFormatException e) {
            throw new GameException(Utility.MESSAGE_INVALID_SECOND_CHARACTER);
        }

        if (colNumber <= 0 || colNumber>size) {
            throw new GameException(Utility.MESSAGE_COLUMN_MIN_VALUE + size);
        }

        return colNumber;
    }

}
