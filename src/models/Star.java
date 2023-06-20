package models;

import processing.core.PApplet;
import spritelib.SingleSprite;

public class Star extends GameObject {

  // These are instance variables that represent the red, green, and blue values
  // of the star's
  // color.
  // They are used in the `draw()` method to set the fill color of the star. The
  // values are randomly
  // generated in the `setup()` method.
  private float r;
  private float g;
  private float b;

  public Star(PApplet applet, float x, float y, int width, int height) {
    super(applet, x, y, width, height);
  }

  /**
   * This function draws a circle with a random size and a fill color that changes
   * based on the
   * y-coordinate of the circle's position.
   */
  @Override
  public void draw() {
    this._applet.fill(r, g, b, (this.getY()) % (this._applet.height / 3) + 50);
    this._applet.circle(this.getX(), this.getY(), this._applet.random(2.5f, 6.5f));
  }

  /**
   * This function updates the position of an object in a PApplet sketch, making
   * it move downwards
   * and randomly resetting its position once it goes off the screen.
   *
   * @param applet The applet parameter is an instance of the PApplet class, which
   *               is the main class
   *               for processing sketches. It is used to access the width and
   *               height of the sketch, as well
   *               as other methods and variables provided by the PApplet class.
   *               In this specific code, the
   *               applet parameter
   */
  public void update() {
    if (getY() > this._applet.height) {
      this.position.y = this._applet.random(this.height);
    } else {
      this.position.y += this._applet.random(0.5f, 2.5f);
    }
  }

  /**
   * The function sets up a SingleSprite object with a randomly generated RGB
   * color using the
   * PApplet library in Java.
   *
   * @param applet The applet parameter is an instance of the PApplet class, which
   *               is the main class
   *               for a Processing sketch. It provides access to various methods
   *               and variables that are used
   *               to create and manipulate graphics, handle user input, and
   *               perform other tasks related to
   *               the sketch. In this code, the app
   */
  public void setup() {
    var applet = this._applet;
    this.sprite = new SingleSprite(applet.createImage(0, 0, 0));
    this.r = applet.random(255);
    this.g = applet.random(255);
    this.b = applet.random(255);
  }
}
