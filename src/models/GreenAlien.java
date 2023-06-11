package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import processing.core.PApplet;
import spritelib.*;

public class GreenAlien extends Alien {
    public GreenAlien(PApplet applet, int x, int y) {
        super(applet, x, y, 34, 23);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void setup(PApplet applet){
        var seqSprite = new SequencedSprite(TextureConstants.GreenEnemyWidth, TextureConstants.GreenEnemyHeight, 1000, ANCHORTYPE.TOP_LEFT);
        var img = TextureHelper.loadSpriteMap(applet);
        // add default frames
        seqSprite.addFrames(applet, img, TextureConstants.GreenEnemyOffsetX, TextureConstants.GreenEnemyOffsetY, 2);
        // add explosion frames
        seqSprite.addFrames(applet, img, TextureConstants.GridGap, TextureConstants.ExplosionOffsetY, 4);
        var dieSequence = new Sequence("die");
        dieSequence.addRange(2, 5);
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
