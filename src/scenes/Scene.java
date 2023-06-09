package scenes;

import models.GameObject;
import models.Star;
import models.ui_elements.UIComponent;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Collection;

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

    public ArrayList<UIComponent> getUiComponents(){
        return this.Components;
    }

    public void RegisterComponent(UIComponent component){
        Components.add(component);
    }

    public void RegisterGameObject(GameObject object){
        GameObjects.add(object);
    }

    public void RegisterGameObjects(Collection<GameObject> objects){
        GameObjects.addAll(objects);
    }

    public void drawScene(){
        var applet = this._applet;
        applet.flush();
        applet.clear();
        this._applet.background(0);

        for (UIComponent component : Components) {
            component.drawComponent();
        }

        ArrayList<GameObject> objectsToDelete = new ArrayList<>();
        for (GameObject gameObject : GameObjects) {
            if(gameObject == null)
                continue;
            if(!gameObject.isAlive()){
                objectsToDelete.add(gameObject);
            }
            if(gameObject instanceof Star star){
                star.update(applet);
            }
            if(gameObject.isVisible()){
                gameObject.draw(applet);
            }
        }
        GameObjects.removeAll(objectsToDelete);
    }

    public void buildScene(){
        for(int i = 0; i < 32; ++i){
            float x = _applet.random(_applet.width);
            float y = _applet.random(_applet.height);
            var star = new Star(x, y, 1, 2);
            star.setup(this._applet);
            RegisterGameObject(star);
        }
    }
}
