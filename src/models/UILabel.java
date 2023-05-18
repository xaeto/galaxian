package models;

import processing.core.PApplet;

public class UILabel extends UIComponent {
    private String _text;
    public UILabel(PApplet applet, float x, float y, float z) {
        super(applet, x, y, z);
    }

    @Override
    public void drawComponent(){
    }

    @Override
    public void buildComponent(){
    }

    public void setText(String text) {
        this._text = text;
    }
}
