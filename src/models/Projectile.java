package models;

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

    public ProjectileSource getProjectileSource(){
        return this._source;
    }

    /**
     * This function sets the destination of an object to a given PVector.
     * 
     * @param dest dest is a parameter of type PVector which represents the destination point that we want
     * to set for an object. This method sets the value of the destination variable of the object to the
     * value of the dest parameter.
     */
    public void setDestination(PVector dest){
        this.destination = dest;
    }

    /** The `getSpeed()` method is returning the value of the `_speed` variable, which represents the speed
     * of the projectile.
    */
    public int getSpeed(){
        return this._speed;
    }

    // The `setDamage` method is setting the value of the `_damage` variable to the value of the `damage`
    // parameter. This method is used to set the damage value of the projectile, which is used to determine
    // how much damage the projectile will inflict on the target it hits.
    public void setDamage(int damage){
        this._damage = damage;
    }

    /**
     * The function returns the value of the private variable "_damage".
    * 
    * @return The method `getDamage()` is returning an integer value which is the `_damage` instance
    * variable of the class.
    */
    public int getDamage(){
        return this._damage;
    }

    /**
     * This function sets up a multi-sprite object with a specified number of rows and columns, adds frames
     * to it, and draws it on a PApplet canvas.
     * 
     * @param applet The PApplet instance that is used to load the image and draw the sprite.
     */
    @Override
    public void setup(PApplet applet){
        var multiSprite = new MultiSprite(3, 7, ANCHORTYPE.TOP_LEFT);
        var img = applet.loadImage("./assets/sprites_normal_x2.png");
        multiSprite.addFrames(applet, img, 132, 392, 1);
        multiSprite.draw(applet, new Point(this.getX(), this.getY() - this.getHeight() - 2));

        sprite = multiSprite;
    }

    /**
     * This function updates the position of an object based on its velocity and destination, with an added
     * upward force if no destination is set.
     */
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

    /**
     * This function draws a sprite on a PApplet canvas at a specified point.
     * 
     * @param applet The PApplet object that is used for drawing the sprite. It is a reference to the main
     * processing sketch that is being run.
     */
    @Override
    public void draw(PApplet applet) {
        if(this.sprite == null)
            return;
        sprite.draw(applet, new Point(this.getX(), this.getY()));
    }

    /**
     * This function updates the visibility of a projectile if its position is below the y-axis.
    */
    public void updateProjectile(){
        if(this.position.y < 0){
            this.toggleVisibility();
        }
    }
}
