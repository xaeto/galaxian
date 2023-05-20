package models.ui_elements;

import constants.TextureConstants;
import helpers.TextureHelper;
import models.ui_elements.UIComponent;
import processing.core.PApplet;
import spritelib.ANCHORTYPE;
import spritelib.MultiSprite;
import spritelib.Point;

public class UIListMenuSelector extends UIComponent {
    public UIListMenuSelector(PApplet applet, float x, float y, float z) {
        super(applet, x, y, z);
    }

    @Override
    public void buildComponent(){
        var sprites = TextureHelper.loadSpriteMap(this._applet);
        var sprite = new MultiSprite(16, 16, ANCHORTYPE.CENTER);
        sprite.addFrames(this._applet, sprites, TextureConstants.SelectorOffsetX, TextureConstants.SelectorOffsetY, 1);
        this.sprite = sprite;
    }

    @Override
    public void drawComponent(){
        this.sprite.draw(this._applet, new Point((int)this.getX() - 16, (int)this.getY()));
    }
}
