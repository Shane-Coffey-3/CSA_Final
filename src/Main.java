import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        Game game = new Game(18, 1); // num on left is tile size
        frame.setSize(game.getWidthOfGame(), game.getHeightOfGame());

        frame.add(game);
        frame.setVisible(true);
    }
}
