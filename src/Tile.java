import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {

    public static final int AIR_TILE = 0;
    public static final int GROUND_TILE = 1;
    public static final int WATER_TILE = 2;
    public static final int JUMP_TILE = 3;
    public static final int BORDER_TILE = 4;
    public static int tileSize;

    private static float borderColor = 0;

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

    public void draw(Graphics g, Game game) {
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
            case JUMP_TILE:
                color = Color.GREEN;
                break;
            case BORDER_TILE:
                if(game.getInEditorMode()) {
                    color = Color.getHSBColor(borderColor, 1, 1);
                    incrementBorderColor();
                } else {
                    color = Color.BLACK;
                }
                break;
            default:
                color = Color.WHITE;
        }

        g.setColor(color);
        //g.fillRect(x * tileSize, (y * tileSize + game.getArena().getTileOffset()) % 720, tileSize, tileSize);
        g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
    }

    public boolean isSolid() {
        switch (tileType) {
            case AIR_TILE:
                return false;
            case WATER_TILE:
                return false;
            case GROUND_TILE:
                return true;
            case JUMP_TILE:
                return true;
            case BORDER_TILE:
                return true;
            default: 
                return false;
        }
    }

    public void setTileType(int newTileType) {
        tileType = newTileType;
    }

    public static void incrementBorderColor() {
        borderColor = (float) (borderColor + 0.000002);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
