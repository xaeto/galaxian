package logic;

import models.GameObject;

import java.util.ArrayList;
import java.util.List;

public class BoundsLogic {
    private int width;
    private int height;
    public BoundsLogic(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void handleConstraints (List<GameObject> objects) {
        for (GameObject obj: objects) {
            if(obj == null){
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
