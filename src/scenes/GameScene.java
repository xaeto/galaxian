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
    private AlienConvoy _convoy = new AlienConvoy();

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
        detectCollision();
        _boundsLogic.handleConstraints(this.getGameObjects());
        _convoy.updateDirection();
        _convoy.moveConvoy();
        super.drawScene();
    }

    private void checkDeadEnemies(){
        for (GameObject obj: this.getGameObjects()){
            if(obj instanceof Enemy e && !e.isAlive()){
                e.die();
                System.out.println(GameState.Highscore);
                GameState.Highscore += 10;
                buildHighScore();
            }
        }
    }

    public void drawPauseInfo(){
        String pause_label = "PAUSED";
        UILabel label = new UILabel(
                this._applet,
                this._applet.width/2,
                this._applet.height/2,
                0
        );
        label.setText(pause_label, UILabelColor.Red, 1);
        RegisterComponent(label);
    }

    public void buildEnemies(){
        _convoy.build(this._applet);
        RegisterGameObjects(_convoy.GetShips());
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

        int current_score = GameState.Highscore;
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
        super.buildScene();
    }

    public void detectCollision(){
        var projectilesPlayerOne = GameState.PlayerOne.GetProjectiles();
        for (var projectile: projectilesPlayerOne) {
            if(!projectile.isVisible())
                continue;
            for (var enemy: this.getGameObjects()){
                if(enemy instanceof Enemy e){
                    if(projectile.intersect(e)){
                        enemy.takeDamage(500);
                        projectile.toggleVisibility();
                        if(!enemy.isAlive()){
                            e.die();
                        }
                    }
                }
            }
        }
    }
}
