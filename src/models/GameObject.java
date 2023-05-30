package models;

import processing.core.PApplet;
import spritelib.Rectangle;
import spritelib.Sprite;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    protected float x;
    protected float y;

    protected ArrayList<Projectile> projectiles = new ArrayList<>();

    protected int width;
    protected int height;

    protected double _direction;
    protected Sprite sprite;
    private GameObjectBehaviour _behaviour;

    public GameObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this._direction = Math.PI/2; // 90°
    }

    public boolean isVisible(){
        return this.sprite.isVisible();
    }

    public void toggleVisibility() {
        if(this.sprite.isVisible()){
            this.sprite.hide();
            return;
        }
        this.sprite.show();
    }

    public Rectangle getBounds(){
        return this.sprite.getPlotRect(this.x, this.y);
    }

    public boolean intersect(GameObject other){
        var rect = other.getBounds();
        var this_rect = this.getBounds();
        if (this.x < other.x + other.width &&
                this.x + this.width > other.x &&
                this.y < other.y + other.height &&
                this.y + this.height > other.y) {
            return true;
        } else {
            return false;
        }
    }

    public void draw(PApplet applet){
        applet.stroke(126);
       // float x1, float y1, float x2, float y2
        float x1 = this.x;
        float y1 = this.y;
        float x2 = this.x + (float)(50*Math.cos(this._direction));
        float y2 = this.y - (float)(50*Math.sin(this._direction));

        // applet.line(this.x, this.y, x2, y2);

        for (var projectile : this.projectiles){
            projectile.draw(applet);
            projectile.updateProjectile();
        }
    }

    public void setup(PApplet applet){
    }

    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void rotate(PApplet applet, float angle) {
        applet.translate(this.x, this.y);
        applet.rotate(angle);
    }

    public List<Projectile> GetProjectiles(){
        return this.projectiles;
    }

    public void shoot(PApplet applet){
        setup(applet);
    }

    public void updateBehaviour(){
        _behaviour.update(this);
    }
}
