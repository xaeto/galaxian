package logic;

import java.util.List;
import models.GameObject;

public class BoundsLogic {
  private final int width;
  private final int height;

  /**
   * Constructs a new BoundsLogic object with the specified width and height.
   * @param width the width of the bounding box
   * @param height the height of the bounding box
   */
  public BoundsLogic(int width, int height) {
    this.width = width;
    this.height = height;
  }

  /**
   * Handles the constraints of the game objects within the bounds.
   * Moves objects back into bounds if they exceed the boundaries.
   * @param objects the list of game objects to handle constraints for
   */
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
