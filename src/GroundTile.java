import java.awt.*;

public class GroundTile extends Tile {

    public GroundTile(int x, int y) {
        super(1, x, y);
        System.out.println("ground tile declared at " + x + ", " + (y) + " size " + Tile.tileSize);
    }

    @Override
    public int getTileType() {
        return 1;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(super.getX() * tileSize, super.getY() * tileSize, tileSize, tileSize);
        System.out.println(super.getX() * tileSize + ", " + super.getY() * tileSize);
        System.out.println(super.getX());
        System.out.println(super.getY());
        System.out.println(tileSize);
    }
}
