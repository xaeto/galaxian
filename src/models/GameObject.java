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
        double y2 = 5.0*Math.sin(direction);
        double x2 = 5.0*Math.cos(direction);
        System.out.println(y2);
        applet.line((float)x, (float)y, (float)x2, (float)y2);
        applet.circle(x, y, 25);
    }
}
