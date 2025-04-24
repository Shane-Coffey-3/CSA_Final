import java.awt.*;

public class Player {

    private int x, y, size, speed = 10;
    private Color color;
    double verticalVelocity = 0;
    double jumpStrength = 20;
    boolean isMovingLeft, isMovingRight;

    public Player(int x, int y, int size, Color color) {
            this.x = x;
            this.y = y;
            this.size = size;
        this.color = color;
    }

    public void draw(Graphics g, Dimension screenSize, Tile[][] map) {
        updateHeight(screenSize.height);

        int lXTile = x / Tile.tileSize;
        int rXTile = (x + size) / Tile.tileSize;
        int tYTile = y / Tile.tileSize;
        int bYTile = (y + size) / Tile.tileSize;

        // collisions
        interactAllTiles(lXTile, rXTile, tYTile, bYTile, map);

        // bottom touching


        if(isMovingLeft) x -= speed;
        if(isMovingRight) x += speed;

        g.setColor(color);
        g.fillRect(x, y, size, size);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void jump() {
        verticalVelocity = -jumpStrength;
    }

    public void updateHeight(int screenHeight) {
        verticalVelocity++;
        y += (int) verticalVelocity;

        if(y + size > screenHeight) {
            y = screenHeight - size;
        }
    }

    public void setIsMovingLeft(boolean isMovingLeft) {
        this.isMovingLeft = isMovingLeft;
    }

    public void setIsMovingRight(boolean isMovingRight) {
        this.isMovingRight = isMovingRight;
    }

    private void interactAllTiles(int lXTile, int rXTile, int tYTile, int bYTile, Tile[][] map) {
        for(int i = lXTile; i <= rXTile; i++) {
            interactTile(i, bYTile, map);
        }
    }

    private void interactTile(int x, int y, Tile[][] map) {
        if(y >= map.length || y < 0 || x >= map[0].length || x < 0) {
            return;
        }
        switch(map[y][x].getTileType()) {
            case(Tile.AIR_TILE):
                break;
            case(Tile.GROUND_TILE):
                moveOutOfTile(x, y);
                verticalVelocity = 1;
                break;
            default:
                break;
        }
    }

    private void moveOutOfTile(int tileXInArr, int tileYInArr) {
        x = tileXInArr * Tile.tileSize;
        y = tileYInArr * Tile.tileSize;

        int distMoveUp = (this.y + this.size) - y;
        int distMoveDown = (y + Tile.tileSize) - this.y;
        int distMoveLeft = (this.x + this.size) - x;
        int distMoveRight = (x + Tile.tileSize) - this.x;

        if(distMoveLeft < distMoveDown && distMoveLeft < distMoveUp && distMoveLeft < distMoveRight) {
            this.x = x - this.size;
        } else if(distMoveRight < distMoveDown && distMoveRight < distMoveUp) {
            this.x = x + Tile.tileSize;
        } else if(distMoveUp < distMoveDown) {
            this.y = y - this.size;
        } else {
            this.y = y + Tile.tileSize;
        }

    }

}
