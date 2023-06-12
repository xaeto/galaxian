package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import processing.core.PApplet;
import spritelib.*;

public class RedAlien extends Alien {
    public RedAlien(PApplet applet, float x, float y) {
        super(applet, x, y, 34, 23);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void setup(PApplet applet){
        var seqSprite = new SequencedSprite(TextureConstants.RedEnemyWidth, TextureConstants.RedEnemyHeight, 1000, ANCHORTYPE.TOP_LEFT);
        var img = TextureHelper.loadSpriteMap(applet);
        seqSprite.addFrames(applet, img, TextureConstants.RedEnemyOffsetX, TextureConstants.RedEnemyOffsetY, 2);
        var dieSequence = new Sequence("die");
        dieSequence.addRange(0, 1);
        seqSprite.addSequence(
                dieSequence
        );
        var idleSequence = new Sequence("idle");
        idleSequence.addRange(0, 1);
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
}
