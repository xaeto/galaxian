package models;

import processing.core.PApplet;
import spritelib.SequencedSprite;

public class Enemy extends GameObject {

    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     Shoots a projectile from this object's position.
     @param applet the PApplet instance used for drawing
     */
    @Override
    public void shoot(PApplet applet){
        float dx = (this.x + 3);
        float dy =  (this.y+7.0f/2);
        var projectile = new Projectile(dx, dy, 10, ProjectileSource.Enemy);
        this.projectiles.add(projectile);
    }

    @Override
    public void draw(PApplet applet) {
        super.draw(applet);
    }

    // This method is changing the sprite animation sequence of the Enemy object to "die"
    public void die(){
        var seq = (SequencedSprite)this.sprite;
        seq.gotoSequence("die");
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
