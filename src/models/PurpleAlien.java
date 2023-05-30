package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import processing.core.PApplet;
import spritelib.*;

public class PurpleAlien extends Alien {
    public PurpleAlien(int x, int y) {
        super(x, y, 34, 23);
    }

    @Override
    public void setup(PApplet applet){
        var seqSprite = new SequencedSprite(TextureConstants.GreenEnemyWidth, TextureConstants.GreenEnemyHeight, 2, ANCHORTYPE.CENTER);
        var img = TextureHelper.loadSpriteMap(applet);
        seqSprite.addFrames(applet, img, TextureConstants.GreenEnemyOffsetX, TextureConstants.GreenEnemyOffsetY, 2);
        var dieSequence = new Sequence("die");
        dieSequence.addRange(4, 5);
        seqSprite.addSequence(
                dieSequence
        );
        var idleSequence = new Sequence("idle");
        idleSequence.addRange(0, 2);
        seqSprite.addSequence(
                idleSequence
        );
        var attackSequence = new Sequence("attacking");
        attackSequence.addRange(0, 1);
        seqSprite.addSequence(
                attackSequence
        );
        seqSprite.gotoSequence("idle");
        sprite = seqSprite;
    }

    @Override
    public void draw(PApplet applet){
        var s = (SequencedSprite)sprite;
        s.draw(applet, new Point(this.getX(), this.getY()));
    }
}
