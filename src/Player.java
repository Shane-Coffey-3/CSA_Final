import java.awt.*;

public class Player {

    private double x, y, size;
    private double speed = 0.38;
    private Color color;
    private double verticalVelocity = 0;
    private double jumpStrength = (0.6 / 18) * Tile.tileSize;
    private boolean isMovingLeft, isMovingRight;
    private double slowedRate = 1;
    private boolean canJump;


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

    public void jump(Tile[][] map) {
        if(!canJump) {
            int tileLeftY = (int) (y + size + 2) / Tile.tileSize;
            int tileLeftX = (int) (x) / Tile.tileSize;
            Tile tileLeft = map[tileLeftY][tileLeftX];
            
            int tileRightY = (int) (y + size + 2) / Tile.tileSize;
            int tileRightX = (int) (x + size) / Tile.tileSize;
            Tile tileRight = map[tileRightY][tileRightX];
            
            if((tileLeft != null && tileLeft.isSolid()) || (tileRight != null && tileRight.isSolid())) {
                canJump = true;
            }
        }
        if(!isTouchingCeiling && canJump) {
            verticalVelocity = -jumpStrength;
        }
    }

    public void updateHeight(int screenHeight, int time) {
        verticalVelocity += (0.00135) * time;
        if(isTouchingGround) {
            verticalVelocity = 0;
        }
        y += verticalVelocity * slowedRate * time;
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
        isTouchingCeiling = false;
        isTouchingGround = false;
        isTouchingLeftWall = false;
        isTouchingRightWall = false;
        canJump = false;
        jumpStrength = (0.6 / 18) * Tile.tileSize;;

        slowedRate = 1;

        for(int i = tYTile; i <= bYTile; i++) {
            interactTile(lXTile, i, map);
            interactTile(rXTile, i, map);
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
            case (Tile.GROUND_TILE):
                moveOutOfTile(x, y);
                break;
            case (Tile.BORDER_TILE):
                moveOutOfTile(x, y);
                break;
            case(Tile.WATER_TILE):
                canJump = true;
                slowedRate = 0.5;
                jumpStrength = (0.4 / 18) * Tile.tileSize;;
                if(verticalVelocity > 0.25) {
                    verticalVelocity = 0.25;
                }
                break;
            case(Tile.JUMP_TILE):
                jumpStrength = (1.2 / 18) * Tile.tileSize;;
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
                canJump = true;
                isTouchingGround = true;
            } else {
                this.y = tileY + Tile.tileSize;
                verticalVelocity = 0;
                isTouchingCeiling = true;
            }
        } else if(verticalOverlap > 0 && horizontalOverlap > 0) {
            if(this.x < tileX) {
                this.x = tileX - this.size;
                isTouchingLeftWall = true;
            } else {
                this.x = tileX + Tile.tileSize + 1;
                isTouchingRightWall = true;
            }
        }

    }

}
/*

I am shane and i am smart at coding ;)
La la la la la
- Keshav
 */
