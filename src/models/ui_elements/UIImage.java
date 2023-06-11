package models.ui_elements;

import helpers.TextureHelper;
import models.ui_elements.UIComponent;
import processing.core.PApplet;
import spritelib.MultiSprite;
import spritelib.Point;
import spritelib.SingleSprite;

public class UIImage extends UIComponent {
    public UIImage(PApplet applet, float x, float y, float z) {
        super(applet, x, y, z);
    }

    @Override
    public void buildComponent(){
        var img = TextureHelper.loadSpriteMap(this._applet);
        var s = new MultiSprite(12, 12);
        s.addFrames(this._applet, img, 277, 348, 1);
        this.sprite = s;
    }
}
