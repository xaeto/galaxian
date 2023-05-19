package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import processing.core.PApplet;
import spritelib.ANCHORTYPE;
import spritelib.MultiSprite;
import spritelib.Point;

public class RedShip extends Ship {
    public RedShip(int x, int y) {
        super(x, y, 34, 23);
    }

    @Override
    public void setup(PApplet applet){
        sprite = new MultiSprite(TextureConstants.RedEnemyWidth, TextureConstants.RedEnemyHeight, ANCHORTYPE.CENTER);
        var img = TextureHelper.loadSpriteMap(applet);
        var mSprite = ((MultiSprite)sprite);
        mSprite.addFrames(
                applet,
                img,
                TextureConstants.RedEnemyOffsetX, TextureConstants.RedEnemyOffsetY, 2
        );
    }
}
