package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import org.w3c.dom.Text;
import processing.core.PApplet;
import spritelib.ANCHORTYPE;
import spritelib.MultiSprite;
import spritelib.Point;
import spritelib.SingleSprite;

public class Player extends GameObject {
    public Player(int x, int y) {
        super(x, 550, 48, 48);
    }

    public void setup(PApplet applet){
        sprite = new MultiSprite(TextureConstants.PlayerWidth, TextureConstants.PlayerHeight, ANCHORTYPE.CENTER);
        var img = TextureHelper.loadSpriteMap(applet);
        ((MultiSprite)sprite).addFrames(
                applet,
                img,
                TextureConstants.OffsetX, TextureConstants.PlayerOffsetY, 2
        );
    }

    @Override
    public void draw(PApplet applet) {
        sprite.draw(applet, new Point(this.getX(), this.getY()));
    }

    public void moveLeft(){
        this.x -= 10;
    }
    public void moveRight(){
        this.x += 10;
    }
    public void fireProjectile(){

    }
}
