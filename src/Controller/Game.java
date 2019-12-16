package Controller;
import Model.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.FileNotFoundException;


import static javafx.scene.input.KeyCode.*;

public class Game extends Application {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    int pauser = 1, newGame = 0, destroyedEnemy = 0;
    int onlyOnce = 0;
    float endPosition = 250;
    private GCamera cam;
    static GameManager gm;

    Scene mainScene, creditsScene, scene, endScene,highScene,pauseScene,levelScene;
    Pane root;

    @Override
    public void start(Stage stage) throws Exception{
        //gui game window
        root = new Pane();
        gm = new GameManager();
        Canvas canvas = new Canvas( 1920, 1080 );
        root.getChildren().add( canvas );
        GraphicsContext gc = canvas.getGraphicsContext2D();
        stage.setWidth(1024);
        stage.setHeight(576);
        Scene scene = new Scene(root);
        stage.setTitle("Defender");
        Label title = new Label("DEFENDER");
        title.setTextFill(Color.BLACK);
        title.setPadding(new Insets(150));

        final double MAX_FONT_SIZE = 50.0;
        title.setFont(new Font(MAX_FONT_SIZE));

        Button gameButton = new Button("Play Game");
        Button scoresButton = new Button("High Scores");
        Button creditsButton = new Button("Credits");
        Button quitButton = new Button("Quit");
        scoresButton.setOnAction(e->stage.setScene(highScene));
        Button goMenu = new Button("Go to Main Menu");

        gameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pauser = 0;
                gm = new GameManager();
                gm.spawnBonus();
                gm.spawnPlayer();
                gm.spawnEnemy();
                gc.translate((endPosition-250)-300,0);
                System.out.println(endPosition + "    " + ((endPosition-250)-300) );
                newGame = 1;
                stage.setScene(scene);
                pauser = 0;
            }
        });

        VBox mainLayout = new VBox(10);
        mainLayout.setAlignment(Pos.TOP_CENTER);
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
        creditsLayout.setStyle("-fx-background-image: url(file:MediaFiles/background.jpg);");
        creditsScene = new Scene(creditsLayout,1024,576);
        quitButton.setOnAction(e -> Platform.exit());
        creditsButton.setOnAction(e -> stage.setScene(creditsScene));


        gm.loadHighScore();
        String[] high = gm.getHighScores();
        Label scores1 = new Label("HIGH SCORES \n \n\n\n");
        Label scores2 = new Label(high[0]+" "+high[1]+" "+high[2]+"\n"+high[3]+" "+high[4]+" "+high[5]+"\n"+high[6]+" "+high[7]+" "+high[8]);
        Label scores3 = new Label(high[9]+" "+high[10]+" "+high[11]+"\n"+high[12]+" "+high[13]+" "+high[14]);
        scores1.setTextFill(Color.WHITE);
        scores1.setFont(Font.font(20));
        scores2.setTextFill(Color.WHITE);
        scores2.setFont(Font.font(20));
        scores3.setTextFill(Color.WHITE);
        scores3.setFont(Font.font(20));
        Button menuButton = new Button("Go To Main Menu");
        menuButton.setOnAction(e -> stage.setScene(mainScene));
        VBox highLayout = new VBox(10);
        //added
        highLayout.setStyle("-fx-background-image: url(file:MediaFiles/background.jpg);");
        highLayout.getChildren().addAll(scores1, scores2, scores3, menuButton);
        highScene = new Scene(highLayout, WIDTH, HEIGHT);
        highLayout.setAlignment(Pos.CENTER);
        Button menuButtonForCredits = new Button("Go To Main Menu");

        menuButtonForCredits.setOnAction(e -> stage.setScene(mainScene));
        creditsLayout.setAlignment(Pos.CENTER);
        creditsLayout.getChildren().addAll(
                person1,
                person2,
                person3,
                person4,
                person5,
                menuButtonForCredits
        );


        gc.scale(1.5,1);

        cam = new GCamera(0,0);
        cam.tick(gm.getP());

        //needs to be in render method
        //Graphics g = getDrawGraphics();
        //Graphics2D g2d = g;
        //g2d.translate(cam.getX(),cam.getY());
        //handler.render(g);
        //g2d.translate(-cam.getX(),-cam.getY());

        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	if (pauser == 0) {
                    if (destroyedEnemy % 3 == 0 && destroyedEnemy != 0 && onlyOnce == 0) {
                        System.out.println("destroyed enemy " + destroyedEnemy);
                        System.out.println("Level " + destroyedEnemy / 3 + " passed!");
                        gm.setKillScore(((destroyedEnemy % 3) + 1) * 500);
                        onlyOnce = 1;
                        pauser = 1;
                        Label level = new Label("Level " + destroyedEnemy / 3 + " passed! \n");
                        level.setTextFill(Color.WHITE);
                        Button resumeButton = new Button("Continue");
                        VBox levelLayout = new VBox();
                        endPosition = gm.getP().getX();
                        levelLayout.setStyle("-fx-background-image: url(file:MediaFiles/background.jpg);");
                        levelLayout.getChildren().addAll(level, resumeButton);
                        levelLayout.setAlignment(Pos.CENTER);
                        levelScene = new Scene(levelLayout, WIDTH, HEIGHT);
                        stage.setScene(levelScene);
                        resumeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                stage.setScene(scene);
                                pauser = 0;
                            }
                        });
                    }
                    checkCol();
                    double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                    //enemy move and shoot
                    for (Enemy e : gm.getEnemyList()) {
                        if (e.isActive()) {
                            int max = 4;
                            int min = 0;
                            int range = max - min + 1;
                            int rand = (int) (Math.random() * range) + min;
                            for (int i = 0; i < 70000; i++) {
                                e.move(rand);
                            }
                            e.shoot();
                        }
                    }
                    //bullet move
                    for (Bullet b : gm.getBulletList()) {
                        if (b.isActive()) {
                            for (int i = 0; i < 5; i++) {
                                b.move(b.getCurDirection());
                            }
                        }
                    }

                    if (gm.getP().getCurDirection() == 1) {
                        gc.translate(-1, 0);
                        gm.getP().move(3, 1);
                    } else {
                        gc.translate(1, 0);
                        gm.getP().move(2, 1);
                    }

                    gc.drawImage(gm.getMapImage(), 0, 0);
                    if (gm.getP().isActive()) {
                        gc.drawImage(gm.getP().getImg(), gm.getP().getX(), gm.getP().getY());
                    }

                    for (Enemy e : gm.getEnemyList()) {
                        if (e.isActive()) {
                            gc.drawImage(e.getImg(), e.getX(), e.getY());

                        }
                    }

                    for (Bullet b : gm.getBulletList()) {
                        if (b.isActive()) {
                            gc.drawImage(b.getImg(), b.getX(), b.getY());
                        }
                    }

                    for (Bonus b : gm.getBonusList()) {
                        if (b.isActive()) {
                            gc.drawImage(b.getImg(), b.getX(), b.getY());
                        }
                    }
                    if (gm.checkLives()) {
                        pauser = 1;
                        endPosition = gm.getP().getX();
                        TextField enterName = new TextField();
                        Label over = new Label("GAME OVER! \n \n" + "Your Score: " + gm.getScore() + "\n \nenter name: ");
                        over.setTextFill(Color.WHITE);
                        over.setFont(Font.font(20));
                        enterName.setAlignment(Pos.CENTER);
                        enterName.setMaxWidth(100);
                        Button enterButton = new Button("ENTER");
                        enterButton.setOnAction(e -> {
                            try {
                                high(enterName.getText(), gm.getScore(), stage);
                            } catch (FileNotFoundException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        });
                        VBox endLayout = new VBox();
                        endLayout.setStyle("-fx-background-image: url(file:MediaFiles/background.jpg);");
                        endLayout.getChildren().addAll(over, enterName, enterButton);
                        endScene = new Scene(endLayout, WIDTH, HEIGHT);
                        endLayout.setAlignment(Pos.CENTER);

                        stage.setScene(endScene);
                        stage.show();
                    }
                }
            }
        }.start();


        scene.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {

                if(event.getCode() == P){//P ?
                	Label pause = new Label("PAUSE \n");
                    Button homeButton = new Button("EXIT");
                    Button resumeButton = new Button("RESUME");
                    VBox pauseLayout = new VBox();
                    homeButton.setOnAction(e -> end(stage));
                    endPosition = gm.getP().getX();
                    pauseLayout.setStyle("-fx-background-image: url(file:MediaFiles/background.jpg);");
                    pauseLayout.getChildren().addAll(pause,homeButton,resumeButton);
                    pauseLayout.setAlignment(Pos.CENTER);
                    pauseScene = new Scene(pauseLayout, WIDTH,HEIGHT);
                    stage.setScene(pauseScene);
                    pauser = 1;

                    resumeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            stage.setScene(scene);
                            pauser = 0;
                        }
                    });
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
                        // if(gm.getP().getCurDirection() == 0)
                        gc.translate(-10,0);
                        System.out.println("right");
                        gm.getP().move(3,10);
                        gm.getP().setCurDirection(1);
                    }
                    if(event.getCode() == LEFT){
                        // if(gm.getP().getCurDirection() == 1)
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
    }

    public void end(Stage stage) {
    	//pauser = 0;
    	stage.setScene(mainScene);
    }
    public void high(String nam, int sco, Stage stage) throws FileNotFoundException {
    	gm.addNewHighScore(sco, nam);
    	gm.loadHighScore();
        String[] high = gm.getHighScores();
        Label scores1 = new Label("HIGH SCORES \n\n\n \n"  );
        Label scores2 = new Label(high[0]+" "+high[1]+" "+high[2]+"\n"+high[3]+" "+high[4]+" "+high[5]+"\n"+high[6]+" "+high[7]+" "+high[8]);
        Label scores3 = new Label(high[9]+" "+high[10]+" "+high[11]+"\n"+high[12]+" "+high[13]+" "+high[14]);
        scores1.setTextFill(Color.WHITE);
        scores1.setFont(Font.font(20));
        scores2.setTextFill(Color.WHITE);
        scores2.setFont(Font.font(20));
        scores3.setTextFill(Color.WHITE);
        scores3.setFont(Font.font(20));
        Button menuButton = new Button("Go To Main Menu");
        menuButton.setOnAction(e -> stage.setScene(mainScene));
        VBox highLayout = new VBox(10);
        highLayout.setStyle("-fx-background-image: url(file:MediaFiles/background.jpg);");
        highLayout.getChildren().addAll(scores1, scores2, scores3, menuButton);
        highScene = new Scene(highLayout, 1024, 576);

        Button menuButtonForCredits = new Button("Go To Main Menu");

        menuButtonForCredits.setOnAction(e -> stage.setScene(mainScene));

        highLayout.setAlignment(Pos.CENTER);
    	stage.setScene(highScene);
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
                        // the condition when the player bullet location is inside the rectangle of enemy
                        if (Math.abs(bullet.getX() - enemy.getX()) < widthEnemy / 2
                                && Math.abs(bullet.getY() - player.getY()) < heightEnemy / 2) {
                            // 1) destroy the player bullet
                            // 2) destroy the enemy
                            if(destroyedEnemy %3 == 0 && destroyedEnemy != 0)onlyOnce = 0;
                            destroyedEnemy++;
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
/*
    public void init(){
        //cam = new GCamera(0,0);
        gm = GameManager.getInstance();
        gm.spawnBonus();

        gm.spawnPlayer();
        gm.spawnEnemy();

    }
*/
////////////////////////////////////////////////////////////////////////////////////////

//        TextField enterName = new TextField();
//        Label over = new Label("GAME OVER! \n \n" + "Your Score: " + gm.getScore() + "\n \nenter name: ");
//        over.setTextFill(Color.WHITE);
//        over.setFont(Font.font(20));
//        enterName.setAlignment(Pos.CENTER);
//        enterName.setMaxWidth(100);
//        Button enterButton = new Button("ENTER");
//        enterButton.setOnAction(e -> stage.setScene(highScene));
//        VBox endLayout = new VBox();
//added
//        endLayout.setStyle("-fx-background-image: url(file:MediaFiles/background.jpg);");
//        endLayout.getChildren().addAll(over, enterName, enterButton);
//        endScene = new Scene(endLayout, WIDTH, HEIGHT);
//      endLayout.setAlignment(Pos.CENTER);
///////////////////////////////////////////////////////