package Model;

import javafx.scene.image.Image;



public abstract class GameObject {

    public int x = 0;
    public int y;
    public int curDirection;
    public int speed;
    public Image img;
    public boolean active;

    GameObject(){
        active = false;
    }

    public void setCurDirection(int curDirection) {
        this.curDirection = curDirection;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setImg(Image img) {
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

    public Image getImg() {
        return img;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean value){
        active = value;
    }
}