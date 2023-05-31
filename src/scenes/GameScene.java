package scenes;

import constants.TextureConstants;
import logic.BoundsLogic;
import models.*;
import models.ui_elements.UILabel;
import models.ui_elements.UILabelColor;
import processing.core.PApplet;
import spritelib.Rectangle;

import java.util.ArrayList;

public class GameScene extends Scene {
    private BoundsLogic _boundsLogic;

    public GameScene(PApplet applet, int width, int height) {
        super(applet, applet.createImage(width, height, 0));
        _boundsLogic = new BoundsLogic(width, height);
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

    @Override
    public void drawScene(){
        _boundsLogic.handleConstraints(this.getGameObjects());
        detectCollision();
        drawStars();
        super.drawScene();
    }

    public void drawStars(){
    }

    public void buildEnemies(){
        ArrayList<GameObject> enemies = new ArrayList<>();

        // initialize yellow ships
        for(int i = 0; i < 4; i++){
            var enemy = new YellowShip(32*(i + 2) + 300 -2, 34);
            enemy.setup(_applet);
            enemies.add(enemy);
        }
        // initialize red ships
        for(int i = 0; i < 6; i++){
            var enemy = new RedAlien(48*(i + 2) + 300 - 100 -2, 34*2);
            enemy.setup(_applet);
            enemies.add(enemy);
        }
        // initialize purple ships
        for(int i = 0; i < 8; i++){
            var enemy = new PurpleAlien(48*(i + 2) + 300/2 -2, 34*3);
            enemy.setup(_applet);
            enemies.add(enemy);
        }
        // initialize green ships
        for(int j =0; j < 10; ++j) {
            var enemy = new GreenAlien(48*(j + 1) + 300/2 -2, 34*4);
            enemy.setup(_applet);
            enemies.add(enemy);
        }
        for(int j =0; j < 10; ++j) {
            var enemy = new GreenAlien(48*(j + 1) + 300/2 -2, 34*5);
            enemy.setup(_applet);
            enemies.add(enemy);
        }
        for(int j =0; j < 10; ++j) {
            var enemy = new GreenAlien(48*(j + 1) + 300/2 -2, 34*6);
            enemy.setup(_applet);
            enemies.add(enemy);
        }
        RegisterGameObjects(enemies);
    }

    private void buildHighScore(){
        String highscore_label = "HIGH SCORE";
        UILabel label = new UILabel(
                this._applet,
                16,
                16,
                0
        );
        label.setText(highscore_label, UILabelColor.Red, 1);

        int current_score = 500;
        int score_length = (int)String.valueOf(current_score).chars().count();
        System.out.println(score_length);
        UILabel score = new UILabel(
                this._applet,
                label.getX() + highscore_label.length()*8,
                label.getY() + TextureConstants.TextHeight + 2*TextureConstants.GridGap,
                0
        );
        score.setText(String.valueOf(current_score), UILabelColor.Green, 1);
        RegisterComponent(score);
        RegisterComponent(label);
    }
    public void buildScene(){
        buildEnemies();
        buildHighScore();
    }

    public void detectCollision(){
        var projectilesPlayerOne = GameState.PlayerOne.GetProjectiles();
        for (var projectile: projectilesPlayerOne) {
            Rectangle projectileBounds = projectile.getBounds();
            for (var enemy: this.getGameObjects()){
                if(enemy instanceof Enemy e){
                    if(projectile.intersect(e)){
                        projectile.toggleVisibility();
                        enemy.takeDamage(10);
                        e.die();
                        System.out.println("Collision detected");
                    }
                }
            }
        }
    }
}
