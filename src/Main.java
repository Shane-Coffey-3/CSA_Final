import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 1200);

        Game game = new Game(40);

        frame.add(game);
        frame.setVisible(true);
    }
}