package userinterface;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class UserInterfaceTest {
    @Test
    void testGetUserInput() {
        UserInterface ui = mock(UserInterface.class);
        when(ui.getUserInput("Enter grid size:")).thenReturn("4");
        String input = ui.getUserInput("Enter grid size:");
        assertEquals("4", input);
    }

    @Test
    void testGetUserInputAsInt() {
        UserInterface ui = mock(UserInterface.class);
        when(ui.getUserInputAsInt("Enter grid size:")).thenReturn(4);
        int input = ui.getUserInputAsInt("Enter grid size:");
        assertEquals(4, input);
    }

    @Test
    void testDisplayGrid() {
        UserInterface ui = mock(UserInterface.class);

        char[][] grid = new char[4][4];
        grid[0][0] = '1';
        ui.displayGrid(grid);

        verify(ui).displayGrid(grid);
    }
}
