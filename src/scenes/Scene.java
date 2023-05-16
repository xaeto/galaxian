package scenes;

import models.GameObject;
import models.UIComponent;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Scene {
    private PImage _background;
    protected PApplet _applet;
    private ArrayList<UIComponent> Components = new ArrayList<>();
    private ArrayList<GameObject> GameObjects = new ArrayList<>();

    public Scene(PApplet applet, PImage background){
        this._background = background;
        this._applet = applet;
    }

    public void RegisterComponent(UIComponent component){
        Components.add(component);
    }

    public void RegisterGameObject(GameObject object){
        GameObjects.add(object);
    }

    public void drawScene(){
        var applet = this._applet;
        applet.clear();

        for (UIComponent component : Components) {
            applet.image(component.getImage(), component.getX(), component.getY());
        }

        for (GameObject gameObject : GameObjects) {
            gameObject.draw(applet);
        }
    }

    public void buildScene(){
    }
}
