package scenes;

import models.*;
import models.ui_elements.UILabel;
import models.ui_elements.UILabelColor;
import processing.core.PApplet;

import java.util.ArrayList;

public class GameScene extends Scene {
    public GameScene(PApplet applet, int width, int height) {
        super(applet, applet.createImage(width, height, 0));
        this.buildScene();
    }

    public static Player InitializePlayer(PApplet applet){
        var player = new Player( 24, 24);
        player.setup(applet);
        return player;
    }

    public static Player InitializePlayerTwo(PApplet applet){
        var player = new Player(100, 100);
        player.setup(applet);
        return player;
    }

    public void addPlayers(Player ... players){
        for(Player player: players) {
            RegisterGameObject(player);
        }
    }

    public void buildScene(){
        // Logo
        var test = new UILabel(
                _applet,
                (float) _applet.width /2 ,
                (float) _applet.height / 2,
                0
        );
        test.setText("TEST", UILabelColor.Red, 2);
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
