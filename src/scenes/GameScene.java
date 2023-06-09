package scenes;

import com.sun.tools.jconsole.JConsoleContext;
import constants.TextureConstants;
import logic.BoundsLogic;
import models.*;
import models.ui_elements.UILabel;
import models.ui_elements.UILabelColor;
import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class GameScene extends Scene {
    private BoundsLogic _boundsLogic;
    private AlienConvoy _convoy = new AlienConvoy();
    private Timer dropTimer;
    private UILabel scoreLabel;

    // This is a constructor for the `GameScene` class that takes in an instance of `PApplet` and the
    // width and height of the scene. It calls the constructor of the parent `Scene` class with the
    // `applet` and creates an image with the specified width and height using `applet.createImage()`. It
    // also initializes a new `BoundsLogic` object with the same width and height.
    public GameScene(PApplet applet, int width, int height) {
        super(applet, applet.createImage(width, height, 0));
        _boundsLogic = new BoundsLogic(width, height);
    }

    /**
     * The function initializes a player object with a given position and sets it up in a PApplet
    * environment.
    * 
    * @param applet The applet parameter is an instance of the PApplet class, which is the main class for
    * processing sketches. It is used to access various methods and properties of the Processing
    * environment, such as the width and height of the sketch window. In this case, it is passed to the
    * Player constructor and
    * @return The method `InitializePlayer` is returning an instance of the `Player` class that has been
    * initialized with the `setup` method and positioned at the center of the `applet` window.
    */
    public static Player InitializePlayer(PApplet applet){
        var player = new Player( applet.width/2, 24);
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
    /* The `drawScene()` method is responsible for updating and rendering the game scene. It first updates
     the score label with the current high score value. Then, it handles the constraints of all game
     objects using the `_boundsLogic` object. It then moves and updates the direction of the alien
     convoy using the `_convoy` object. It then updates the attacking enemies using the
     `updateAttackingEnemies()` method, detects collisions using the `detectCollision()` method, and
     checks for dead enemies using the `checkDeadEnemies()` method. Finally, it calls the `drawScene()`
     method of the parent `Scene` class to render all game objects on the screen. */
    public void drawScene(){
        this.scoreLabel.setText(String.valueOf(GameState.Highscore), UILabelColor.Green, 1);

        _boundsLogic.handleConstraints(this.getGameObjects());
        _convoy.moveConvoy();
        _convoy.updateDirection();

        updateAttackingEnemies();
        detectCollision();
        checkDeadEnemies();

        if(_convoy.isStageCleared()){
            refreshConvoy();
        }

        super.drawScene();
    }

    private void refreshConvoy(){
       Timer t = new Timer();
       var task = new TimerTask(){
           @Override
           public void run() {
               buildEnemies();
           }
       };
       t.schedule(task, 2500);
    }

    private void updateAttackingEnemies(){
        var player = GameState.PlayerOne;
        for(Alien alien: _convoy.GetAliens()){
            if(alien.isInConvoy())
                continue;
            float angle = 1/((alien.getY() - player.getY())/(alien.getX() - player.getX()));
            var dx = alien.getX() + 2*(float)(Math.cos(angle));
            var dy = alien.getY() - 2*(float)(Math.sin(angle));

            alien.setX(dx);
            alien.setY(dy);
        }
    }

    /**
     * Checks for dead enemies in the game and calls the "die" method on them.
    */
    private void checkDeadEnemies(){
        for (GameObject obj: this.getGameObjects()){
            if(obj instanceof Alien e && !e.isAlive()){
                e.die();
                this._convoy.GetAliens().remove(e);
            }
        }
    }

    /**
     * This function builds a convoy of enemies and registers them as game objects.
    */
    public void buildEnemies(){
        _convoy.build(this._applet);
        this.RegisterGameObjects(_convoy.GetAliens()
                .stream()
                .map(c-> (GameObject)c)
                .collect(Collectors.toList()));
    }

    /**
     * The function builds the game scene by creating enemies, a highscore display, and a timer that drops
    * aliens periodically.
    */
    public void buildScene(){
        buildEnemies();
        buildHighscore();

        dropTimer = new Timer();
        var task = new TimerTask() {
            @Override
            public void run() {
                _convoy.dropAlien(_applet, GameState.PlayerOne);
            }
        };
        dropTimer.scheduleAtFixedRate(task, 5000, 5000);

        super.buildScene();
    }

    /**
     * This function builds a high score label and displays the current high score in a separate label.
    */
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

    /**
     * The function detects collisions between projectiles and enemies, and between the player and aliens,
     * and removes the affected objects.
     */
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

        var aliensToDelete = new ArrayList<Alien>();
        for(var alien: this._convoy.GetAliens()){
            if(GameState.PlayerOne.intersect(alien)){
                aliensToDelete.add(alien);
                alien.toggleVisibility();
            }
        }
        this._convoy.GetAliens().removeAll(aliensToDelete);
    }
}
