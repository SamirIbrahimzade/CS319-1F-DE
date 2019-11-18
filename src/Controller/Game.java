package Controller;


import Model.GCamera;
import Model.Player;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import static javafx.scene.input.KeyCode.*;


public class Game extends Application {

    //GCamera cam;
    //Player player;


    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));
        stage.setTitle("Defender");

        Scene scene = new Scene(root, 300, 275);

        //cam = new GCamera(0,0);
        //cam.tick(player);

        //needs to be in render method
        //Graphics g = getDrawGraphics();
        //Graphics2D g2d = g;
        //g2d.translate(cam.getX(),cam.getY());
        //handler.render(g);
        //g2d.translate(-cam.getX(),-cam.getY());

        scene.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                /*
                switch (event.getCode()) {
                    case UP:    goNorth = true; break;
                    case DOWN:  goSouth = true; break;
                    case LEFT:  goWest  = true; break;
                    case RIGHT: goEast  = true; break;
                    case SHIFT: running = true; break;
                }*/
                if(event.getCode() == UP){
                    System.out.println("up");
                }
                else if(event.getCode() == DOWN){
                    System.out.println("down");
                }
                if(event.getCode() == RIGHT){
                    System.out.println("right");
                }
                else if(event.getCode() == LEFT){
                    System.out.println("left");
                }
            }
        });


        scene.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                /*
                switch (event.getCode()) {
                    case UP:    goNorth = false; break;
                    case DOWN:  goSouth = false; break;
                    case LEFT:  goWest  = false; break;
                    case RIGHT: goEast  = false; break;
                    case SHIFT: running = false; break;
                }*/
            }
        });

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {

        launch(args);

    }
}
