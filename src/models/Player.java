package models;

import processing.core.PApplet;
import spritelib.Point;
import spritelib.SingleSprite;

public class Player extends GameObject {
    private SingleSprite playerSprite;

    public Player(PApplet applet, int x, int y) {
        super(x, 400);
        playerSprite = new SingleSprite(applet.loadImage("./assets/ball.png"));
    }

    @Override
    public void draw(PApplet applet) {
        playerSprite.draw(applet, new Point(this.getX(), this.getY()));
    }

    public void moveLeft(){
        this.x -= 10;
    }
    public void moveRight(){
        this.x += 10;
    }
    public void fireProjectile(){

    }
}
