import java.awt.*;

public class AirTile extends Tile {

    public AirTile(int x, int y) {
        super(0, x, y);

    }

    @Override
    public int getTileType() {
        return 0;
    }

    @Override
    public void draw(Graphics g) {

    }
}
