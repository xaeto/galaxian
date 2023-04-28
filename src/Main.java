import models.GameObject;
import models.Player;
import processing.core.PApplet;
import processing.event.KeyEvent;

import java.util.ArrayList;

public class Main extends PApplet {
    private Player player;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main(Main.class);
    }

    @Override
    public void draw() {
        background(42);
        for(int i=0; i < gameObjects.size(); ++i) {
            GameObject obj = gameObjects.get(i);
            obj.draw(this);
        }
    }

    @Override
    public void keyPressed(KeyEvent event){
        int keyCode = event.getKeyCode();
        System.out.println(keyCode);

        // move right
        if(keyCode == 39){
            player.moveRight();
        }
        // move left
        if(keyCode == 37){
            player.moveLeft();
        }
    }

    @Override
    public void setup() {
        Player player = new Player(width/2, height -25);
        this.player = player;
        gameObjects.add(player);
    }

    @Override
    public void settings(){
        setSize(800, 600);
        super.settings();
    }
}