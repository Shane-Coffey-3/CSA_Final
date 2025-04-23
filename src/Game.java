import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel {
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
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == 'a') {
                    playerOne.setIsMovingLeft(false);
                } else if(e.getKeyChar() == 'd') {
                    playerOne.setIsMovingRight(false);
                }
            }
        });

        playerOne = new Player(100, 400, 20, Color.BLUE);
        playerTwo = new Player(700, 400, 20, Color.RED);
        arena = new Arena(1, 20);
    }

    @Override
    public void paintComponent(Graphics g) {

        int[] playerOneCoordinate = new int[] {(playerOne.getY() + (playerOne.getSize() / 2)) / TILE_SIZE, (playerOne.getX() + (playerOne.getSize() / 2)) / TILE_SIZE};
        int[] playerTwoCoordinate = new int[] {(playerTwo.getY() + (playerTwo.getSize() / 2)) / TILE_SIZE, (playerTwo.getX() + (playerTwo.getSize() / 2)) / TILE_SIZE};

        //if((arena.getTileAt(playerOneCoordinate)).getTileType() == Tile.GROUND_TILE && playerOne.isTouchingTile()) {

        //}

        super.paintComponent(g);

        arena.drawTiles(g);

        playerOne.draw(g, getSize(), arena.getMap());
        playerTwo.draw(g, getSize(), arena.getMap());

        try {
            Thread.sleep(20);
        } catch(Exception e){
            System.out.println("Exception:\n" + e);
        }

        // get player coordinate


        repaint();
    }
}