package maingame;

import grid.GameGrid;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import userinterface.UserInterface;
import util.Utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MinesweeperGameTest {
    @Test
    void testFullGameFlow() {
        UserInterface ui = mock(UserInterface.class);

        // Mock the methods to simulate game flow
        when(ui.getUserInputAsInt(Utility.MESSAGE_GRID_SIZE)).thenReturn(9);
        when(ui.getUserInputAsInt(anyString(), anyInt(), anyInt())).thenReturn(1);
        when(ui.getUserInput(anyString())).thenReturn("A1", "B2");
        when(ui.getUserInput2(anyString())).thenReturn("q");

        MinesweeperGame game = new MinesweeperGame(ui);

        game.start();

        verify(ui).showWelcomeMessage();
        verify(ui).getUserInputAsInt(Utility.MESSAGE_GRID_SIZE);
        verify(ui, atLeastOnce()).displayGrid(any());
        verify(ui).getUserInput2(Utility.MESSAGE_CONTINUE_GAME);
    }
}
