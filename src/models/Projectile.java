package models;

import helpers.TextureHelper;
import processing.core.PApplet;
import processing.core.PVector;
import spritelib.ANCHORTYPE;
import spritelib.MultiSprite;
import spritelib.Point;

public class Projectile extends GameObject {
    private int _damage;
    private int _speed;
    private ProjectileSource _source;
    private PVector destination = new PVector();

    public Projectile(float x, float y, int speed, ProjectileSource source) {
        super(x, y, 3, 7);
        this._source = source;
        this._speed = speed;
    }

    public int getSpeed(){
        return this._speed;
    }

    public void setDamage(int damage){
        this._damage = damage;
    }

    public int getDamage(){
        return this._damage;
    }

    @Override
    public void setup(PApplet applet){
        var multiSprite = new MultiSprite(3, 7, ANCHORTYPE.TOP_LEFT);
        multiSprite.addFrames(applet, TextureHelper.loadSpriteMap(applet), 132, 392, 1);
        multiSprite.draw(applet, new Point(this.getX(), this.getY() - this.getHeight() - 2));

        sprite = multiSprite;
    }

    @Override
    public void draw(PApplet applet) {
        sprite.draw(applet, new Point(this.x, this.y));
    }

    public void updateProjectile(){
        this.y -= 10*Math.sin(this._direction);
        this.x += 10*Math.cos(this._direction);
        if(this.y < 0){
            this.toggleVisibility();
        }
    }
}
