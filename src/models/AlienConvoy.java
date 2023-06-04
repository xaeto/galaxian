package models;

import processing.core.PApplet;
import scenes.GameScene;

import java.util.ArrayList;
import java.util.Comparator;

public class AlienConvoy {
    private ArrayList<GameObject> Ships = new ArrayList<>();
    private Direction direction = Direction.RIGHT;

    public ArrayList<GameObject> GetShips() {
        return Ships;
    }

    public void updateDirection(){
        GameObject leftObj = Ships.stream().min(Comparator.comparing(GameObject::getX)).get();
        GameObject rightObj = Ships.stream().max(Comparator.comparing(GameObject::getX)).get();

        if(rightObj.getX() == 768.0 - rightObj.width){
            this.direction = Direction.LEFT;
        }

        if(leftObj.getX() == 34.0){
            System.out.println("test");
            this.direction = Direction.RIGHT;
        }
    }
    public void moveConvoy(){
        for(GameObject ship: Ships){
            if(this.direction == Direction.LEFT){
                ship.x -= 2;
            } else {
                ship.x += 2;
            }
        }
    }

    public void build(PApplet applet){
        ArrayList<GameObject> enemies = new ArrayList<>();

        // initialize yellow ships
        for(int i = 0; i < 4; i++){
            var enemy = new YellowShip(32*(i + 2) + 300 -2, 40);
            enemy.setup(applet);
            Ships.add(enemy);
        }

        // initialize red ships
        for(int i = 0; i < 6; i++){
            var enemy = new RedAlien(48*(i + 2) + 300 - 100 -2, 40*2);
            enemy.setup(applet);
            Ships.add(enemy);
        }
        // initialize purple ships
        for(int i = 0; i < 8; i++){
            var enemy = new PurpleAlien(48*(i + 2) + 300/2 -2, 40*3);
            enemy.setup(applet);
            Ships.add(enemy);
        }

        // initialize green ships
        for(int j =0; j < 10; ++j) {
            var enemy = new GreenAlien(48*(j + 1) + 300/2 -2, 40*4);
            enemy.setup(applet);
            Ships.add(enemy);
        }
        for(int j =0; j < 10; ++j) {
            var enemy = new GreenAlien(48*(j + 1) + 300/2 -2, 40*5);
            enemy.setup(applet);
            Ships.add(enemy);
        }
        for(int j =0; j < 10; ++j) {
            var enemy = new GreenAlien(48*(j + 1) + 300/2 -2, 40*6);
            enemy.setup(applet);
            Ships.add(enemy);
        }
    }
}
