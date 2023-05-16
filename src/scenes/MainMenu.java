package scenes;

import models.UIComponent;
import processing.core.PApplet;
import scenes.Scene;

public class MainMenu extends Scene {
    public MainMenu(PApplet applet, int width, int height) {
        super(applet, applet.createImage(width , height, 1));
        this.buildScene();
    }

    @Override
    public void buildScene(){
        // Logo
        RegisterComponent(
                new UIComponent(
                        _applet.loadImage("./assets/logo.png"),
                        _applet.width /2 , _applet.height / 2, 0
                )
        );
    }
}
