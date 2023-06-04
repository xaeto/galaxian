package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import processing.core.PApplet;
import spritelib.ANCHORTYPE;
import spritelib.MultiSprite;
import spritelib.Point;

import java.util.OptionalLong;

public class Player extends GameObject {
    public Player(float x, float y) {
        super(x, 550, 48, 48);
    }

    public void setup(PApplet applet){
        sprite = new MultiSprite(TextureConstants.PlayerWidth, TextureConstants.PlayerHeight, ANCHORTYPE.TOP_LEFT);
        var img = TextureHelper.loadSpriteMap(applet);
        ((MultiSprite)sprite).addFrames(
                applet,
                img,
                TextureConstants.GridGap, TextureConstants.PlayerOffsetY, 2
        );
    }

    @Override
    public void draw(PApplet applet) {
        sprite.draw(applet, new Point(this.getX(), this.getY()));
        super.draw(applet);
    }

    public void removeProjectile(){
        this.projectiles.remove(0);
    }

    public void moveLeft(){
        this.x -= 5;
        this._direction -= 0.02*Math.PI;
    }

    public void moveRight(){
        this._direction += 0.02*Math.PI;
        this.x += 5;
    }

    @Override
    public void shoot(PApplet applet){
        if(this.projectiles.stream().anyMatch(c-> c.isVisible())){
            return;
        }
        float dx = (this.x + 14);
        float dy =  (this.y+7.0f/2);
        var projectile = new Projectile(dx, dy, 2, ProjectileSource.Player);
        projectile.setup(applet);
        this.projectiles.add(projectile);
    }
}
