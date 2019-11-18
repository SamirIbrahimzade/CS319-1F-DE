package Controller;


import Model.Enemy;
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
    GameManager gm;


    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));
        stage.setTitle("Defender");

        init();
        Scene scene = new Scene(root, 300, 275);


        //cam.tick(gm.getP());

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
                if(event.getCode() == P){//P ?
                    System.out.println("pause");

                }
                else{
                    if(event.getCode() == UP){
                        System.out.println("up");
                        gm.getP().move(0);
                    }
                    else if(event.getCode() == DOWN){
                        System.out.println("down");
                        gm.getP().move(1);
                    }
                    if(event.getCode() == RIGHT){
                        System.out.println("right");
                        gm.getP().move(3);
                    }
                    else if(event.getCode() == LEFT){
                        System.out.println("left");
                        gm.getP().move(2);
                    }
                    if(event.getCode() == SPACE){
                        System.out.println("player.shoot");
                    }
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
        //enemy move and shoot
        for(Enemy e: gm.getEnemyList()){
            for(int i = 0; i < 5;i++){
                e.move((int)( Math.random()*4 -1));
            }
            e.shoot();
        }

        stage.setScene(scene);
        stage.show();
    }

    public void init(){
        //cam = new GCamera(0,0);
        gm = GameManager.getInstance();

    }

    public static void main(String[] args) {

        launch(args);

    }
}
