package models;

import processing.core.PApplet;

public class GameObject {
    protected int x;
    protected int y;

    private double direction;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = Math.PI; // 90°
    }

    public void draw(PApplet applet){
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
}
