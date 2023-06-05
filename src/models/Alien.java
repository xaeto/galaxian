package models;

import processing.core.PApplet;
import spritelib.MultiSprite;
import spritelib.Point;

import java.util.OptionalLong;

public class Alien extends Enemy {
    public Alien(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(PApplet applet){
        sprite.draw(applet, new Point(this.getX(), this.getY()));
        super.draw(applet);
    }
}
