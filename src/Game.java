import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel {
    private long duration = 0, prevMillis = System.currentTimeMillis();
    private Arena arena;
    private Player playerOne;
    private Player playerTwo;
    private boolean inEditorMode = false;
    private boolean shiftPressed = false;
    private double seconds = 0;
    private final long startMillis = System.currentTimeMillis();
    private boolean startMovingMap = false;
    private boolean someoneWon = false;
    private double secondsToDelay = 1;
    private int numTimesCycled = 0;
    private int redWins = 0;
    private int blueWins = 0;

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
                } else if(e.getKeyChar() == 'p') {
                    arena.printAsNumbers();
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

        playerOne = new Player(100, 500, tileSize - 1, Color.CYAN);
        playerTwo = new Player(120, 500, tileSize - 1, Color.RED);
        arena = new Arena(mapCode, tileSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        if(someoneWon) {
            repaint();
            return;
        }
        super.paintComponent(g);

        arena.drawTiles(g, this);

        playerOne.draw(g, getSize(), arena.getMap(), (int) duration, this);
        playerTwo.draw(g, getSize(), arena.getMap(), (int) duration, this);

        if(startMovingMap && seconds > secondsToDelay) {
            arena.moveDown();
            playerOne.moveDown();
            playerTwo.moveDown();
            seconds = 0;
            numTimesCycled++;
        } else if(!startMovingMap && seconds > 5) {
            seconds = 0;
            startMovingMap = true;
        }

        if(numTimesCycled % 40 == 0 && numTimesCycled > 0) {
            secondsToDelay *= 0.9;
        }

        try {
            Thread.sleep(1);
            duration = System.currentTimeMillis() - prevMillis;
            prevMillis = System.currentTimeMillis();
            seconds += duration / 1000.0;
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

    public boolean getInEditorMode() {
        return inEditorMode;
    }

    public Arena getArena() {
        return arena;
    }

    public void playerWins(Color color) {
        someoneWon = true;
        String message;
        if (color.equals(Color.RED)) {
            message = "Blue wins! Click to play again";
            blueWins++;
        } else {
            message = "Red wins! Click to play again";
            redWins++;
        }

        JOptionPane.showMessageDialog(null, message);

        SwingUtilities.invokeLater(() -> resetGame());
    }

    private void resetGame() {
        duration = 0;
        prevMillis = System.currentTimeMillis();
        seconds = 0;
        startMovingMap = false;
        secondsToDelay = 1;
        numTimesCycled = 0;

        playerOne = new Player(100, 500, Tile.tileSize - 1, Color.CYAN);
        playerTwo = new Player(120, 500, Tile.tileSize - 1, Color.RED);

        arena = new Arena(1, Tile.tileSize);

        someoneWon = false;

        repaint();
    }
}
