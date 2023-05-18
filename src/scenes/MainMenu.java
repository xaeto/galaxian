package scenes;

import models.UIComponent;
import models.UILabel;
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
        var test = new UILabel(
                _applet,
                (float) _applet.width /2 ,
                (float) _applet.height / 2,
                0
        );
        test.setText("test");
        RegisterComponent(
                test
        );
    }
}
