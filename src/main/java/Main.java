import maingame.MinesweeperGame;
import userinterface.UserInterface;

public class Main {
    public static void main(String[] args) {


        UserInterface ui = new UserInterface();
        MinesweeperGame game = new MinesweeperGame(ui);
        game.start();
    }
}
