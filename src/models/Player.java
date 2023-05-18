package models;

import processing.core.PApplet;
import spritelib.ANCHORTYPE;
import spritelib.MultiSprite;
import spritelib.Point;
import spritelib.SingleSprite;

public class Player extends GameObject {
    public Player(int x, int y) {
        super(x, 550);
    }

    public void setup(PApplet applet){
        sprite = new MultiSprite(15, 16, ANCHORTYPE.CENTER);
        var img = applet.loadImage("./assets/sprites.png");
        ((MultiSprite)sprite).addFrames(
                applet,
                img,
                1, 70, 2
        );
    }

    @Override
    public void draw(PApplet applet) {
        sprite.draw(applet, new Point(this.getX(), this.getY()));
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
