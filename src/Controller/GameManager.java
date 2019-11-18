package Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Model.*;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

public class GameManager {
    private static GameManager gm = null;

    private int enemyCount = 20;

    private int bulletCount = 80;
    private int bulletIndex = 0;

    private int bonusCount = 5;

    private int playerSafeDistance;

    private int score;
    //We take position, name and score for each of highscores and store 5 highscores
    private String[] highScores = new String[15];
    private GameManager(){
        createBonus();
        createBullet();
        createEnemy();
        createPlayer();
        createMap();

        playerSafeDistance = (mapImage.getWidth()/10);
    }

    public static GameManager getInstance(){
        if(gm == null){
            gm = new GameManager();
        }
        return gm;
    }

    private GCamera gCamera;
    private int level;
    private BufferedImage mapImage;
    private Bullet[] bulletList;
    private Enemy[] enemyList;
    private Player p;
    private Bonus[] bonusList;

    private void createEnemy(){
        enemyList = new Enemy[enemyCount];
    }
    private void createBullet(){
        bulletList = new Bullet[bulletCount];
    }

    private void createBonus(){
        bonusList = new Bonus[bonusCount];
    }

    private void createPlayer(){
        p = new Player();
    }

    private void createMap(){
        try {
            mapImage = ImageIO.read(new File("tempMap.jpg"));
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void spawnPlayer(){
        p.setX(( mapImage.getWidth()/2 ) );
        p.setY(( mapImage.getHeight()/2 ) );
        p.setActive(true);
    }

    public void spawnEnemy(){
        for(int i = 0; i < enemyCount; i++){
            enemyList[i].setX(randomXPos());
            enemyList[i].setY(randomYPos());
            enemyList[i].setActive(true);
        }
    }
    /*
    public void spawnBullet(int direction, boolean isEnemyBullet){
        if(isEnemyBullet){
            bulletList[bulletIndex].setX(this.getX());
            bulletList[bulletIndex].setY(this.getY());
        }
        else{
            bulletList[bulletIndex].setX(p.getX());
            bulletList[bulletIndex].setY(p.getY());
        }

        bulletList[bulletIndex].setCurDirection(direction);
        bulletList[bulletIndex].setActive(true);
    }

     */

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

    private void checkLives(){}

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
    /*
    private void loadHighScore(){
        private Scanner data;
        //loading file
        try {
            data = new Scanner(new File("Highscores.txt"));
        }
        //To decide what to do in case of exception
        catch (Exception e) {}
        //int for going through highscores array
        private int highArrayP = 0;
        //storing data of the file in array
        while (data.hasNext()) {
            highScores[highArrayP] = data.next();
            highArrayP++;
        }
        //closing the file
        data.close();
    }

    private void addNewHighScore(int score, String name){
        //Adding the new highscore in our array of highscores
        //We go from bottom of highscores checking for each score if our current score is greater
        //than that score or not. If it is greater we change their places, we remove last HS.
        for(i = 14; i > 0; i = i - 3) {
            if(highScores[i] < score) {
                //removing last highscore
                if (i == 14) {
                    highScores[i] = Integer.toString(score);
                    highscores[i-1] = name;
                }
                //changing positions of highscore
                else {
                    highScores[i+3] = highScores[i];
                    highScores[i+2] = highScores[i-1];
                    highScores[i] = Integer.toString(score);
                    highscores[i-1] = name;
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
    */
    private void increaseScore(int point){
        score = score + point;
    }

    private void increaseLevel(){
        level++;
    }

    private void updateMinimap(){}
}