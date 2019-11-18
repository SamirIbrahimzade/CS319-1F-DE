package Model;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Bullet extends GameObject {
    private boolean isEnemyBullet;
    private final int speed = 1;

    public Bullet(){
        try (FileInputStream inputStream = new FileInputStream("MediaFiles/bullet.png")) {
            img = new Image(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(int direction){

        if(direction == 0){
            this.x += speed;
        }
        if(direction == 1){
            this.x -= speed;
        }
    }

    public void setEnemyBullet(boolean enemyBullet) {
        isEnemyBullet = enemyBullet;
    }

    public boolean isEnemyBullet() {
        return isEnemyBullet;
    }
}