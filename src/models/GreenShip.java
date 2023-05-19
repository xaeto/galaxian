package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import processing.core.PApplet;
import spritelib.ANCHORTYPE;
import spritelib.MultiSprite;
import spritelib.Point;

import java.sql.Time;

public class GreenShip extends Ship {
    public GreenShip(int x, int y) {
        super(x, y, 34, 23);
    }

    @Override
    public void setup(PApplet applet){
        sprite = new MultiSprite(TextureConstants.GreenEnemyWidth, TextureConstants.GreenEnemyHeight, ANCHORTYPE.CENTER);
        var img = TextureHelper.loadSpriteMap(applet);
        var mSprite = ((MultiSprite)sprite);
        mSprite.addFrames(
                applet,
                img,
                TextureConstants.GreenEnemyOffsetX, TextureConstants.GreenEnemyOffsetY, 2
        );
    }
}
