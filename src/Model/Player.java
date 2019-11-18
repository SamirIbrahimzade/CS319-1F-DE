package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends GameObject {
    private int lives;


    public Player(){
        lives = 3;
        speed = 10;
        curDirection = 1;
        String imagePath = "spaceshipRight.png";
        try {
            img = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            this.y += speed;
        }
        if(direction == 1){
            this.y -= speed;
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
                this.x -= speed;

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

            this.x += speed;
        }

    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}