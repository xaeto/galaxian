import models.GameState;
import scenes.MainMenu;
import processing.core.PApplet;
import processing.event.KeyEvent;
import scenes.GameScene;

public class Main extends PApplet {
    private GameScene _gameScene;
    private int[] activeKeys = new int[256];
    public static void main(String[] args) {
        PApplet.main(Main.class);
    }

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
                GameState.PlayerOne.shoot(this);
            }
        }
        GameState.CurrentScene.drawScene();
    }

    @Override
    public void keyPressed(KeyEvent event){
        int keyCode = event.getKeyCode();
        if (keyCode > 255) {
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
                game_scene.addPlayers(GameState.PlayerOne, GameState.PlayerTwo);
                game_scene.buildScene();
                GameState.CurrentScene = game_scene;
            }
            return;
        }
        if(keyCode == 32) {
            if(GameState.CurrentScene instanceof GameScene scene) {

                GameState.PlayerOne.shoot(this);
            }
            return;
        }
        if(keyCode == 10) {
            GameState.Paused = !GameState.Paused;
        }
        activeKeys[keyCode] = 1;
    }

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
        MainMenu _menu = new MainMenu(this, width, height);
        GameState.CurrentScene = _menu;
        super.setup();
    }

    @Override
    public void settings(){
        setSize(800, 600);
        super.settings();
    }
}
