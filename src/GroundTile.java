import java.awt.*;

public class GroundTile extends Tile {

    public GroundTile(int x, int y) {
        super(0, x, y);

    }

    @Override
    public int getTileType() {
        return 1;
    }

    @Override
    public void draw(Graphics g) {

    }
}
