package models;

import processing.core.PApplet;
import spritelib.SequencedSprite;

public class Enemy extends GameObject {

    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

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
    public void die(){
        var seq = (SequencedSprite)this.sprite;
        seq.gotoSequence("die");
        System.out.println(seq.getFrames());
    }

    public void setAttackPose(){
        var seq = (SequencedSprite)this.sprite;
        seq.gotoSequence("attack");
    }
    public void setIdle(){
        var seq = (SequencedSprite)this.sprite;
        seq.gotoSequence("idle");
    }
}
