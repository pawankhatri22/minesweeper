package grid;

import customeexception.GameException;
import grid.GameGrid;
import org.junit.jupiter.api.Test;
import util.Utility;

import static org.junit.jupiter.api.Assertions.*;

public class GameGridTest {

    @Test
    void testMinePlacement(){
        GameGrid grid = new GameGrid(5,5);
        int mineCount = 0;
        char[][] display = grid.getDisplayGrid(true);
        for (char[] row : display) {
            for (char cell : row) {
                if (cell == '*') mineCount++;
            }
        }
        assertEquals(5,mineCount,"Incorrect number of mines placed");
    }

    @Test
    void testInputLengthLessThanTwo(){
        int gridSize = 4;
        GameGrid grid = new GameGrid(gridSize,2);
        Exception exception = assertThrows(GameException.class,()->{
            grid.revealCell("");
        });

        assertEquals(Utility.MESSAGE_INVALID_INPUT_LENGTH  , exception.getMessage());
    }

    @Test
    void testInputLengthGreaterThanTwo(){
        int gridSize = 4;
        GameGrid grid = new GameGrid(gridSize,2);
        Exception exception = assertThrows(GameException.class,()->{
            grid.revealCell("A91");
        });

        assertEquals(Utility.MESSAGE_INVALID_INPUT_LENGTH  , exception.getMessage());
    }

    @Test
    void testInvalidFirstInput(){
        int gridSize = 4;
        GameGrid grid = new GameGrid(gridSize,2);
        Exception exception = assertThrows(GameException.class,()->{
            grid.revealCell("Z9");
        });

        assertEquals(Utility.MESSAGE_INVALID_FIRST_CHARACTER + ((char)('A' + gridSize-1)) , exception.getMessage());
    }

    @Test
    void testInvalidSecondInput(){
        int gridSize = 4;
        GameGrid grid = new GameGrid(gridSize,2);
        Exception exception = assertThrows(GameException.class,()->{
            grid.revealCell("A9");
        });

        assertEquals(Utility.MESSAGE_COLUMN_MIN_VALUE + gridSize , exception.getMessage());
    }


    @Test
    void testGameOverOnMine() {
        GameGrid grid = new GameGrid(3, 3);

        GameException exception = assertThrows(GameException.class, () -> {
            grid.revealCell("A1");
            grid.revealCell("A2");
            grid.revealCell("A3");
            grid.revealCell("B1");

            grid.revealCell("B2");
            grid.revealCell("B3");
            grid.revealCell("C1");
            grid.revealCell("C2");
            grid.revealCell("C3");
        });

        assertEquals(Utility.MESSAGE_GAME_OVER, exception.getMessage());
    }




}
