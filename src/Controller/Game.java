package Controller;


import Model.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import static javafx.scene.input.KeyCode.*;


public class Game extends Application {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    //GCamera cam;
    //Player player;
    static GameManager gm;

    Scene mainScene, gameScene;

    Pane root;

    @Override
    public void start(Stage stage) throws Exception{
        // root = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));

        root = new Pane();

        stage.setTitle("Defender");

        Scene scene = new Scene(root);

        Label title = new Label("DEFENDER");
        title.setTextFill(Color.BLACK);
        title.setTranslateX(WIDTH / 2 - 25);
        title.setTranslateY(HEIGHT / 3);


        Button gameButton = new Button("Play Game");
        gameButton.setTranslateX(WIDTH / 2 - 25);
        gameButton.setTranslateY(HEIGHT / 3);
        gameButton.setOnAction(e -> stage.setScene(scene));


        VBox mainLayout = new VBox();

        mainLayout.getChildren().addAll(title, gameButton);

        mainScene = new Scene(mainLayout, WIDTH, HEIGHT);

        stage.setScene(mainScene);
        stage.show();
        //cam = new GCamera(0,0);
        //cam.tick(player);

        //needs to be in render method
        //Graphics g = getDrawGraphics();
        //Graphics2D g2d = g;
        //g2d.translate(cam.getX(),cam.getY());
        //handler.render(g);
        //g2d.translate(-cam.getX(),-cam.getY());

        Canvas canvas = new Canvas( 512, 512 );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                gc.drawImage(gm.getMapImage(),0,0) ;
                gc.drawImage( gm.getP().getImg(), gm.getP().getX(), gm.getP().getY() );
                gc.drawImage( gm.getP().getImg(), gm.getP().getX(), gm.getP().getY() );
                for(Enemy e: gm.getEnemyList()){
                    gc.drawImage( e.getImg(), e.getX(), e.getY() );
                }
                for(Bullet b: gm.getBulletList()){
                    gc.drawImage( b.getImg(), b.getX(), b.getY() );
                }
                for(Bonus b: gm.getBonusList()){
                    gc.drawImage( b.getImg(), b.getX(), b.getY() );
                }
            }
        }.start();

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

        // stage.setScene(scene);
        // stage.show();
    }


    public void init(){
        //cam = new GCamera(0,0);
        gm = GameManager.getInstance();

    }

    public static void main(String[] args) {

        launch(args);

    }
}