package logic;

import java.util.List;
import models.GameObject;

public class BoundsLogic {
  private final int width;
  private final int height;

  public BoundsLogic(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public void handleConstraints(List<GameObject> objects) {
    for (GameObject obj : objects) {
      if (obj == null) {
        continue;
      }
      if (obj.getX() - obj.getWidth() < 0) {
        obj.setX(obj.getWidth());
      }
      if (obj.getX() > this.width - obj.getWidth()) {
        obj.setX(this.width - obj.getWidth());
      }
    }
  }
}
