package models;

import constants.TextureConstants;
import helpers.SoundHelper;
import helpers.TextureHelper;
import processing.core.PApplet;
import processing.sound.*;
import spritelib.*;

public class Player extends GameObject {
    public Player(PApplet applet, float x, float y) {
        super(applet, x, 550, 48, 48);
        this.Health = 300;
    }

    public void setup(PApplet applet){
        var seqSprite = new SequencedSprite(TextureConstants.PlayerWidth, TextureConstants.PlayerHeight, 30, ANCHORTYPE.TOP_LEFT);
        var img = TextureHelper.loadSpriteMap(applet);
        seqSprite.addFrames(
                applet,
                img,
                TextureConstants.GridGap, TextureConstants.PlayerOffsetY, 2
        );
        seqSprite.addFrames(applet, img, TextureConstants.RedEnemyOffsetX, TextureConstants.RedEnemyOffsetY, 2);
        var idleSequence = new Sequence("idle");
        idleSequence.addFrame(1);
        seqSprite.addSequence(idleSequence);

        var dieSequence = new Sequence("die");
        dieSequence.addRange(2, 5);
        seqSprite.addSequence(
                dieSequence
        );

        this.sprite = seqSprite;
    }

    @Override
    public void draw(PApplet applet) {
        sprite.draw(applet, new Point(this.getX(), this.getY()));
        if(this.Health > 0){
            ((SequencedSprite)sprite).gotoSequence("idle");
        }
        super.draw(applet);
    }

    public void removeProjectile(){
        this.projectiles.remove(0);
    }

    public void moveLeft(){
        this.velocity.x = -4;
    }

    public void moveRight(){
        this.velocity.x = 4;
    }

    @Override
    public void shoot(PApplet applet){
        if(this.projectiles.stream().anyMatch(c-> c.isVisible())){
            return;
        }
        float dx = (this.getX() + 14);
        float dy =  (this.getY()+7.0f/2);
        var projectile = new Projectile(applet, dx, dy, 10, ProjectileSource.Player);
        projectile.setup(applet);
        this.projectiles.add(projectile);
        SoundHelper.playShootSound(applet);
    }
}
