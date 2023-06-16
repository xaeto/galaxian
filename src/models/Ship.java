package models;

import processing.core.PApplet;

public class Ship extends Enemy {
  // This is a constructor method for the `Ship` class that takes in parameters `applet`, `x`, `y`,
  // `width`, and `height`. It calls the constructor of the parent class `Enemy` using the `super`
  // keyword and passes in the same parameters. This allows the `Ship` object to inherit properties
  // and
  // methods from the `Enemy` class.
  public Ship(PApplet applet, float x, float y, int width, int height) {
    super(applet, x, y, width, height);
  }
}
