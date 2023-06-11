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
    private PVector destination;

    public Projectile(PApplet applet, float x, float y, int speed, ProjectileSource source) {
        super(applet, x, y, 3, 7);
        this._source = source;
        this._speed = speed;
    }

    public void setDestination(PVector dest){
        this.destination = dest;
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
        var img = applet.loadImage("./assets/sprites_normal_x2.png");
        multiSprite.addFrames(applet, img, 132, 392, 1);
        multiSprite.draw(applet, new Point(this.getX(), this.getY() - this.getHeight() - 2));

        sprite = multiSprite;
    }

    @Override
    public void update(){
        if(destination != null){
            var dir = PVector.sub(destination, getPosition());
            dir.add(0, 100);
            dir.normalize();
            dir.mult(this._speed);
            this.velocity.add(dir);
        } else {
            this.velocity.add(0, -this._speed);
        }
        super.update();
    }

    @Override
    public void draw(PApplet applet) {
        if(this.sprite == null)
            return;
        sprite.draw(applet, new Point(this.getX(), this.getY()));
    }

    public void updateProjectile(){
        if(this.position.y < 0){
            this.toggleVisibility();
        }
    }
}
