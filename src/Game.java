import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel {
    private Arena arena;
    private Player playerOne;
    private Player playerTwo;

    public Game() {
        setFocusable(true);
        requestFocus();
        setBackground(Color.LIGHT_GRAY);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
            }
        });

        playerOne = new Player(100, 400, 20, Color.BLUE);
        playerTwo = new Player(700, 400, 20, Color.RED);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        playerOne.draw(g);
        playerTwo.draw(g);

        try {
            Thread.sleep(20);
        } catch(Exception e){
            System.out.println("Exception:\n" + e);
        }


        repaint();
    }
}