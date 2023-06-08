package scenes;

import constants.TextureConstants;
import logic.BoundsLogic;
import models.*;
import models.ui_elements.UILabel;
import models.ui_elements.UILabelColor;
import processing.core.PApplet;

public class GameScene extends Scene {
    private BoundsLogic _boundsLogic;
    private AlienConvoy _convoy = new AlienConvoy();
    private UILabel scoreLabel;

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

        this.scoreLabel.setText(String.valueOf(GameState.Highscore), UILabelColor.Green, 1);
        _boundsLogic.handleConstraints(this.getGameObjects());
        _convoy.moveConvoy();
        _convoy.updateDirection();
        checkDeadEnemies();
        // redraw();
        super.drawScene();
    }

    private void redraw(){
        for(var uiComp: this.getUiComponents()){
            uiComp.drawComponent();
        }
    }

    private void checkDeadEnemies(){
        for (GameObject obj: this.getGameObjects()){
            if(obj instanceof Enemy e && !e.isAlive()){
                e.die();
            }
        }
    }

    public void buildEnemies(){
        _convoy.build(this._applet);
        this.RegisterGameObjects(_convoy.GetShips());
    }

    public void buildScene(){
        buildEnemies();
        buildHighscore();
        super.buildScene();
    }

    public void buildHighscore(){
        String highscore_label = "HIGH SCORE";
        UILabel label = new UILabel(
                this._applet,
                16,
                16,
                0
        );
        label.setText(highscore_label, UILabelColor.Red, 1);
        RegisterComponent(label);

        int current_score = GameState.Highscore;
        int score_length = (int)String.valueOf(current_score).chars().count();
        this.scoreLabel = new UILabel(
                this._applet,
                label.getX() - score_length* TextureConstants.TextHeight/2 + highscore_label.length()*8,
                label.getY() + TextureConstants.TextHeight + 2*TextureConstants.GridGap,
                0
        );

        this.scoreLabel.setText(String.valueOf(current_score), UILabelColor.Green, 1);
        RegisterComponent(this.scoreLabel);
        RegisterComponent(label);
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
                    }
                }
            }
        }
    }
}
