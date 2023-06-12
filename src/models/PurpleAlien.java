package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import processing.core.PApplet;
import spritelib.*;

public class PurpleAlien extends Alien {
    public PurpleAlien(PApplet applet, int x, int y) {
        super(applet, x, y, 34, 23);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void setup(PApplet applet){
        var seqSprite = new SequencedSprite(TextureConstants.PurpleEnemyWidth, TextureConstants.PurpleEnemyHeight, 1000, ANCHORTYPE.TOP_LEFT);
        var img = TextureHelper.loadSpriteMap(applet);
        seqSprite.addFrames(applet, img, TextureConstants.PurpleEnemyOffsetX, TextureConstants.PurpleEnemyOffsetY, 2);
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
}
