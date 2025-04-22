import javax.swing.*;
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

    public void draw(Graphics g, Dimension screenSize) {
        updateHeight(screenSize.height);

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

   /* public boolean isTouchingSquare() {
        int playerCenterX = x + (size / 2);
        int playerCentery = y + (size / 2);
        int playerCenterX = x + (size / 2);
        int playerCenterY = x + (size / 2);
        if(true) {
        //    return 1;
        }
    }*/
}
