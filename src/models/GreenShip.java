package models;

import processing.core.PApplet;
import spritelib.ANCHORTYPE;
import spritelib.MultiSprite;
import spritelib.Point;

import java.sql.Time;

public class GreenShip extends Enemy {
    public GreenShip(int x, int y) {
        super(x, y);
    }

    @Override
    public void setup(PApplet applet){
        sprite = new MultiSprite(15, 8, ANCHORTYPE.CENTER);
        var img = applet.loadImage("./assets/sprites.png");
        var mSprite = ((MultiSprite)sprite);
        mSprite.addFrames(
                applet,
                img,
                171, 35, 2
        );
    }
    @Override
    public void draw(PApplet applet){
        sprite.draw(applet, new Point(this.getX(), this.getY()));
        var mSprite = ((MultiSprite)sprite);
        if(((System.currentTimeMillis()) & 1) % 4 == 0){
            mSprite.nextFrame();
        }
        mSprite.nextFrame();
    }
}
