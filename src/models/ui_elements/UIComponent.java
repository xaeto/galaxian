package models.ui_elements;

import processing.core.PApplet;
import spritelib.Point;
import spritelib.Sprite;

public class UIComponent {

    protected Sprite sprite;
    protected PApplet _applet;
    private float x;
    private float y;
    private float z;
    public UIComponent(PApplet applet, float x, float y, float z){
        this._applet = applet;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void buildComponent(){
    }

    public void updateComponent(){

    }

    public void drawComponent(){
        if(this.sprite == null)
            return;
        this.sprite.draw(
                this._applet,
                new Point(this.getX(), this.getY())
        );
    }

    public int getHeight(){
        return sprite.getSize().getHeight();
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return this.z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
