import scenes.MainMenu;
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

        System.out.println(keyCode);
        if(GameState.CurrentScene instanceof MainMenu) {
            var scene = (MainMenu)GameState.CurrentScene;
            // move right
            if(keyCode == 525 || keyCode == 83){
                scene.increasePlayerCount();
            }
            if(keyCode == 47 || keyCode == 87){
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
        }

        if(GameState.CurrentScene instanceof GameScene) {
            // move right
            if(keyCode == 68){
                GameState.PlayerOne.moveRight();
            }
            if(keyCode == 65){
                GameState.PlayerOne.moveLeft();
            }
        }
    }

    @Override
    public void setup() {
        _menu = new MainMenu(this, width, height);
        GameState.CurrentScene = _menu;
    }

    @Override
    public void settings(){
        setSize(800, 600);
        super.settings();
    }
}
