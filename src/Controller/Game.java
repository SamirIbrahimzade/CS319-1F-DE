package Controller;


import Model.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    Scene mainScene, endScene;

    Pane root;

    @Override
    public void start(Stage stage) throws Exception{
        // root = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));

        stage.setFullScreen(true);

        root = new Pane();

        stage.setTitle("Defender");

        Scene scene = new Scene(root);

        Label title = new Label("DEFENDER");
        title.setTextFill(Color.BLACK);


        Button gameButton = new Button("Play Game");
        Button scoresButton = new Button("High Scores");
        Button creditsButton = new Button("Credits");
        Button quitButton = new Button("Quit");

        gameButton.setOnAction(e -> stage.setScene(scene));


        TextField enterName = new TextField("enter name");
        Label over = new Label("GAME OVER! \n \n" + "Your Score: " + gm.getScore() + "\n \n");
        over.setTextFill(Color.BLACK);
        over.setTranslateX(WIDTH / 2 - 25);
        over.setTranslateY(HEIGHT / 3);
        enterName.setTranslateX(WIDTH / 2 - 25);
        enterName.setTranslateY(HEIGHT / 3);
        enterName.setAlignment(Pos.CENTER);
        enterName.setMaxWidth(100);
        VBox endLayout = new VBox();
        endLayout.getChildren().addAll(over, enterName);
        endScene = new Scene(endLayout, WIDTH, HEIGHT);

        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);

        mainLayout.getChildren().addAll(
                title,
                gameButton,
                scoresButton,
                creditsButton,
                quitButton
        );

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

        Canvas canvas = new Canvas( 1200, 700 );
        canvas.setHeight(stage.getHeight());
        canvas.setHeight(stage.getWidth());
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                gc.drawImage(gm.getMapImage(),0,0) ;
                if(gm.getP().isActive()){
                    gc.drawImage( gm.getP().getImg(), gm.getP().getX(), gm.getP().getY() );
                }

                for(Enemy e: gm.getEnemyList()){
                    if(e.isActive()){
                        gc.drawImage( e.getImg(), e.getX(), e.getY() );

                    }
                }
                for(Bullet b: gm.getBulletList()){
                    if(b.isActive()){
                        gc.drawImage( b.getImg(), b.getX(), b.getY() );
                    }
                }
                for(Bonus b: gm.getBonusList()){
                    if(b.isActive()){
                        gc.drawImage( b.getImg(), b.getX(), b.getY() );
                    }
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

                checkCol();

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
        gm.spawnBonus();
        gm.spawnEnemy();
        gm.spawnPlayer();

    }

    void checkCol() {

        // 1'st case
        // the player is hit with enemy bullet
        Player player = gm.getP();
        int widthPlayer = (int) player.getImg().getWidth();
        int heightPlayer = (int) player.getImg().getHeight();

        for (Bullet bullet : gm.getBulletList()) {

            // the bullet must be an enemy bullet
            if (bullet.isEnemyBullet()) {

                // the condition when the bullet location is inside the rectangle of player
                if ( Math.abs( bullet.getX() - player.getX() ) < widthPlayer / 2
                        && Math.abs( bullet.getY() - player.getY() ) < heightPlayer / 2 ) {

                    // 1) destroy the bullet
                    // 2) decrease the life of a player

                    bullet.setActive(false);
                    player.decreaseLife();
                    break;

                }
            }

        }


        // 2'nd case
        // the enemy is hit with player bullet

        for (Enemy enemy : gm.getEnemyList()) {
            int widthEnemy = (int) enemy.getImg().getWidth();
            int heightEnemy = (int) enemy.getImg().getHeight();
            for (Bullet bullet : gm.getBulletList()) {
                if (!bullet.isEnemyBullet()) {

                    // the condition when the player bullet location is inside the rectangle of enemy
                    if ( Math.abs( bullet.getX() - enemy.getX() ) < widthEnemy / 2
                            && Math.abs( bullet.getY() - player.getY() ) < heightEnemy / 2 ) {

                        // 1) destroy the player bullet
                        // 2) destroy the enemy

                        enemy.setActive(false);
                        bullet.setActive(false);

                        break;
                    }
                }
            }
        }


        // 3'rd case
        // the player collided with enemy ship

        for (Enemy enemy : gm.getEnemyList()) {

            int widthEnemy = (int) enemy.getImg().getWidth();
            int heightEnemy = (int) enemy.getImg().getHeight();

            // the condition when the enemy rectangle is inside the rectangle of player
            if ( Math.abs( enemy.getX() - player.getX() ) < (widthPlayer / 2 + widthEnemy / 2)
                    && Math.abs ( enemy.getY() - player.getY() ) < (heightPlayer / 2 + heightEnemy / 2)) {


                // 1) destroy the enemy
                // 2) decrease the player's life

                enemy.setActive(false);
                player.decreaseLife();

                break;
            }
        }


        for (Bonus bonus : gm.getBonusList()) {
            int widthBonus = (int) bonus.getImg().getWidth();
            int heightBonus = (int) bonus.getImg().getHeight();

            if ( Math.abs( bonus.getX() - player.getX() ) < (widthPlayer / 2 + widthBonus / 2)
                    && Math.abs ( bonus.getY() - player.getY() ) < (heightPlayer / 2 + heightBonus / 2)) {

                bonus.setActive(false);
                if (player.getLives() < 3) {
                    player.setLives(player.getLives() + 1);
                }

            }
        }

    }

    public static void main(String[] args) {

        launch(args);

    }
}