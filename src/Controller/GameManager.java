package Controller;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

import Model.*;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

public class GameManager {
    private static GameManager gm = null;

    private int enemyCount = 10;

    private int bulletCount = 80;
    private int bulletIndex = 0;

    private int bonusCount = 5;

    private int playerSafeDistance;
    private GCamera gCamera;
    private int level;
    private Image mapImage;
    private Bullet[] bulletList;
    private Enemy[] enemyList;
    private Player p;
    private Bonus[] bonusList;

    private int killScore = 500;

    private int score = 0;
    //We take position, name and score for each of highscores and store 5 highscores
    static String[] highScores = new String[15];

    public Image getMapImage() {
        return mapImage;
    }

    private GameManager(){
        createBonus();
        createBullet();
        createEnemy();
        createPlayer();
        createMap();

        playerSafeDistance = (int)(mapImage.getWidth()/10);
    }

    public static GameManager getInstance(){
        if(gm == null){
            gm = new GameManager();
        }
        return gm;
    }


    private void createEnemy(){
        enemyList = new Enemy[enemyCount];
        for(int i =0; i < enemyCount; i++){
            enemyList[i] = new Enemy();
        }
    }
    private void createBullet(){
        bulletList = new Bullet[bulletCount];
        for(int i =0; i < bulletCount; i++){
            bulletList[i] = new Bullet();
        }
    }

    private void createBonus(){
        bonusList = new Bonus[bonusCount];
        for(int i =0; i < bonusCount; i++){
            bonusList[i] = new Bonus();
        }
    }

    private void createPlayer(){
        p = new Player();
    }

    private void createMap(){
        try (FileInputStream inputStream = new FileInputStream("MediaFiles/mapImg3.png")) {
            mapImage = new Image(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void spawnPlayer(){
        p.setX((int)( mapImage.getWidth()/2 ) );
        p.setY((int)( mapImage.getHeight()/2 ) );
        p.setActive(true);
    }

    public void spawnEnemy(){
        for(int i = 0; i < enemyCount; i++){
            enemyList[i].setX(randomXPos());
            enemyList[i].setY(randomYPos());
            enemyList[i].setActive(true);
        }
    }
    public void spawnBonus(){
        for(int i = 0; i < bonusCount; i++){
            bonusList[i].setX(randomXPos());
            bonusList[i].setY(randomYPos());
            bonusList[i].setActive(true);
        }
    }

    public void increaseBulletIndex(){
        bulletIndex++;
        if(bulletIndex >= bulletCount){
            bulletIndex = 0;
        }
    }

    private int randomXPos(){
        int randX;
        randX = (int)( Math.random()*(mapImage.getWidth()));
        while (Math.abs(randX - p.getX()) < playerSafeDistance){
            randX = (int)( Math.random()*(mapImage.getWidth()));
        }
        return randX;
    }

    private int randomYPos(){
        return (int)( Math.random()*(mapImage.getHeight()));
    }

    boolean checkLives(){
        if(p.getLives() < 1) {
            return true;
        }
        return false;
    }

    public void setgCamera(GCamera gCamera) {
        this.gCamera = gCamera;
    }

    public int getScore() {
        return score;
    }

    public int getBulletIndex(){
        return bulletIndex;
    }

    public String[] getHighScores() {
        return highScores;
    }

    public GCamera getgCamera() {
        return gCamera;
    }

    public int getLevel() {
        return level;
    }

    public Bullet[] getBulletList() {
        return bulletList;
    }

    public Enemy[] getEnemyList() {
        return enemyList;
    }

    public Player getP() {
        return p;
    }

    public Bonus[] getBonusList() {
        return bonusList;
    }

    public void increaseScore(){
        score += killScore;
    }

    static void loadHighScore() throws FileNotFoundException{
        Scanner data;
        //loading file

        data = new Scanner(new File("MediaFiles/Highscores.txt"));


        int highArrayP = 0;
        while (data.hasNext()) {
            highScores[highArrayP] = data.next();
            highArrayP++;
        }
        //closing the file
        data.close();
    }
    static void addNewHighScore(int score, String name){
        //Adding the new highscore in our array of highscores
        //We go from bottom of highscores checking for each score if our current score is greater
        //than that score or not. If it is greater we change their places, we remove last HS.
        for(int i = 14; i > 0; i = i - 3) {
            if(Integer.parseInt(highScores[i]) < score) {
                //removing last highscore
                if (i == 14) {
                    highScores[i] = Integer.toString(score);
                    highScores[i-1] = name;
                }
                //changing positions of highscore
                else {
                    highScores[i+3] = highScores[i];
                    highScores[i+2] = highScores[i-1];
                    highScores[i] = Integer.toString(score);
                    highScores[i-1] = name;
                }
            }
        }
        //now writing new HS to file
        try {
            PrintWriter hs = new PrintWriter("Highscores.txt");
            for(int i = 0; i < 15; i++) {
                hs.println(highScores[i]);
            }
            hs.close();
        }
        catch (Exception e) {}
    }


    private void increaseLevel(){
        level++;
    }

    private void updateMinimap(){}
}