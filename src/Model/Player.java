package Model;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Player extends GameObject {
    private int lives;


    public Player(){
        lives = 3;
        speed = 10;
        curDirection = 1;
        try (FileInputStream inputStream = new FileInputStream("MediaFiles\\spaceshipRight.png")) {
            img = new Image(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
                try (FileInputStream inputStream = new FileInputStream("MediaFiles\\spaceshipLeft.png")) {
                    img = new Image(inputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                this.x -= speed;

        }
        if(direction == 3){
            if(this.curDirection == 0){
                try (FileInputStream inputStream = new FileInputStream("MediaFiles\\spaceshipRight.png")) {
                    img = new Image(inputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }            }

            this.x += speed;
        }

    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}