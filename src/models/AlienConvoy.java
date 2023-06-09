package models;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Comparator;

public class AlienConvoy {
    private ArrayList<Alien> Aliens = new ArrayList<>();
    private Direction direction = Direction.RIGHT;
    private boolean dropDir = false;

    public ArrayList<Alien> GetAliens() {
        return Aliens;
    }

    public boolean getDropDir(){
        return this.dropDir;
    }

    // The `updateDirection()` method is updating the direction of the AlienConvoy based on the position of
    // the leftmost and rightmost aliens in the convoy. It first filters the list of aliens to only include
    // those that are part of the convoy, then finds the leftmost and rightmost aliens using the `min()`
    // and `max()` methods respectively. If the rightmost alien is at the right edge of the screen, the
    // direction is set to LEFT, and if the leftmost alien is at the left edge of the screen, the direction
    // is set to RIGHT.
    public void updateDirection(){
        GameObject leftObj = Aliens.stream().filter(c-> c.partOfConvoy == true).min(Comparator.comparing(GameObject::getX)).get();
        GameObject rightObj = Aliens.stream().filter(c-> c.partOfConvoy == true).max(Comparator.comparing(GameObject::getX)).get();

        if(rightObj.getX() == 768.0 - rightObj.width){
            this.direction = Direction.LEFT;
        }

        if(leftObj.getX() == 34.0){
            this.direction = Direction.RIGHT;
        }
    }

 /**
  * This function drops an alien from either the left or right side of the screen and starts an attack
  * on the player.
  * 
  * @param applet an instance of the PApplet class, which is the main class for processing sketches in
  * the Processing environment.
  * @param player The "player" parameter is an object of the "Player" class, which is likely a
  * representation of the player-controlled character in the game. It is being passed into the
  * "dropAlien" method as an argument, likely because the method needs to interact with the player in
  * some way (e
  */
    public void dropAlien(PApplet applet, Player player){
        Alien leftObj = Aliens.stream().min(Comparator.comparing(GameObject::getX)).get();
        Alien rightObj = Aliens.stream().max(Comparator.comparing(GameObject::getX)).get();

        float screen_side_left = applet.width /2 - leftObj.getX();
        float screen_side_right = applet.width /2 + rightObj.getX();

        System.out.println("left: " + screen_side_left);
        System.out.println("right: " + screen_side_right);
        if(this.dropDir){
            leftObj.startAttack(applet, player);
            leftObj.partOfConvoy = false;
        } else {
            rightObj.startAttack(applet, player);
            rightObj.partOfConvoy = false;
        }
        // alternate drop side
        this.dropDir = !this.dropDir;
    }

    // The `isStageCleared()` method is checking if the `Aliens` ArrayList is empty, and returning a
    // boolean value indicating whether the stage is cleared. If the ArrayList is empty, it means
    // that all the aliens have been defeated and the stage is cleared.
    public boolean isStageCleared(){
        return this.Aliens.size() == 0;
    }

    /**
     * This function moves a convoy of aliens either left or right by adjusting their x-coordinates.
    */
    public void moveConvoy(){
        for(Alien alien: Aliens){
            if(!alien.partOfConvoy)
                continue;
            if(this.direction == Direction.LEFT){
                alien.x -= 2;
            } else {
                alien.x += 2;
            }
        }
    }

    // The `build()` method is initializing a convoy of aliens by creating instances of different alien
    // classes (`YellowShip`, `RedAlien`, `PurpleAlien`, and `GreenAlien`) and adding them to the `Aliens`
    // ArrayList. The method takes an instance of the `PApplet` class as a parameter, which is used to call
    // the `setup()` method on each alien instance to initialize its appearance and behavior. The aliens
    // are positioned on the screen using specific x and y coordinates, which are calculated based on the
    // alien's type and position in the convoy.
    public void build(PApplet applet){
        // initialize yellow ships
        for(int i = 0; i < 4; i++){
            var enemy = new YellowShip(32*(i + 2) + 300 -2, 40);
            enemy.setup(applet);
            Aliens.add(enemy);
        }

        // initialize red ships
        for(int i = 0; i < 6; i++){
            var enemy = new RedAlien(48*(i + 2) + 300 - 100 -2, 40*2);
            enemy.setup(applet);
            Aliens.add(enemy);
        }
        // initialize purple ships
        for(int i = 0; i < 8; i++){
            var enemy = new PurpleAlien(48*(i + 2) + 300/2 -2, 40*3);
            enemy.setup(applet);
            Aliens.add(enemy);
        }

        // initialize green ships
        for(int j =0; j < 10; ++j) {
            var enemy = new GreenAlien(48*(j + 1) + 300/2 -2, 40*4);
            enemy.setup(applet);
            Aliens.add(enemy);
        }
        for(int j =0; j < 10; ++j) {
            var enemy = new GreenAlien(48*(j + 1) + 300/2 -2, 40*5);
            enemy.setup(applet);
            Aliens.add(enemy);
        }
        for(int j =0; j < 10; ++j) {
            var enemy = new GreenAlien(48*(j + 1) + 300/2 -2, 40*6);
            enemy.setup(applet);
            Aliens.add(enemy);
        }
    }
}
