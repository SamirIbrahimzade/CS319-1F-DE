package Model;

import Controller.GameManager;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Player extends GameObject {
    private int lives;
    static GameManager gm = GameManager.getInstance();


    public Player(){
        lives = 3;
        speed = 10;
        curDirection = 1;
        try (FileInputStream inputStream = new FileInputStream("MediaFiles/spaceshipRight2.png")) {
            img = new Image(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void shoot(){

        gm.getBulletList()[gm.getBulletIndex()].setCurDirection(this.curDirection);
        gm.getBulletList()[gm.getBulletIndex()].setX(this.x);
        gm.getBulletList()[gm.getBulletIndex()].setY(this.y);
        gm.getBulletList()[gm.getBulletIndex()].setEnemyBullet(false);
        gm.getBulletList()[gm.getBulletIndex()].setActive(true);
        gm.increaseBulletIndex();

    }

    public void decreaseLife(){
        lives = lives - 1;
    }

    public int getLives(){
        return lives;
    }

    public void move(int direction){

        //if direction = 0 move UP
        //if direction = 1 move DOWN
        //if direction = 2 move LEFT
        //if direction = 3 move RIGHT

        //curDirection 0 for Left
        //curDirection 1 for Right

        if(direction == 0){
            this.y -= speed;
        }
        if(direction == 1){
            this.y += speed;
        }
        if(direction == 2){
            if(this.curDirection == 1){
                try (FileInputStream inputStream = new FileInputStream("MediaFiles/spaceshipLeft2.png")) {
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
                try (FileInputStream inputStream = new FileInputStream("MediaFiles/spaceshipRight2.png")) {
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