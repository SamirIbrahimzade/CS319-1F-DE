package Model;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EnemyType2 extends Enemy {

    public EnemyType2(){

        try (FileInputStream inputStream = new FileInputStream("MediaFiles/enemyT2.png")) {
            img = new Image(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        type = 2;
    }

    public void move(int direction){
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
}
