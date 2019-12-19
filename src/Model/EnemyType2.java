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
    }

    public void move(int direction){
        if(getX()>gm.getP().getX())setX(getX()-(float)0.00003);
        else setX(getX()+(float)0.00003);

        if(getY()>gm.getP().getY())setY(getY()-(float)0.00003);
        else setY(getY()+(float)0.00003);
    }
}
