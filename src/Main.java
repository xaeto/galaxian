import models.GameState;
import scenes.IntroScene;
import scenes.MainMenu;
import processing.core.PApplet;
import processing.event.KeyEvent;
import scenes.GameScene;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends PApplet {
    private int[] activeKeys = new int[256];
    
    public static void main(String[] args) {
        PApplet.main(Main.class);
    }

    /**
     * This function checks for key presses and updates the game state accordingly, then draws the current
     * scene.
     */
    @Override
    public void draw() {
        boolean right_pressed = activeKeys[68]  == 1;
        boolean left_pressed = activeKeys[65]  == 1;
        boolean space_pressed = activeKeys[32]  == 1;

        if(GameState.CurrentScene instanceof GameScene scene) {
            if(right_pressed){
                GameState.PlayerOne.moveRight();
            }
            if(left_pressed){
                GameState.PlayerOne.moveLeft();
            }
            if(space_pressed){
                scene.playerShoot();
            }
        }
        GameState.CurrentScene.drawScene();
    }

    /**
     * This is a function that handles key presses and performs different actions based on the current
     * game state.
     * 
     * @param event A KeyEvent object representing a key press event.
     */
    @Override
    public void keyPressed(KeyEvent event){
        int keyCode = event.getKeyCode();
        if (keyCode > 255) {
            return;
        }

        if(GameState.CurrentScene instanceof IntroScene){
            if(event.getKeyCode() == 10){
                GameState.CurrentScene = new MainMenu(this, width, height);
                GameState.CurrentScene.buildScene();
            }
            return;
        }

        if(GameState.CurrentScene instanceof MainMenu scene) {
            // move right
            if(keyCode == 87){
                scene.increasePlayerCount();
            }
            if(keyCode == 83){
                scene.decreasePlayerCount();
            }
            if(keyCode == 10){
                int players = scene.initializeGame();
                if (players == 1) {
                    GameState.PlayerOne = GameScene.InitializePlayer(this);
                } else  if(players == 2){
                    GameState.PlayerOne = GameScene.InitializePlayer(this);
                    GameState.PlayerTwo = GameScene.InitializePlayerTwo(this);
                }
                GameScene game_scene = new GameScene(this, width, height);
                GameState.Highscore = 0;
                game_scene.addPlayers(GameState.PlayerOne, GameState.PlayerTwo);
                game_scene.buildScene();
                GameState.CurrentScene = game_scene;
            }
            return;
        }
        if(keyCode == 32) {
            if(GameState.CurrentScene instanceof GameScene scene) {
                scene.playerShoot();
            }
            return;
        }
        if(keyCode == 10) {
            GameState.Paused = !GameState.Paused;
        }
        activeKeys[keyCode] = 1;
    }

    /**
     * This is an overridden function that handles key releases. It takes a KeyEvent
     * object as a parameter,
     * which represents a key release event. The function extracts the key code from
     * the event and sets the
     * corresponding value in the activeKeys array to 0, indicating that the key is
     * no longer being pressed.
     */
    @Override
    public void keyReleased(KeyEvent event){
        int keyCode = event.getKeyCode();
        if (keyCode > 255) {
            return;
        }
        activeKeys[keyCode] = 0;
    }

    @Override
    public void setup() {
        GameState.CurrentScene = new IntroScene(this, width, height);
        GameState.CurrentScene.buildScene();
        frameRate(60);
        smooth();
        super.setup();
    }

    /**
     * This function sets the size of the window to 800x600 in Java.
     */
    @Override
    public void settings(){
        setSize(800, 600);
        super.settings();
    }
}
