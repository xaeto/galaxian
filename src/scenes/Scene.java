package scenes;

import models.GameObject;
import models.ui_elements.UIComponent;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Scene {
    private PImage _background;
    protected PApplet _applet;
    private final ArrayList<UIComponent> Components = new ArrayList<>();
    private final ArrayList<GameObject> GameObjects = new ArrayList<>();

    public Scene(PApplet applet, PImage background){
        this._background = background;
        this._applet = applet;
    }

    public ArrayList<GameObject> getGameObjects(){
        return this.GameObjects;
    }

    public void RegisterComponent(UIComponent component){
        Components.add(component);
    }

    public void RegisterGameObject(GameObject object){
        GameObjects.add(object);
    }

    public void RegisterGameObjects(ArrayList<GameObject> objects){
        GameObjects.addAll(objects);
    }

    public void drawScene(){
        var applet = this._applet;
        applet.clear();

        for (UIComponent component : Components) {
            component.drawComponent();
        }

        for (GameObject gameObject : GameObjects) {
            if(gameObject == null)
                continue;
            if(gameObject.isVisible()){
                gameObject.draw(applet);
            }
        }
    }

    public void buildScene(){
    }
}
