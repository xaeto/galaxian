package models;

import processing.core.PApplet;
import spritelib.MultiSprite;
import spritelib.SequencedSprite;
import spritelib.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends GameObject {

    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public boolean isAlive(){
        return Health > 0;
    }

    @Override
    public void shoot(PApplet applet){
        float dx = (this.x + 3);
        float dy =  (this.y+7.0f/2);
        var projectile = new Projectile(dx, dy, 1, ProjectileSource.Enemy);
        this.projectiles.add(projectile);
    }

    public void die(){
        var seq = (SequencedSprite)this.sprite;
        seq.gotoSequence("die");
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
