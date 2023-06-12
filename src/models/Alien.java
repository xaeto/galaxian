package models;

import processing.core.PApplet;
import processing.core.PVector;
import spritelib.Point;

import java.util.Timer;
import java.util.TimerTask;

public class Alien extends Enemy {
    protected boolean partOfConvoy = true;
    protected Timer shootTimer;

    public Alien(PApplet applet, float x, float y, int width, int height) {
        super(applet, x, y, width, height);
    }

    @Override
    public void draw(PApplet applet){
        sprite.draw(applet, new Point(this.getX(), this.getY()));
        if(!this.isVisible() && this.shootTimer != null){
            shootTimer.cancel();
        }
        super.draw(applet);
    }

    public void setPartOfConvoy(boolean partOfConvoy){
        this.partOfConvoy = partOfConvoy;
    }
    /**
     * The function returns a boolean value indicating whether the object is part of a convoy or not.
     *
     * @return A boolean value indicating whether the object is part of a convoy or not.
     */
    public boolean isInConvoy(){
        return this.partOfConvoy;
    }

    public void reset(){
        this.partOfConvoy = true;
        this.shootTimer = null;
        this.velocity = new PVector();
        this.canShoot = true;
        toggleVisibility();
    }

}
