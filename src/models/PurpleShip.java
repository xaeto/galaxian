package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import processing.core.PApplet;
import spritelib.ANCHORTYPE;
import spritelib.MultiSprite;

public class PurpleShip extends Ship {
    public PurpleShip(int x, int y) {
        super(x, y, 34, 23);
    }

    @Override
    public void setup(PApplet applet){
        sprite = new MultiSprite(TextureConstants.PurpleEnemyWidth, TextureConstants.PurpleEnemyHeight, ANCHORTYPE.CENTER);
        var img = TextureHelper.loadSpriteMap(applet);
        var mSprite = ((MultiSprite)sprite);
        mSprite.addFrames(
                applet,
                img,
                TextureConstants.PurpleEnemyOffsetX, TextureConstants.PurpleEnemyOffsetY, 2
        );
        this.combatSprites = new MultiSprite(TextureConstants.PurpleEnemyWidth, TextureConstants.PurpleEnemyHeight, ANCHORTYPE.CENTER);
    }
}
