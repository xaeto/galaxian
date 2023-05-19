package models;

import processing.core.PApplet;
import spritelib.MultiSprite;
import spritelib.Point;

import java.util.OptionalInt;
import java.util.OptionalLong;

public class Ship extends Enemy {
    protected OptionalLong previousTimestamp = OptionalLong.empty();
    protected MultiSprite combatSprites;
    public Ship(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(PApplet applet){
        sprite.draw(applet, new Point(this.getX(), this.getY()));
        var mSprite = ((MultiSprite)sprite);
        if(previousTimestamp.isEmpty()) {
            previousTimestamp = OptionalLong.of(System.currentTimeMillis());
        } else {
            long deltaTime = System.currentTimeMillis() - previousTimestamp.getAsLong();
            if(deltaTime % 60 == 0){
                mSprite.nextFrame();
            }
        }
    }
}
