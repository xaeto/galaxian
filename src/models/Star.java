package models;

import processing.core.PApplet;
import spritelib.Point;
import spritelib.SingleSprite;
import spritelib.Sprite;

public class Star extends GameObject {
    private float r;
    private float g;
    private float b;
    public Star(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(PApplet applet){
        applet.fill(r, g, b, 255);
        applet.circle(this.x, this.y, 5.0f);
    }

    public void update(PApplet applet){
        if(y > applet.height){
            this.y = applet.random(this.height);
        } else {
            this.y += applet.random(0.5f, 2.5f);
        }
    }

    public void setup(PApplet applet){
        this.sprite = new SingleSprite(applet.createImage(0,0,0));
        this.r = applet.random(255);
        this.g = applet.random(255);
        this.b = applet.random(255);
    }
}
