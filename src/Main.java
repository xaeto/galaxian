import scenes.MainMenu;
import models.Player;
import processing.core.PApplet;
import processing.event.KeyEvent;
import scenes.GameScene;

public class Main extends PApplet {
    private MainMenu _menu;
    private GameScene _gameScene;
    public static void main(String[] args) {
        PApplet.main(Main.class);
    }

    @Override
    public void draw() {
        // draw currentScene, saved in GameState
        GameState.CurrentScene.drawScene();
        // _gameScene.drawScene();
    }

    @Override
    public void keyPressed(KeyEvent event){
        int keyCode = event.getKeyCode();

        // move right
        if(keyCode == 39){
            GameState.Player.moveRight();
        }
        // move left
        if(keyCode == 37){
            GameState.Player.moveLeft();
        }
    }

    @Override
    public void setup() {
        _menu = new MainMenu(this, width, height);
        GameState.CurrentScene = _menu;
        _gameScene = new GameScene(this, width, height);
        GameState.Player = _gameScene.InitializePlayer();
        _gameScene.RegisterGameObject(GameState.Player);
    }

    @Override
    public void settings(){
        setSize(800, 600);
        super.settings();
    }
}
