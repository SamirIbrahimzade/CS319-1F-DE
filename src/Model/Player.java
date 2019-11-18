package Model;

import Controller.GameManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends GameObject {
    private int lives;
    final private int SPEED = 10;
    static GameManager gm = GameManager.getInstance();

    public void shoot(){

    }
    public void decreaseLife(){

    }
    public void getLives(){

    }
    public void move(int direction){

        //if direction = 0 move UP
        //if direction = 1 move DOWN
        //if direction = 2 move LEFT
        //if direction = 3 move RIGHT

        //curDirection 0 for Left
        //curDirection 1 for Right

        if(direction == 0){
            this.y += SPEED;
        }
        if(direction == 1){
            this.y -= SPEED;
        }
        if(direction == 2){
            if(this.curDirection == 1){
                String imagePath = "spaceshipLeft.png";
                try {
                    this.img = ImageIO.read(new File(imagePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                this.x -= SPEED;

        }
        if(direction == 3){
            if(this.curDirection == 0){
                String imagePath = "spaceshipRight.png";
                try {
                    this.img = ImageIO.read(new File(imagePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            this.x += SPEED;
        }

    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}