package models;

import processing.core.PApplet;
import processing.core.PVector;
import spritelib.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class GameObject {
    protected int Health = 100;
    protected int Damage = 10;
    protected int Points = 10;

    protected PVector position = new PVector();
    protected PVector velocity = new PVector();

    private PVector destination = new PVector();
    protected CopyOnWriteArrayList<Projectile> projectiles = new CopyOnWriteArrayList(new ArrayList());

    protected int width;
    protected int height;

    protected double angle;
    protected Sprite sprite;

    private PApplet _applet;

    public GameObject(PApplet applet, float x, float y, int width, int height) {
        this._applet = applet;

        this.position.x = x;
        this.position.y = y;

        this.width = width;
        this.height = height;
        this.angle = Math.PI/2; // 90°
    }

    public void update(){
        this.position.add(this.velocity);
        this.velocity = new PVector();
    }

    public PVector getPosition(){
        return this.position;
    }

    public PVector getVelocity(){
        return this.velocity;
    }

    public boolean isAlive(){
        return Health > 0;
    }
    public boolean isVisible(){
        if(this.sprite == null)
            return false;
        return this.sprite.isVisible() && (this.position.x >= 0 && this.position.y <= this._applet.height);
    }

    /**
     Toggles the visibility of the sprite associated with this object.
     If the sprite is currently visible, it will be hidden. If it is hidden, it will be shown.
     */
    public void toggleVisibility() {
        if(this.sprite == null)
            return;
        if(this.sprite.isVisible() ||
                this.position.x < 0 ||
                this.position.x > this._applet.width ||
                this.position.y > this._applet.height ||
                this.position.y < 0){
            this.sprite.hide();
            return;
        }
        this.sprite.show();
    }

    public void die(){
    }

    /**
     Checks if this GameObject intersects with the specified GameObject.
     @param other the GameObject to check for intersection
     @return true if the two GameObjects intersect, false otherwise
     */
    public boolean intersect(GameObject other){
        boolean checkX = this.position.x < other.getX()+other.width && this.getX()+this.width>other.getX();
        boolean checkY = this.position.y < other.getY()+other.height && this.getY()+this.height>other.getY();

        return checkX && checkY;
    }

    /**
     Draws the projectiles on the screen and updates their positions.
     @param applet the PApplet instance used for drawing
     */
    public void draw(PApplet applet)  {
        for (var projectile : this.projectiles){
            projectile.update();
            projectile.draw(applet);
            projectile.updateProjectile();
        }
    }

    /**
     Sets up the object's sprite and sequences for animation.
     @param applet the PApplet instance used for drawing
     */
    public abstract void setup(PApplet applet);

    public float getX(){
        return this.position.x;
    }
    public float getY(){
        return this.position.y;
    }

    public void setX(float x) {
        this.position.x = x;
    }

    public void setY(float y) {
        this.position.y = y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public List<Projectile> getProjectiles(){
        return this.projectiles;
    }

    public void shoot(PApplet applet){
        setup(applet);
    }

    public void takeDamage(int damage){
        this.Health -= damage;
    }
    public int getHealth(){
        return this.Health;
    }
}
