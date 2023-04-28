package models;

public class Player extends GameObject {
    public Player(int x, int y) {
        super(x, y);
    }

    public void moveLeft(){
        this.x -= 10;
    }
    public void moveRight(){
        this.x += 10;
    }
}
