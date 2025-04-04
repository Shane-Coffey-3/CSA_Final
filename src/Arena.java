import javax.swing.*;

public class Arena {

    public static final Tile[][] emptyMap = new Tile[20][20];

    private Tile[][] map;

    public Arena(Tile[][] map) {
        this.map = map;
    }

    public Tile[][] getMap() {
        return map;
    }
}
