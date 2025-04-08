import javax.swing.*;
import java.awt.*;

public class Arena {

    public static final Tile[][] emptyMap = new Tile[20][30];

    private Tile[][] map;

    public Arena(Tile[][] map) {
        this.map = map;
    }

    public Tile[][] getMap() {
        return map;
    }

    public Tile getTileAt(int[] arr) {
        return map[arr[0]][arr[1]];
    }

    public void drawTiles(Graphics g) {
        for(Tile[] tileArr : map) {
            for(Tile tile : tileArr) {
                tile.draw(g);
            }
        }
    }
}
