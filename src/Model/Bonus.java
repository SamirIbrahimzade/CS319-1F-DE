package Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Bonus extends GameObject {


    public Bonus(){

        speed = 10;
        curDirection = 1;
        String imagePath = "bonus.png";
        try {
            img = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}