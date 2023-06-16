package models.ui_elements;

import processing.core.PApplet;

public class UIListElement extends UILabel {
  private final int _index;

  public UIListElement(PApplet applet, int index, float x, float y, float z) {
    super(applet, x, y, z);
    this._index = index;
  }

  public int getIndex() {
    return this._index;
  }
}
