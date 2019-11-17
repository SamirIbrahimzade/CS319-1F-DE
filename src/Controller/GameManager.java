package Controller;

import java.io.PrintWriter;
import java.util.Scanner;

import Model.*;
import javafx.scene.image.Image;

public class GameManager {

    private int score;
    //We take position, name and score for each of highscores and store 5 highscores
    private String[] highScores = new String[15];
    //GameManager(){ }

    private GCamera gCamera;
    private int level;
    private Image mapImage;
    private Bullet[] bulletList;
    private Enemy[] enemyList;
    private Player p;
    private Bonus[] bonusList;

    private void createEnemy(){}
    private void createBullet(){}
    private void checkLives(){}

    public void setgCamera(GCamera gCamera) {
        this.gCamera = gCamera;
    }

    public void setMapImage(Image mapImage) {
        this.mapImage = mapImage;
    }

    public int getScore() {
        return score;
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