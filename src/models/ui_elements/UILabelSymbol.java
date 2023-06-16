package models.ui_elements;

import processing.core.PApplet;
import spritelib.Point;
import spritelib.Sprite;

public class UILabelSymbol {
  private final Sprite _sprite;
  private final Point _position;

  public UILabelSymbol(Sprite sprite, Point position) {
    this._position = position;
    this._sprite = sprite;
  }

  public void draw(PApplet applet) {
    _sprite.draw(applet, this._position);
  }
}
