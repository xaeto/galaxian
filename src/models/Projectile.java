package models;

import processing.core.PApplet;
import processing.core.PVector;
import spritelib.ANCHORTYPE;
import spritelib.MultiSprite;
import spritelib.Point;

public class Projectile extends GameObject {
    private final int _speed;
    private final ProjectileSource _source;
    private PVector destination;

    public Projectile(PApplet applet, float x, float y, int speed, ProjectileSource source) {
        super(applet, x, y, 3, 7);
        this._source = source;
        this._speed = speed;
    }

    public ProjectileSource getProjectileSource() {
        return this._source;
    }

    /**
     * This function sets the destination of an object to a given PVector.
     * 
     * @param dest dest is a parameter of type PVector which represents the
     *             destination point that we want
     *             to set for an object. This method sets the value of the
     *             destination variable of the object to the
     *             value of the dest parameter.
     */
    public void setDestination(PVector dest) {
        this.destination = dest;
    }

    /**
     * This function sets up a multi-sprite object with a specified number of rows
     * and columns, adds frames
     * to it, and draws it on a PApplet canvas.
     */
    @Override
    public void setup() {
        var multiSprite = new MultiSprite(3, 7, ANCHORTYPE.TOP_LEFT);
        var img = this._applet.loadImage("./assets/sprites_normal_x2.png");
        multiSprite.addFrames(this._applet, img, 132, 392, 1);
        multiSprite.draw(this._applet, new Point(this.getX(), this.getY() - this.getHeight() - 2));

        sprite = multiSprite;
    }

    /**
     * This function updates the position of an object based on its velocity and
     * destination, with an added
     * upward force if no destination is set.
     */
    @Override
    public void update() {
        if (destination != null) {
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

    /**
     * This function draws a sprite on a PApplet canvas at a specified point.
     */
    @Override
    public void draw() {
        if (this.sprite == null)
            return;
        sprite.draw(this._applet, new Point(this.getX(), this.getY()));
    }
}
