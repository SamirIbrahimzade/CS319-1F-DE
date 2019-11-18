package Model;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public abstract class GameObject {

    public int x = 0;
    public int y;
    public int curDirection;
    public int speed;
    public BufferedImage img;

    public void setCurDirection(int curDirection) {
        this.curDirection = curDirection;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getCurDirection() {
        return curDirection;
    }

    public int getSpeed() {
        return speed;
    }

    public BufferedImage getImg() {
        return img;
    }
}