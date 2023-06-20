package models;

import processing.core.PApplet;
import spritelib.SequencedSprite;

public class Enemy extends GameObject {
  /**
   * This is the class constructor it constructs a new Enemy object with the
   * specified parameters.
   * 
   * @param applet the PApplet instance associated with the enemy
   * @param x      the x-coordinate of the enemy's position
   * @param y      the y-coordinate of the enemy's position
   * @param width  the width of the enemy's bounding box
   * @param height the height of the enemy's bounding box
   */
  public Enemy(PApplet applet, float x, float y, int width, int height) {
    super(applet, x, y, width, height);
  }

  /**
   * Shoots a projectile from this object's position.
   *
   * @param applet the PApplet instance used for drawing
   */
  @Override
  public void draw() {
    super.draw();
  }

  @Override
  public void setup() {
  }

  /** Sets the sprite's sequence to "attack". */
  public void setAttackPose() {
    var seq = (SequencedSprite) this.sprite;
    seq.gotoSequence("attack");
  }

  /** Sets the sprite sequence to "idle". */
  public void setIdle() {
    var seq = (SequencedSprite) this.sprite;
    seq.gotoSequence("idle");
  }
}
