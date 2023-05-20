package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import processing.core.PApplet;
import spritelib.ANCHORTYPE;
import spritelib.MultiSprite;
import spritelib.Point;

public class YellowShip extends Ship {
    public YellowShip(int x, int y) {
        super(x, y, TextureConstants.YellowEnemyWidth, TextureConstants.YellowEnemyHeight);
    }

    public void setup(PApplet applet){
        sprite = new MultiSprite(TextureConstants.YellowEnemyWidth, TextureConstants.YellowEnemyHeight, ANCHORTYPE.CENTER);
        var img = TextureHelper.loadSpriteMap(applet);
        var mSprite = ((MultiSprite)sprite);
        mSprite.addFrames(
                applet,
                img,
                TextureConstants.GridGap, TextureConstants.YellowEnemyOffsetY, 7     );
    }
    @Override
    public void draw(PApplet applet){
        sprite.draw(applet, new Point(this.getX(), this.getY()));
    }
}
