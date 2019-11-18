package Model;

public class Player extends GameObject {
    private int lives;

    public void shoot(){

    }
    public void decreaseLife(){

    }
    public void getLives(){

    }
    public void move(int direction){

        //if direction = 0 move UP
        //if direction = 1 move DOWN
        //if direction = 2 move LEFT
        //if direction = 3 move RIGHT

        //curDirection 0 for Left
        //curDirection 1 for Right

        if(direction == 0){
            this.y += 10;
        }
        if(direction == 1){
            this.y -= 10;
        }
        if(direction == 2){
            if(this.curDirection == 1){
                //change png file
                //this.img =
            }
                this.x -= 10;

        }
        if(direction == 3){
            if(this.curDirection == 0){
                //change png file
                //this.img =
            }

            this.x += 10;
        }

    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}