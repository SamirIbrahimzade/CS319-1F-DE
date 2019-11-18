package Model;

public class Bullet extends GameObject {
    private boolean isEnemyBullet;
    private final int speed = 50;

    public Bullet(){

    }

    public void move(){

    }

    public void setEnemyBullet(boolean enemyBullet) {
        isEnemyBullet = enemyBullet;
    }

    public boolean isEnemyBullet() {
        return isEnemyBullet;
    }
}