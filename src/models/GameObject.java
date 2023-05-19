package models;

import processing.core.PApplet;
import spritelib.Sprite;

public class GameObject {
    protected int x;
    protected int y;

    protected int width;
    protected int height;
    private double direction;
    protected Sprite sprite;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = Math.PI; // 90°
    }

    public void draw(PApplet applet){
    }

    public void setup(PApplet applet){
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public void rotate(PApplet applet, float angle) {
        applet.translate(this.x, this.y);
        applet.rotate(angle);
    }
}
