package models;

import processing.core.PApplet;
import spritelib.MultiSprite;
import spritelib.Point;

public class Ship extends Enemy {
    protected MultiSprite combatSprites;
    public Ship(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(PApplet applet){
        sprite.draw(applet, new Point(this.getX(), this.getY()));
        var mSprite = ((MultiSprite)sprite);
        System.out.println(System.currentTimeMillis());
        if(((System.currentTimeMillis())) % (30) * applet.random(5) == 0){
            mSprite.nextFrame();
        }
    }
}
