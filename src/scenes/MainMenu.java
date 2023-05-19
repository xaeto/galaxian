package scenes;

import models.*;
import processing.core.PApplet;
import scenes.Scene;

public class MainMenu extends Scene {
    public MainMenu(PApplet applet, int width, int height) {
        super(applet, applet.createImage(width , height, 1));
        this.buildScene();
    }

    @Override
    public void buildScene(){
        // list_menu
        var list_menu_selector = new UIListMenuSelector(this._applet, 0, 0, 0);
        list_menu_selector.buildComponent();
        var list_menu = new UIListMenu(_applet, list_menu_selector, 0, 0, 0);
        var player_one = new UIListElement(
                _applet,
                0,
                (float) _applet.width /2 ,
                (float) _applet.height / 2,
                0
        );
        RegisterComponent(player_one);
        list_menu.addUIListElement(player_one);
        var player_two = new UIListElement(
                _applet,
                1,
                (float) _applet.width /2 ,
                (float) _applet.height / 2 - 16,
                0
        );
        RegisterComponent(player_two);
        list_menu.addUIListElement(player_two);
        list_menu.buildComponent();
        RegisterComponent(list_menu);
    }
}
