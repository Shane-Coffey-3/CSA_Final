import javax.swing.*;
import java.awt.*;

public class Player {

    private int x, y, size;
    private Color color;
    double verticalVelocity = 0;
    double timeInAir = 0;
    double jumpStrength = 20;

    public Player(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public void draw(Graphics g) {
        updateHeight();

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

    public void moveLeft() {
        setX(getX() - 5);
    }

    public void moveRight() {
        setX(getX() + 5);
    }

    public void moveUp() {
        setY(getY() - 5);
    }

    public void moveDown() {
        setY(getY() + 5);
    }

    public void jump() {
        timeInAir = 0;
        verticalVelocity = -jumpStrength;
    }

    public void updateHeight() {
        timeInAir += 0.1;
        verticalVelocity++;
        y += verticalVelocity;
    }
}
