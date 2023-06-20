package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import processing.core.PApplet;
import spritelib.*;

public class YellowShip extends Alien {
  public YellowShip(PApplet applet, int x, int y) {
    super(applet, x, y, TextureConstants.YellowEnemyWidth, TextureConstants.YellowEnemyHeight);
  }

  /** {@inheritDoc} */
  public void setup() {
    var applet = this._applet;
    var seqSprite = new SequencedSprite(
        TextureConstants.YellowEnemyWidth,
        TextureConstants.YellowEnemyHeight,
        1000,
        ANCHORTYPE.TOP_LEFT);
    var img = TextureHelper.loadSpriteMap(applet);
    seqSprite.addFrames(
        applet, img, TextureConstants.GridGap, TextureConstants.YellowEnemyOffsetY, 2);

    var dieSequence = new Sequence("die");
    dieSequence.addRange(0, 1);
    seqSprite.addSequence(dieSequence);
    var idleSequence = new Sequence("idle");
    idleSequence.addRange(0, 1);
    seqSprite.addSequence(idleSequence);
    var attackSequence = new Sequence("attacking");
    attackSequence.addRange(0, 1);
    seqSprite.addSequence(attackSequence);
    seqSprite.gotoSequence("idle");
    sprite = seqSprite;
  }
}
