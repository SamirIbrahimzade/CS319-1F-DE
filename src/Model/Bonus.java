package Model;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Bonus extends GameObject {


    public Bonus(){

        speed = 10;
        curDirection = 1;
        try (FileInputStream inputStream = new FileInputStream("MediaFiles\\bonus.png")) {
            img = new Image(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}