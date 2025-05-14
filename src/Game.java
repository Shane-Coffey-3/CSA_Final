import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel {
    private long duration, prevMillis = System.currentTimeMillis();
    private Arena arena;
    private Player playerOne;
    private Player playerTwo;
    private boolean inEditorMode = false;
    private boolean shiftPressed = false;

    public Game(int tileSize, int mapCode) {
        setFocusable(true);
        requestFocus();
        setBackground(Color.LIGHT_GRAY);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(inEditorMode) {
                    int[] coord = {e.getY() / Tile.tileSize, e.getX() / Tile.tileSize};
                    Tile tile = arena.getMap()[coord[0]][coord[1]];
                    if(shiftPressed) {
                        tile.setTileType(tile.getTileType() - 1);
                    } else {
                        tile.setTileType(tile.getTileType() + 1);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

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
                    playerOne.jump(arena.getMap());
                } else if(e.getKeyChar() == 'e') {
                    inEditorMode = !inEditorMode;
                }

                if(e.getKeyCode() == 37) {
                    playerTwo.setIsMovingLeft(true);
                } else if(e.getKeyCode() == 39) {
                    playerTwo.setIsMovingRight(true);
                } else if(e.getKeyCode() == 38) {
                    playerTwo.jump(arena.getMap());
                } else if(e.getKeyCode() == 16) {
                    shiftPressed = true;
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
                } else if(e.getKeyCode() == 16) {
                    shiftPressed = false;
                }
            }
        });

        playerOne = new Player(100, 100, tileSize - 1, Color.CYAN);
        playerTwo = new Player(700, 100, tileSize - 1, Color.RED);
        arena = new Arena(mapCode, tileSize);
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