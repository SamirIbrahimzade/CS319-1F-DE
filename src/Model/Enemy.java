package Model;

import Controller.GameManager;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Enemy extends GameObject {

    static GameManager gm = GameManager.getInstance();


    public Enemy(){

        speed = 1;
        curDirection = 1;
        try (FileInputStream inputStream = new FileInputStream("MediaFiles/enemyImg2.png")) {
            img = new Image(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(int direction){
        //if direction = 0 move UP
        //if direction = 1 move DOWN
        //if direction = 2 move LEFT
        //if direction = 3 move RIGHT

        if(direction == 0){
            this.y += 0.0001;
        }
        if(direction == 1){
            this.y -= 0.0001;
        }
        if(direction == 2){
            this.x -= 0.0001;
        }
        if(direction == 3){
            this.x += 0.0001;
        }
    }

    public void shoot(){
        gm.getBulletList()[gm.getBulletIndex()].setCurDirection(this.curDirection);
        gm.getBulletList()[gm.getBulletIndex()].setX(this.x);
        gm.getBulletList()[gm.getBulletIndex()].setY(this.y);
        gm.getBulletList()[gm.getBulletIndex()].setEnemyBullet(true);
        gm.getBulletList()[gm.getBulletIndex()].setActive(true);
        gm.increaseBulletIndex();
    }
}