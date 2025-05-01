import java.awt.*;

public class Player {

    private double x, y, size;
    double speed = 0.5;
    private Color color;
    double verticalVelocity = 0;
    double jumpStrength = 2.2;
    boolean isMovingLeft, isMovingRight;
    double slowedRate = 1;

    boolean isTouchingGround, isTouchingCeiling, isTouchingLeftWall, isTouchingRightWall;

    public Player(int x, int y, int size, Color color) {
            this.x = x;
            this.y = y;
            this.size = size;
        this.color = color;
    }

    public void draw(Graphics g, Dimension screenSize, Tile[][] map, int time) {
        updateHeight(screenSize.height, time);

        int lXTile =(int) (x / Tile.tileSize);
        int rXTile = (int) ((x + size) / Tile.tileSize);
        int tYTile = (int) (y / Tile.tileSize);
        int bYTile = (int) ((y + size) / Tile.tileSize);

        if(isMovingLeft) x -= (speed * time * slowedRate);
        if(isMovingRight) x += (speed * time * slowedRate);
        // collisions
        interactAllTiles(lXTile, rXTile, tYTile, bYTile, map);

        g.setColor(color);
        g.fillRect((int) x, (int) y, (int) size, (int) size);
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getSize() {
        return (int) size;
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
        if(!isTouchingCeiling) {
            verticalVelocity = -jumpStrength;
        }
    }

    public void updateHeight(int screenHeight, int time) {
        verticalVelocity += (0.01) * time;
        if(isTouchingGround) {
            verticalVelocity = 0;
        }
        y += verticalVelocity * slowedRate;
        System.out.println("touching ground: " + isTouchingGround + "   speed: " + verticalVelocity);
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
        System.out.println("im lost at y = " + y);
        isTouchingCeiling = false;
        isTouchingGround = false;
        isTouchingLeftWall = false;
        isTouchingRightWall = false;

        slowedRate = 1;

        for(int i = tYTile; i <= bYTile; i++) {
            interactTile(i, lXTile, map);
            interactTile(i, rXTile, map);
        }

        for(int i = lXTile; i <= rXTile; i++) {
            interactTile(i, bYTile, map);
            interactTile(i, tYTile, map);
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
                break;
            default:
                break;
        }
    }

    private void moveOutOfTile(int tileXInArr, int tileYInArr) {
        int tileX = tileXInArr * Tile.tileSize;
        int tileY = tileYInArr * Tile.tileSize;

        int verticalOverlap = (int) Math.min((this.y + this.size) - tileY, (tileY + Tile.tileSize) - this.y);
        int horizontalOverlap = (int) Math.min((this.x + this.size) - tileX, (tileX + Tile.tileSize) - this.x);;

        if(verticalOverlap > 0 && horizontalOverlap > 0 && verticalOverlap < horizontalOverlap) {
            if(verticalVelocity > 0) {
                this.y = tileY - this.size;
                isTouchingGround = true;
                System.out.println("im rouching the ground");
            } else {
                this.y = tileY + Tile.tileSize;
                if(horizontalOverlap > 3) {
                    isTouchingCeiling = true;
                }
            }
        } else if(verticalOverlap > 0 && horizontalOverlap > 0) {
            if(this.x < tileX) {
                this.x = tileX - this.size;
                if(verticalOverlap > 3) {
                    isTouchingLeftWall = true;
                }
            } else {
                this.x = tileX + Tile.tileSize + 5;
                if(verticalOverlap > 3) {
                    isTouchingRightWall = true;
                }
            }
        }

    }

}
