package models.ui_elements;

import constants.TextureConstants;
import java.util.ArrayList;
import processing.core.PApplet;

public class UIListMenu extends UIComponent {
  private final ArrayList<UIListElement> _listElements = new ArrayList<>();
  private int currentIndex = 0;
  private UIListMenuSelector _selector;

  public UIListMenu(PApplet applet, float x, float y, float z) {
    super(applet, x, y, z);
  }

  public void setSelector(UIListMenuSelector selector) {
    this._selector = selector;
  }

  public void addUIListElement(UIListElement listElement) {
    this._listElements.add(listElement);
    listElement.buildComponent();
  }

  public void decreaseSelector() {
    if (this.currentIndex - 1 < 0) {
      this.currentIndex = this._listElements.size() - 1;
    } else {
      this.currentIndex -= 1;
    }
    UIListElement currentElement =
        this._listElements.stream()
            .filter(c -> c.getIndex() == this.currentIndex)
            .findFirst()
            .get();

    this._selector.setY(
        currentElement.getY() + TextureConstants.TextHeight / 2 - TextureConstants.GridGap);
  }

  public void advanceSelector() {
    if (this.currentIndex + 1 > this._listElements.size() - 1) {
      this.currentIndex = 0;
    } else {
      this.currentIndex += 1;
    }
    UIListElement currentElement =
        this._listElements.stream()
            .filter(c -> c.getIndex() == this.currentIndex)
            .findFirst()
            .get();

    this._selector.setY(
        currentElement.getY() + TextureConstants.TextHeight / 2 - TextureConstants.GridGap);
  }

  public int getCurrentIndex() {
    return this.currentIndex;
  }

  @Override
  public void drawComponent() {
    this._applet.clear();
    _selector.drawComponent();

    for (UIListElement element : _listElements) {
      element.drawComponent();
    }
  }
}
