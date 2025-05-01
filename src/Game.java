import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel {
    private long duration, prevMillis = System.currentTimeMillis();
    private Arena arena;
    private Player playerOne;
    private Player playerTwo;
    private final int TILE_SIZE;

    public Game(int tileSize) {
        TILE_SIZE = tileSize;
        setFocusable(true);
        requestFocus();
        setBackground(Color.LIGHT_GRAY);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'a') {
                    playerOne.setIsMovingLeft(true);
                } else if(e.getKeyChar() == 'd') {
                    playerOne.setIsMovingRight(true);
                } else if(e.getKeyChar() == 'w') {
                    playerOne.jump();
                }

                if(e.getKeyCode() == 37) {
                    playerTwo.setIsMovingLeft(true);
                } else if(e.getKeyCode() == 39) {
                    playerTwo.setIsMovingRight(true);
                } else if(e.getKeyCode() == 38) {
                    playerTwo.jump();
                }
                System.out.println(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == 'a') {
                    playerOne.setIsMovingLeft(false);
                } else if(e.getKeyChar() == 'd') {
                    playerOne.setIsMovingRight(false);
                } else if(e.getKeyCode() == 37) {
                    playerTwo.setIsMovingLeft(false);
                } else if(e.getKeyCode() == 39) {
                    playerTwo.setIsMovingRight(false);
                }
            }
        });

        playerOne = new Player(100, 100, 20, Color.BLUE);
        playerTwo = new Player(700, 100, 20, Color.RED);
        arena = new Arena(1, tileSize);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        arena.drawTiles(g);

        playerOne.draw(g, getSize(), arena.getMap(), (int) duration);
        playerTwo.draw(g, getSize(), arena.getMap(), (int) duration);

        try {
            Thread.sleep(1);
            duration = System.currentTimeMillis() - prevMillis;
            prevMillis = System.currentTimeMillis();
        } catch(Exception e){
            System.out.println("Exception:\n" + e);
        }

        // get player coordinate


        repaint();
    }

    public int getHeightOfGame() {
        return (arena.getMap().length + 1) * Tile.tileSize;
    }

    public int getWidthOfGame() {
        return arena.getMap()[0].length* Tile.tileSize;
    }
}