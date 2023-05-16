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
    }

    @Override
    public void keyPressed(KeyEvent event){
        int keyCode = event.getKeyCode();

        // move right
        if(keyCode == 39){
            System.out.println("Switching to MainMenu");
            GameState.CurrentScene = _menu;
        }
        // move left
        if(keyCode == 37){
            System.out.println("Switching to GameScene");
            GameState.CurrentScene = _gameScene;
        }
    }

    @Override
    public void setup() {
        _menu = new MainMenu(this, width, height);
        GameState.CurrentScene = _menu;
        _gameScene = new GameScene(this, width, height);
    }

    @Override
    public void settings(){
        setSize(800, 600);
        super.settings();
    }
}
