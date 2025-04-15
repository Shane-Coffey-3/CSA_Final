import javax.swing.*;
import java.awt.*;

public abstract class Tile extends JPanel {

    public static final int AIR_TILE = 0;
    public static final int GROUND_TILE = 1;
    private static int tileSize;

    private final int x, y;
    private int tileType;

    public Tile(int tileType, int x, int y) {
        this.tileType = tileType;
        this.x = x;
        this.y = y;
    }

    public static void setTileSize(int newTileSize) {
        tileSize = newTileSize;
    }

    public abstract int getTileType();

    public abstract void draw(Graphics g);
}