package models;

import helpers.TextureHelper;
import models.ui_elements.UIComponent;
import processing.core.PApplet;

public class Flag extends UIComponent {
    public Flag(PApplet applet, float x, float y, float z) {
        super(applet, x, y, z);
    }

    @Override
    public void buildComponent(){
        var img = TextureHelper.loadSpriteMap(this._applet);
    }
}
