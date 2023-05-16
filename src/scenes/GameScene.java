package scenes;

import models.Player;
import scenes.Scene;
import processing.core.PApplet;
import processing.core.PImage;

public class GameScene extends Scene {
    private Player _player;
    public GameScene(PApplet applet, int width, int height) {
        super(applet, applet.createImage(width, height, 0));
        InitializePlayer();
        this.buildScene();
    }

    public Player InitializePlayer(){
        _player = new Player(_applet, _applet.width / 2 - 12, 24);
        return _player;
    }

    @Override
    public void buildScene(){
        RegisterGameObject(_player);
    }
}
