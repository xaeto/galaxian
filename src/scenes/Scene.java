package scenes;

import models.GameObject;
import models.Star;
import models.ui_elements.UIComponent;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class Scene {
    private PImage _background;
    protected PApplet _applet;
    private final CopyOnWriteArrayList<UIComponent> Components = new CopyOnWriteArrayList (new ArrayList<>());
    private final CopyOnWriteArrayList<GameObject> GameObjects = new CopyOnWriteArrayList(new ArrayList<>());
    private final ArrayList<Star> Stars = new ArrayList<>();

    protected boolean redrawObjects = true;

    public Scene(PApplet applet, PImage background){
        this._background = background;
        this._applet = applet;
    }

    public ArrayList<Star> getStars(){
        return this.Stars;
    }

    public void drawStars(){
        for (var star: getStars()) {
            star.update(this._applet);
            star.draw(this._applet);
        }
    }

    public CopyOnWriteArrayList<GameObject> getGameObjects(){
        return this.GameObjects;
    }

    public CopyOnWriteArrayList<UIComponent> getUiComponents(){
        return this.Components;
    }

    public void RegisterComponent(UIComponent component){
        Components.add(component);
    }

    /**
     * The `RegisterGameObject` method is adding a single `GameObject` object to the
     * `GameObjects` list. It
     * uses the `add` method of the `CopyOnWriteArrayList` class to add the
     * specified object to the end of the list.
     */
    public void RegisterGameObject(GameObject object){
        GameObjects.add(object);
    }

    /**
     * The `RegisterGameObjects` method is adding a collection of `GameObject`
     * objects to the `GameObjects`
     * list. It uses the `addAll` method of the `CopyOnWriteArrayList` class to add
     * all the elements of the
     * specified collection to the end of the list.
     */
    public void RegisterGameObjects(Collection<GameObject> objects){
        GameObjects.addAll(objects);
    }

    /**
     * This is a method that draws the scene by clearing the screen, drawing UI
     * components, updating and
     * drawing game objects, and drawing stars. It also calls `System.gc()` to
     * suggest that the garbage
     * collector should run to free up memory.
     */
    public void drawScene(){
        var applet = this._applet;
        applet.flush();
        applet.clear();
        this._applet.background(0);

        for (UIComponent component : Components) {
            component.drawComponent();
        }
        for (GameObject obj: this.GameObjects) {
            if(obj == null)
                continue;
            if(!obj.isVisible())
                continue;
            obj.draw(applet);
            obj.update();
        }
        drawStars();
    }

    /**
     * This function builds a scene by creating 32 randomly positioned stars and
     * adding them to a list.
     */
    public void buildScene(){
        for(int i = 0; i < 32; ++i){
            float x = _applet.random(_applet.width);
            float y = _applet.random(_applet.height);
            var star = new Star(this._applet, x, y, 1, 2);
            star.setup(this._applet);
            Stars.add(star);
        }
    }
}
