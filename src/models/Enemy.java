package models;

import processing.core.PApplet;
import spritelib.SequencedSprite;

public class Enemy extends GameObject {

    public Enemy(PApplet applet, float x, float y, int width, int height) {
        super(applet, x, y, width, height);
    }

    /**
     Shoots a projectile from this object's position.
     @param applet the PApplet instance used for drawing
     */
    @Override
    public void draw(PApplet applet) {
        super.draw(applet);
    }

    @Override
    public void setup(PApplet applet) {

    }

    /**
     * Sets the sprite's sequence to "attack".
    */
    public void setAttackPose(){
        var seq = (SequencedSprite)this.sprite;
        seq.gotoSequence("attack");
    }

    /**
     * Sets the sprite sequence to "idle".
     */
    public void setIdle(){
        var seq = (SequencedSprite)this.sprite;
        seq.gotoSequence("idle");
    }
}
