package scenes;

import constants.TextureConstants;
import logic.BoundsLogic;
import models.*;
import models.ui_elements.UIImage;
import models.ui_elements.UILabel;
import models.ui_elements.UILabelColor;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.*;
import java.util.stream.Collectors;

public class GameScene extends Scene {
    private BoundsLogic _boundsLogic;
    private AlienConvoy _convoy;
    private Timer dropTimer;
    private Timer alienTask;
    private UILabel scoreLabel;
    private boolean isGameOver = false;
    private int stage = 1;
    private final int healthPoints = 5;
    private boolean healthChanged = false;
    private Stack<UIImage> HealthPointStack = new Stack<>();

    /** This is a constructor for the `GameScene` class that takes in an instance of `PApplet` and the
     * width and height of the scene. It calls the constructor of the parent `Scene` class with the
     * `applet` and creates an image with the specified width and height using `applet.createImage()`. 
     * It also initializes a new `BoundsLogic` object with the same width and height.
    */ 
    public GameScene(PApplet applet, int width, int height) {
        super(applet, applet.createImage(width, height, 0));
        _convoy = new AlienConvoy(this._applet);
        _boundsLogic = new BoundsLogic(width, height);
    }

    public void playerShoot(Player player){
        if(!player.canShoot){
            return;
        }
        var proj = player.shoot(this._applet);
        RegisterGameObject(proj);
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

    public void addPlayers(Player ... players){
        for(Player player: players) {
            RegisterGameObject(player);
        }
    }

    /**
    The `drawScene()` method is responsible for updating and rendering the game scene. It first updates
     the score label with the current high score value. Then, it handles the constraints of all game
     objects using the `_boundsLogic` object. It then moves and updates the direction of the alien
     convoy using the `_convoy` object. It then updates the attacking enemies using the
     `updateAttackingEnemies()` method, detects collisions using the `detectCollision()` method, and
     checks for dead enemies using the `checkDeadEnemies()` method. Finally, it calls the `drawScene()`
     method of the parent `Scene` class to render all game objects on the screen.
     */
    @Override
    public void drawScene(){
        this.scoreLabel.setText(String.valueOf(GameState.Highscore), UILabelColor.Green, 1);

        _boundsLogic.handleConstraints(this.getGameObjects());

        if(!_convoy.isStageCleared()){
            _convoy.moveConvoy();
            _convoy.updateDirection();
            updateAttackingEnemies();
            detectCollision();
        } else {
            _convoy.reset();
            this.stage += 1;
            alienTask.cancel();
        }

        super.drawScene();
        for(var healthPoint: this.HealthPointStack){
            healthPoint.drawComponent();
        }

        if(this.HealthPointStack.stream().count() == 0){
            GameState.CurrentScene = new MainMenu(this._applet, this._applet.width, this._applet.height);
            GameState.CurrentScene.buildScene();
        }
    }

    /**
     * The function starts an attack by creating a timer that periodically creates a projectile aimed at
     * the player's position.
     *
     * @param applet The PApplet object that is used to draw the game and handle user input.
     * @param player The player parameter is an instance of the Player class, which represents the player
     * character in the game. It is used in the startAttack method to calculate the angle at which the
     * enemy should shoot its projectile towards the player.
     */
    public Projectile startAttack(PApplet applet, Alien alien, Player player){
        alien.canShoot = false;
        int speed = 4 + this.stage;
        var projectile = new Projectile(
                applet,
                alien.getX(),
                alien.getY(),
                speed,
                ProjectileSource.Enemy
        );
        projectile.setDestination(player.getPosition().copy());
        projectile.setup(applet);
        var task = new TimerTask() {
            @Override
            public void run() {
                alien.canShoot = true;
            }
        };
        var timer = new Timer();
        timer.schedule(task, 3000);

        return projectile;
    }
    /**
     * This function updates the attacking enemies' velocity towards the player's position.
     */
    private void updateAttackingEnemies(){
        // update attacking aliens
        for(GameObject obj: this.getGameObjects()){
            if(!(obj instanceof Alien alien))
                continue;
            if(alien.isInConvoy())
                continue;

            Player p = GameState.PlayerOne;
            if(GameState.PlayerTwo != null){
                float playerOneDist = obj.getPosition().dist(GameState.PlayerOne.getPosition());
                float playerTwoDist = obj.getPosition().dist(GameState.PlayerTwo.getPosition());
                if(playerTwoDist < playerOneDist){
                    p = GameState.PlayerTwo;
                }
            }

            PVector dir = PVector.sub(p.getPosition(), alien.getPosition());
            dir.normalize();
            alien.getVelocity().set(dir);

            if(alien.canShoot && alien.isVisible()){
                var proj = startAttack(_applet, alien, p);
                RegisterGameObject(proj);
            }
        }

        for(GameObject obj: this.getGameObjects()){
            if(this._convoy.getAliens().stream().anyMatch(c -> c == obj)){
                continue;
            }
            if(!(obj instanceof Alien alien))
                continue;

            Player p = GameState.PlayerOne;
            if(GameState.PlayerTwo != null){
                float playerOneDist = obj.getPosition().dist(GameState.PlayerOne.getPosition());
                float playerTwoDist = obj.getPosition().dist(GameState.PlayerTwo.getPosition());
                if(playerTwoDist < playerOneDist){
                    p = GameState.PlayerTwo;
                }
            }
            PVector dir = PVector.sub(p.getPosition(), alien.getPosition());
            dir.normalize();
            alien.getVelocity().set(dir);
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

    /**
     * This function builds a health counter by creating a stack of UIImage objects representing the
     * player's health points.
     */
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
                _convoy.dropAlien(_applet);
            }
        };
        dropTimer.scheduleAtFixedRate(task, 3500, 3500);

        this.alienTask = new Timer();
        var t = new TimerTask(){
            @Override
            public void run() {
                var greenAlien = new GreenAlien(_applet, 0, (int)_applet.random(_applet.height/2));
                greenAlien.setup(_applet);
                greenAlien.setPartOfConvoy(false);
                RegisterGameObject(greenAlien);
            }
        };
        this.alienTask.scheduleAtFixedRate(t, 1000, 7500);

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
        var projectilesToDelete = new ArrayList<Projectile>();

        for (var obj: this.getGameObjects()) {
            if(obj instanceof Projectile proj){
                if(proj.getProjectileSource() == ProjectileSource.Enemy){
                    if(proj.intersect(GameState.PlayerOne)){
                        projectilesToDelete.add(proj);
                        HealthPointStack.pop();
                    }
                    if(GameState.PlayerTwo != null){
                        if(proj.intersect(GameState.PlayerTwo)){
                            projectilesToDelete.add(proj);
                            HealthPointStack.pop();
                        }
                    }
                } else if (proj.getProjectileSource() == ProjectileSource.Player){
                    for(var subObj: this.getGameObjects()){
                        if(!(subObj instanceof Alien alien))
                            continue;
                        if(!alien.isVisible())
                            continue;
                        if(proj.intersect(alien)){
                            GameState.Highscore += 10;
                            projectilesToDelete.add(proj);
                            alien.toggleVisibility();
                            break;
                        }
                    }
                }
            }
        }

        for(var obj: this.getGameObjects()){
            if(obj instanceof Alien alien && alien.isVisible()){
                if(alien.intersect(GameState.PlayerOne)){
                    alien.toggleVisibility();
                    if(HealthPointStack.size() > 0){
                        HealthPointStack.pop();
                    }
                }
                if(GameState.PlayerTwo != null){
                    if(alien.intersect(GameState.PlayerTwo)){
                        alien.toggleVisibility();
                        if(HealthPointStack.size() > 0){
                            HealthPointStack.pop();
                        }
                    }
                }
            }
        }
        this.getGameObjects().removeAll(projectilesToDelete);
    }
}
