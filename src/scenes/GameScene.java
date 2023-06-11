package scenes;

import constants.TextureConstants;
import logic.BoundsLogic;
import models.*;
import models.ui_elements.UIImage;
import models.ui_elements.UILabel;
import models.ui_elements.UILabelColor;
import processing.core.PApplet;

import java.util.*;
import java.util.stream.Collectors;

public class GameScene extends Scene {
    private BoundsLogic _boundsLogic;
    private AlienConvoy _convoy;
    private Timer dropTimer;
    private UILabel scoreLabel;
    private boolean isGameOver = false;
    private int healthPoints = 25;
    private Stack<UIImage> HealthPointStack = new Stack<>();

    // This is a constructor for the `GameScene` class that takes in an instance of `PApplet` and the
    // width and height of the scene. It calls the constructor of the parent `Scene` class with the
    // `applet` and creates an image with the specified width and height using `applet.createImage()`. It
    // also initializes a new `BoundsLogic` object with the same width and height.
    public GameScene(PApplet applet, int width, int height) {
        super(applet, applet.createImage(width, height, 0));
        _convoy = new AlienConvoy(this._applet);
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
        var player = new Player( applet, applet.width/2, 24);
        player.setup(applet);
        return player;
    }

    public static Player InitializePlayerTwo(PApplet applet){
        var player = new Player(applet, 100, 100);
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

        if(!_convoy.isStageCleared()){
            _convoy.moveConvoy();
            _convoy.updateDirection();
            updateAttackingEnemies();
            detectCollision();
        } else {
            _convoy.setStageState(false);
            redrawObjects = false;
            var t = new Timer();
            var task = new TimerTask(){
                @Override
                public void run() {
                    _convoy.build(_applet);
                }
            };
            t.schedule(task, 2500);
        }

        super.drawScene();
        for(var healthPoint: this.HealthPointStack){
            healthPoint.drawComponent();
        }

        if(this.healthPoints == 0){
            System.out.println("Game is Over");
        }
    }

    private void updateAttackingEnemies(){
        var player = GameState.PlayerOne;
        for(Alien alien: _convoy.getAliens()){
            if(alien.isInConvoy())
                continue;
            float dx = (player.getX() + player.getWidth()/2) - alien.getX();
            float dy = (player.getY() + player.getHeight()/2) - alien.getY();

            double normalize = Math.sqrt(dx*dx + dy*dy);
            dx /= normalize;
            dy /= normalize;

            float angle = (float)Math.atan2(dy, dx);
            angle = (float)Math.toDegrees(angle);
            float newX = alien.getX() + 3*(float)(Math.sin(angle));
            float newY = alien.getY() + 2;
            alien.setX(newX);
            alien.setY(newY);
        }
    }

    /**
     * This function builds a convoy of enemies and registers them as game objects.
     */
    public void buildEnemies(){
        _convoy.build(this._applet);
        this.RegisterGameObjects(_convoy.getAliens()
                .stream()
                .map(c-> (GameObject)c)
                .collect(Collectors.toList()));
    }

    public void buildHealthCounter(){
        float y = 16;
        float x = this._applet.width - 3*12 + 24;

        for(int i = 0; i < this.healthPoints; ++i){
            var healthPoint = new UIImage(this._applet, x - 16*i, y, 0);
            healthPoint.buildComponent();
            this.HealthPointStack.push(healthPoint);
        }
    }

    /**
     * The function builds the game scene by creating enemies, a highscore display, and a timer that drops
     * aliens periodically.
     */
    public void buildScene(){
        buildEnemies();
        buildHighscore();
        buildHealthCounter();

        dropTimer = new Timer();
        var task = new TimerTask() {
            @Override
            public void run() {
                for(int i = 0; i < 3; i++){
                    _convoy.dropAlien(_applet, GameState.PlayerOne);
                }
            }
        };
        dropTimer.scheduleAtFixedRate(task, 3500, 3500);

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
        var projectilesPlayerOne = GameState.PlayerOne.getProjectiles();
        var projectilesToDelete = new ArrayList<Projectile>();

        for (var projectile: projectilesPlayerOne) {
            if(!projectile.isVisible())
                continue;
            for (var e: this._convoy.getAliens()){
                if(e.isVisible()){
                    if(e.intersect(projectile)){
                        e.takeDamage(100);
                        if(!e.isAlive()){
                            e.toggleVisibility();
                        }
                        projectilesToDelete.add(projectile);
                        GameState.Highscore += 10;
                    }
                }
            }
        }
        projectilesPlayerOne.removeAll(projectilesToDelete);

        for(var alien: this._convoy.getAliens()){
            if(alien.intersect(GameState.PlayerOne) && alien.isVisible()){
                alien.toggleVisibility();
                this.HealthPointStack.pop();
            }
        }
    }
}
