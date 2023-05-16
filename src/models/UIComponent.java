package models;

import processing.core.PImage;
import spritelib.Sprite;

import java.util.function.Function;

public class UIComponent {
    private float x;
    private float y;
    private float z;

    private PImage image;
    private Sprite sprite;

    public UIComponent(PImage image, float x, float y, float z){
        this.image = image;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void buildComponent(){
    }

    public PImage getImage() {
        return this.image;
    }

    public void registerEvent(Function func){
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
