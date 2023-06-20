package models;

import java.util.Timer;
import processing.core.PApplet;
import processing.core.PVector;
import spritelib.Point;

public class Alien extends Enemy {
  protected boolean partOfConvoy = true;
  protected Timer shootTimer;

  /**
   * This is the constructor for the class which constructs an Alien object.
   * 
   * @param applet The PApplet instance.
   * @param x      The x-coordinate of the alien's position.
   * @param y      The y-coordinate of the alien's position.
   * @param width  The width of the alien.
   * @param height The height of the alien.
   */
  public Alien(PApplet applet, float x, float y, int width, int height) {
    super(applet, x, y, width, height);
  }

  /**
   * This method draws the sprite on the given PApplet.
   * If the entity is not visible and has a shoot timer, it cancels the shoot
   * timer.
   * At last, calls the superclass's draw method.
   */
  @Override
  public void draw() {
    sprite.draw(this._applet, new Point(this.getX(), this.getY()));
    if (!this.isVisible() && this.shootTimer != null) {
      shootTimer.cancel();
    }
    super.draw();
  }

  /**
   * Sets whether the entity is part of a convoy or not.
   * 
   * @param partOfConvoy true if the entity is part of a convoy, false otherwise
   */
  public void setPartOfConvoy(boolean partOfConvoy) {
    this.partOfConvoy = partOfConvoy;
  }

  /**
   * The function returns a boolean value indicating whether the object is part of
   * a convoy or not.
   *
   * @return A boolean value indicating whether the object is part of a convoy or
   *         not.
   */
  public boolean isInConvoy() {
    return this.partOfConvoy;
  }

  /**
   * This method resets the entity to its initial state.
   * - Sets the entity as part of a convoy.
   * - Sets the shoot timer to null.
   * - Resets the velocity to zero.
   * - Sets the entity as able to shoot.
   * - Toggles the visibility of the entity.
   */
  public void reset() {
    this.partOfConvoy = true;
    this.shootTimer = null;
    this.velocity = new PVector();
    this.canShoot = true;
    toggleVisibility();
  }
}
