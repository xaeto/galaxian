package models;

import processing.core.PApplet;
import processing.core.PImage;
import spritelib.Sprite;

import java.util.function.Function;

public class UIComponent {
    private float x;
    private float y;
    private float z;

    private Sprite sprite;
    protected PApplet _applet;

    public UIComponent(PApplet applet, float x, float y, float z){
        this._applet = applet;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void buildComponent(){
    }

    public void drawComponent(){
    }

    public float getX() {
        return this.x;
    }
    public float getY() {
        return this.y;
    }
    public float getZ() {
        return this.z;
    }
}
