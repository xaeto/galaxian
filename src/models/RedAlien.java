package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import processing.core.PApplet;
import spritelib.*;

public class RedAlien extends Alien {
    public RedAlien(float x, float y) {
        super(x, y, 34, 23);
    }

    @Override
    public void setup(PApplet applet){
        var seqSprite = new SequencedSprite(TextureConstants.RedEnemyWidth, TextureConstants.RedEnemyHeight, 30, ANCHORTYPE.TOP_LEFT);
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
