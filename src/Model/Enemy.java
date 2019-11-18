package Model;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Enemy extends GameObject {



    public Enemy(){

        speed = 10;
        curDirection = 1;
        try (FileInputStream inputStream = new FileInputStream("MediaFiles\\enemyImg.png")) {
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
            this.y += speed;
        }
        if(direction == 1){
            this.y -= speed;
        }
        if(direction == 2){
            this.x -= speed;
        }
        if(direction == 3){
            this.x += speed;
        }
    }

    public void shoot(){

    }
}