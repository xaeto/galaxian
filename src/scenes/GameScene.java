package scenes;

import models.*;
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

        // Logo
        var test = new UILabel(
                _applet,
                (float) _applet.width /2 ,
                (float) _applet.height / 2,
                0
        );
        test.setText("ABC123", UILabelColor.White, 2);
        RegisterComponent(
                test
        );
        ArrayList<GameObject> enemies = new ArrayList<>();

        // initialize yellow ships
        for(int i = 0; i < 4; i++){
            var enemy = new YellowShip(32*(i + 2) + 300 -2, 34);
            enemy.setup(_applet);
            enemies.add(enemy);
        }
        // initialize red ships
        for(int i = 0; i < 6; i++){
            var enemy = new RedShip(48*(i + 2) + 300 - 100 -2, 34*2);
            enemy.setup(_applet);
            enemies.add(enemy);
        }
        // initialize purple ships
        for(int i = 0; i < 8; i++){
            var enemy = new PurpleShip(48*(i + 2) + 300/2 -2, 34*3);
            enemy.setup(_applet);
            enemies.add(enemy);
        }
        // initialize green ships
        for(int j =0; j < 10; ++j) {
            var enemy = new GreenShip(48*(j + 1) + 300/2 -2, 34*4);
            enemy.setup(_applet);
            enemies.add(enemy);
        }
        for(int j =0; j < 10; ++j) {
            var enemy = new GreenShip(48*(j + 1) + 300/2 -2, 34*5);
            enemy.setup(_applet);
            enemies.add(enemy);
        }
        for(int j =0; j < 10; ++j) {
            var enemy = new GreenShip(48*(j + 1) + 300/2 -2, 34*6);
            enemy.setup(_applet);
            enemies.add(enemy);
        }
        RegisterGameObjects(enemies);
    }
}
