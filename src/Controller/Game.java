package Controller;


import Model.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.util.ArrayList;

import static javafx.scene.input.KeyCode.*;


public class Game extends Application {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    GCamera cam;
    //Player player;
    static GameManager gm;

    Scene mainScene, creditsScene, scene, endScene,highScene;

    Pane root;

    @Override
    public void start(Stage stage) throws Exception{
        // root = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));

        root = new Pane();


        stage.setWidth(1024);
        stage.setHeight(576);
        //stage.setMaximized(true);
        Scene scene = new Scene(root);

        stage.setTitle("Defender");

////////////////////////////////////////////////////////////////////////////////////////

        Label title = new Label("DEFENDER");
        title.setTextFill(Color.BLACK);
        title.setPadding(new Insets(150));

        final double MAX_FONT_SIZE = 50.0;
        title.setFont(new Font(MAX_FONT_SIZE));

        Button gameButton = new Button("Play Game");
        Button scoresButton = new Button("High Scores");
        Button creditsButton = new Button("Credits");
        Button quitButton = new Button("Quit");

        // gameButton.setOnAction(e -> stage.setScene(scene));
        // creditsButton.setOnAction(e -> stage.setScene(creditsScene));

        scoresButton.setOnAction(e->stage.setScene(highScene));
        Button goMenu = new Button("Go to Main Menu");

        gameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(scene);
                // stage.setMaximized(true);
            }
        });



        VBox mainLayout = new VBox(10);

        mainLayout.setAlignment(Pos.TOP_CENTER);
        //added
        title.setTextFill(Color.WHITE);
        //1300 720
        mainLayout.setStyle("-fx-background-image: url(file:MediaFiles/background.jpg);");
        mainLayout.getChildren().addAll(
                title,
                gameButton,
                scoresButton,
                creditsButton,
                quitButton
        );

        mainScene = new Scene(mainLayout);

        stage.setScene(mainScene);
        stage.show();

////////////////////////////////////////////////////////////////////////////////////////


        Label person1 = new Label("Samir Ibrahimzade");
        Label person2 = new Label("Abdullah Ayberk Gorgun");
        Label person3 = new Label("Taner Durmaz");
        Label person4 = new Label("Hassan ");
        Label person5 = new Label("Samir Ibrahimzade");
        person1.setTextFill(Color.WHITE);
        person1.setFont(Font.font(20));
        person2.setTextFill(Color.WHITE);
        person2.setFont(Font.font(20));
        person3.setTextFill(Color.WHITE);
        person3.setFont(Font.font(20));
        person4.setTextFill(Color.WHITE);
        person4.setFont(Font.font(20));
        person5.setTextFill(Color.WHITE);
        person5.setFont(Font.font(20));

        VBox creditsLayout = new VBox(10);
        //added
        creditsLayout.setStyle("-fx-background-image: url(file:MediaFiles/background.jpg);");


        creditsScene = new Scene(creditsLayout,1024,576);




        quitButton.setOnAction(e -> Platform.exit());

        creditsButton.setOnAction(e -> stage.setScene(creditsScene));

////////////////////////////////////////////////////////////////////////////////////////

        TextField enterName = new TextField();
        Label over = new Label("GAME OVER! \n \n" + "Your Score: " + gm.getScore() + "\n \nenter name: ");
        over.setTextFill(Color.WHITE);
        over.setFont(Font.font(20));
        over.setTranslateX(WIDTH / 2 - 25);
        over.setTranslateY(HEIGHT / 3);
        enterName.setTranslateX(WIDTH / 2 - 25);
        enterName.setTranslateY(HEIGHT / 3);
        enterName.setAlignment(Pos.CENTER);
        enterName.setMaxWidth(100);
        Button enterButton = new Button("ENTER");
        enterButton.setTranslateX(WIDTH / 2 - 25);
        enterButton.setTranslateY(HEIGHT / 3);
        enterButton.setOnAction(e -> stage.setScene(highScene));
        VBox endLayout = new VBox();
        //added
        endLayout.setStyle("-fx-background-image: url(file:MediaFiles/background.jpg);");
        endLayout.getChildren().addAll(over, enterName, enterButton);
        endScene = new Scene(endLayout, WIDTH, HEIGHT);
///////////////////////////////////////////////////////

        gm.loadHighScore();
        String[] high = gm.getHighScores();
        Label scores1 = new Label("HIGH SCORES \n \n" + high[0]+" "+high[1]+" "+high[2]);
        Label scores2 = new Label(high[3]+" "+high[4]+" "+high[5]+"\n"+high[6]+" "+high[7]+" "+high[8]);
        Label scores3 = new Label(high[9]+" "+high[10]+" "+high[11]+"\n"+high[12]+" "+high[13]+" "+high[14]);
        scores1.setTranslateX(WIDTH / 2 - 25);
        scores1.setTranslateY(HEIGHT / 3);
        scores2.setTranslateX(WIDTH / 2 - 25);
        scores2.setTranslateY(HEIGHT / 3);
        scores3.setTranslateX(WIDTH / 2 - 25);
        scores3.setTranslateY(HEIGHT / 3);
        scores1.setTextFill(Color.WHITE);
        scores1.setFont(Font.font(20));
        scores2.setTextFill(Color.WHITE);
        scores2.setFont(Font.font(20));
        scores3.setTextFill(Color.WHITE);
        scores3.setFont(Font.font(20));
        Button menuButton = new Button("Go To Main Menu");
        menuButton.setTranslateX(WIDTH / 2 - 25);
        menuButton.setTranslateY(HEIGHT / 3);
        menuButton.setOnAction(e -> stage.setScene(mainScene));
        VBox highLayout = new VBox();
        //added
        highLayout.setStyle("-fx-background-image: url(file:MediaFiles/background.jpg);");
        highLayout.getChildren().addAll(scores1, scores2, scores3, menuButton);
        highScene = new Scene(highLayout, WIDTH, HEIGHT);

        Button menuButtonForCredits = new Button("Go To Main Menu");

        menuButtonForCredits.setOnAction(e -> stage.setScene(mainScene));

        //creditsLayout.setAlignment();
        creditsLayout.setAlignment(Pos.CENTER);
        creditsLayout.getChildren().addAll(
                person1,
                person2,
                person3,
                person4,
                person5,
                menuButtonForCredits
        );

////////////////////////////////////////////////////////////////////////////////////////


        cam = new GCamera(0,0);
        cam.tick(gm.getP());

        //needs to be in render method
        //Graphics g = getDrawGraphics();
        //Graphics2D g2d = g;
        //g2d.translate(cam.getX(),cam.getY());
        //handler.render(g);
        //g2d.translate(-cam.getX(),-cam.getY());

        Canvas canvas = new Canvas( 1920, 1080 );



        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();



        gc.translate(-750,0);

        gc.scale(2,1);

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {


                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                //enemy move and shoot
                for(Enemy e: gm.getEnemyList()){
                    if(e.isActive()){
                        int max = 4;
                        int min = 0;
                        int range = max - min + 1;
                        int rand = (int)(Math.random() * range) + min;
                        for(int i = 0; i < 70000; i++){
                            e.move(rand);
                        }
                        e.shoot();
                    }
                }
                //bullet move
                for(Bullet b: gm.getBulletList()){
                    if(b.isActive()){
                        for(int i = 0; i < 5; i++){
                            b.move(b.getCurDirection());
                        }
                    }
                }


                if(gm.getP().getCurDirection() == 1){
                    gc.translate(-1,0);
                    gm.getP().move(3,1);
                }

                else {
                    gc.translate(1, 0);
                    gm.getP().move(2,1);
                }


                //gc.translate(100,100);

                //gc.translate(-cam.getX(),-cam.getY());

                gc.drawImage(gm.getMapImage(),0,0) ;
                if(gm.getP().isActive()){
                    gc.drawImage( gm.getP().getImg(), gm.getP().getX(), gm.getP().getY());

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

                //enemy move and shoot
                for(Enemy e: gm.getEnemyList()){
                    int max = 3;
                    int min = 0;
                    int range = max - min + 1;
                    for(int i = 0; i < 5; i++){
                        e.move((int)(Math.random() * range) + min);
                    }
                    e.shoot();
                }

                checkCol();

                if(gm.checkLives()){
                    stage.setScene(endScene);
                    stage.show();
                }



                if(event.getCode() == P){//P ?
                    System.out.println("pause");

                }
                else{
                    if(event.getCode() == UP){
                        System.out.println("up");
                        gm.getP().move(0,10);
                    }
                    if(event.getCode() == DOWN){
                        System.out.println("down");
                        gm.getP().move(1,10);
                    }
                    if(event.getCode() == RIGHT){
                     //   if(gm.getP().getCurDirection() == 0)
                            gc.translate(-10,0);
                        System.out.println("right");
                        gm.getP().move(3,10);
                        gm.getP().setCurDirection(1);
                    }
                    if(event.getCode() == LEFT){
                     //   if(gm.getP().getCurDirection() == 1)
                            gc.translate(10,0);
                        System.out.println("left");
                        gm.getP().move(2,10);
                        gm.getP().setCurDirection(0);
                    }
                    if(event.getCode() == SPACE){

                        gm.getBulletList()[gm.getBulletIndex()].setCurDirection(gm.getP().curDirection);
                        gm.getBulletList()[gm.getBulletIndex()].setX(gm.getP().x+47);
                        gm.getBulletList()[gm.getBulletIndex()].setY(gm.getP().y+33);
                        gm.getBulletList()[gm.getBulletIndex()].setEnemyBullet(false);
                        gm.getBulletList()[gm.getBulletIndex()].setActive(true);
                        gm.increaseBulletIndex();
                        System.out.println(gm.getBulletIndex() + " is fired");
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


        // stage.setScene(scene);
        // stage.show();
    }


    public void init(){
        //cam = new GCamera(0,0);
        gm = GameManager.getInstance();
        gm.spawnBonus();

        gm.spawnPlayer();
        gm.spawnEnemy();

    }

    void checkCol() {

        // 1'st case
        // the player is hit with enemy bullet
        Player player = gm.getP();
        int widthPlayer = (int) player.getImg().getWidth();
        int heightPlayer = (int) player.getImg().getHeight();

        for (Bullet bullet : gm.getBulletList()) {

            // the bullet must be an enemy bullet
            if (bullet.isEnemyBullet() && bullet.isActive()) {

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
            if (enemy.isActive()) {
                int widthEnemy = (int) enemy.getImg().getWidth();
                int heightEnemy = (int) enemy.getImg().getHeight();
                for (Bullet bullet : gm.getBulletList()) {
                    if (!bullet.isEnemyBullet() && bullet.isActive()) {
                        //System.out.println("enemy fired");
                        // the condition when the player bullet location is inside the rectangle of enemy
                        if (Math.abs(bullet.getX() - enemy.getX()) < widthEnemy / 2
                                && Math.abs(bullet.getY() - player.getY()) < heightEnemy / 2) {

                            // 1) destroy the player bullet
                            // 2) destroy the enemy
                            System.out.println("COLLISION");

                            gm.increaseScore();
                            enemy.setActive(false);
                            bullet.setActive(false);

                            break;
                        }
                    }
                }
            }
        }


        // 3'rd case
        // the player collided with enemy ship

        for (Enemy enemy : gm.getEnemyList()) {

            if (enemy.isActive()) {

                int widthEnemy = (int) enemy.getImg().getWidth();
                int heightEnemy = (int) enemy.getImg().getHeight();

                // the condition when the enemy rectangle is inside the rectangle of player
                if (Math.abs(enemy.getX() - player.getX()) < (widthPlayer / 2 + widthEnemy / 2)
                        && Math.abs(enemy.getY() - player.getY()) < (heightPlayer / 2 + heightEnemy / 2)) {


                    // 1) destroy the enemy
                    // 2) decrease the player's life

                    System.out.println("Collapse with enemy");
                    enemy.setActive(false);
                    player.decreaseLife();

                    break;
                }
            }
        }


        for (Bonus bonus : gm.getBonusList()) {
            if (bonus.isActive()) {
                int widthBonus = (int) bonus.getImg().getWidth();
                int heightBonus = (int) bonus.getImg().getHeight();

                if (Math.abs(bonus.getX() - player.getX()) < (widthPlayer / 2 + widthBonus / 2)
                        && Math.abs(bonus.getY() - player.getY()) < (heightPlayer / 2 + heightBonus / 2)) {

                    bonus.setActive(false);
                    if (player.getLives() < 3) {
                        player.setLives(player.getLives() + 1);
                    }

                }
            }
        }

    }
    public static void main(String[] args) {

        launch(args);

    }
}