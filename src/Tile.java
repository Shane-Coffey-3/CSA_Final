import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {

    public static final int AIR_TILE = 0;
    public static final int GROUND_TILE = 1;
    public static final int WATER_TILE = 2;
    protected static int tileSize;

    private int x, y;
    private int tileType;

    public Tile(int x, int y, int tileType) {
        this.tileType = tileType;
        this.x = x;
        this.y = y;
    }

    public static void setTileSize(int newTileSize) {
        tileSize = newTileSize;
    }

    public int getTileType() {
        return tileType;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        Color color;
        switch(tileType) {
            case AIR_TILE:
                color = Color.GRAY;
                break;
            case GROUND_TILE:
                color = Color.BLACK;
                break;
            case WATER_TILE:
                color = Color.BLUE;
                break;
            default:
                color = Color.WHITE;
        }

        g.setColor(color);
        g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
    }
}