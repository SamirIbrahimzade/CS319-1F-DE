package Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Enemy extends GameObject {

    final private int SPEED = 10;

    public void move(int direction){
        //if direction = 0 move UP
        //if direction = 1 move DOWN
        //if direction = 2 move LEFT
        //if direction = 3 move RIGHT

        if(direction == 0){
            this.y += SPEED;
        }
        if(direction == 1){
            this.y -= SPEED;
        }
        if(direction == 2){
            this.x -= SPEED;
        }
        if(direction == 3){
            this.x += SPEED;
        }
    }

    public void shoot(){

    }
}