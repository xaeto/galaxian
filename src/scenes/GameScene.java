package scenes;

import models.Enemy;
import models.GameObject;
import models.GreenShip;
import models.Player;
import processing.core.PApplet;

import java.util.ArrayList;

public class GameScene extends Scene {
    private Player _player;
    public GameScene(PApplet applet, int width, int height) {
        super(applet, applet.createImage(width, height, 0));
        this.buildScene();
    }

    public Player InitializePlayer(){
        _player = new Player(_applet.width / 2 - 12, 24);
        _player.setup(_applet);
        return _player;
    }

    @Override
    public void buildScene(){
        RegisterGameObject(_player);
        ArrayList<GameObject> enemies = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            var enemy = new GreenShip(15*(i + 1) + 300, 15);
            enemy.setup(_applet);
            enemies.add(enemy);
        }
        RegisterGameObjects(enemies);
    }
}
