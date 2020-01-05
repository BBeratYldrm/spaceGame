package spacegame.proje;

import java.awt.HeadlessException;
import javax.swing.JFrame;

public class GameScreen extends JFrame {

    public GameScreen() throws HeadlessException {
    }

    public static void main(String[] args) {

        GameScreen gameScreen = new GameScreen();
        gameScreen.setResizable(false);
        gameScreen.setFocusable(false);

        gameScreen.setSize(800, 600);
        gameScreen.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Game game = new Game();

        game.requestFocus();

        game.addKeyListener(game);

        game.setFocusable(true);

        game.setFocusTraversalKeysEnabled(true);

        gameScreen.add(game);
        gameScreen.setVisible(true);

    }
}
