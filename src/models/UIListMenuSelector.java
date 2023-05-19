package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import processing.core.PApplet;
import spritelib.ANCHORTYPE;
import spritelib.MultiSprite;

public class UIListMenuSelector extends UIComponent {
    public UIListMenuSelector(PApplet applet, float x, float y, float z) {
        super(applet, x, y, z);
    }

    @Override
    public void buildComponent(){
        var sprites = TextureHelper.loadSpriteMap(this._applet);
        var sprite = new MultiSprite(16, 16, ANCHORTYPE.CENTER);
        sprite.addFrames(this._applet, sprites, TextureConstants.SelectorOffsetX, TextureConstants.SelectorOffsetY, 1);
    }
}
