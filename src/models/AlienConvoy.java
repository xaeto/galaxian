package models;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.*;

public class AlienConvoy {
    private ArrayList<Alien> Aliens = new ArrayList();
    private Direction direction = Direction.RIGHT;
    private boolean dropDir = false;

    public List<Alien> getAliens() {
        return Aliens.stream().toList();
    }

    private PApplet _applet;
    private boolean isStageBuild = false;
    public AlienConvoy(PApplet applet){
        _applet = applet;
    }

    // The `updateDirection()` method is updating the direction of the AlienConvoy based on the position of
    // the leftmost and rightmost aliens in the convoy. It first filters the list of aliens to only include
    // those that are part of the convoy, then finds the leftmost and rightmost aliens using the `min()`
    // and `max()` methods respectively. If the rightmost alien is at the right edge of the screen, the
    // direction is set to LEFT, and if the leftmost alien is at the left edge of the screen, the direction
    // is set to RIGHT.
    public void updateDirection(){
        if(Aliens.stream().noneMatch(c->c.partOfConvoy == true)){
            return;
        }
        if(!isStageBuild){
            return;
        }
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
        if(Aliens.stream().filter(c-> c.isVisible()).count() == 0){
            return;
        }
        Alien leftObj = Aliens.stream().min(Comparator.comparing(GameObject::getX)).get();
        Alien rightObj = Aliens.stream().max(Comparator.comparing(GameObject::getX)).get();

        if(this.dropDir){
            leftObj.partOfConvoy = false;
        } else {
            rightObj.partOfConvoy = false;
        }
        // alternate drop side
        this.dropDir = !this.dropDir;
    }

    // The `isStageCleared()` method is checking if the `Aliens` ArrayList is empty, and returning a
    // boolean value indicating whether the stage is cleared. If the ArrayList is empty, it means
    // that all the aliens have been defeated and the stage is cleared.
    public boolean isStageCleared(){
        return this.Aliens.stream().filter(c-> c.isVisible()).count() == 0;
    }

    public void setStageState(boolean state){
        this.isStageBuild = state;
    }

    /**
     * This function moves a convoy of aliens either left or right by adjusting their x-coordinates.
    */
    public void moveConvoy(){
        for(Alien alien: Aliens){
            if(!alien.partOfConvoy)
                continue;
            if(this.direction == Direction.LEFT){
                alien.velocity.x = -2;
            } else {
                alien.velocity.x = 2;
            }
        }
    }

    public void reset(){
        // initialize yellow ships
        for(int i = 0; i < 4; i++){
            var enemy = this.Aliens.get(i);
            enemy.setX( 32*(i + 2) + 300 -2);
            enemy.setY(40);
            enemy.reset();
        }
        int offset = 4;

        // initialize red ships
        for(int i = 0; i < 6; i++){
            var enemy = this.Aliens.get(offset + i);
            enemy.setX(48*(i + 2) + 300 - 100 -2);
            enemy.setY(40*2);
            enemy.reset();
        }
        offset += 6;
        // initialize purple ships
        for(int i = 0; i < 8; i++){
            var enemy = this.Aliens.get(offset + i);
            enemy.setX(48*(i + 2) + 300/2 -2);
            enemy.setY(40*3);
            enemy.reset();
        }

        offset += 8;
        // initialize green ships
        for(int j =0; j < 10; ++j) {
            var enemy = this.Aliens.get(offset + j);
            enemy.setX(48*(j + 1) + 300/2 -2);
            enemy.setY(40*4);
            enemy.reset();
        }
        offset += 10;
        for(int j =0; j < 10; ++j) {
            var enemy = this.Aliens.get(offset + j);
            enemy.setX(48*(j + 1) + 300/2 -2);
            enemy.setY(40*5);
            enemy.reset();
        }
        offset += 10;
        for(int j =0; j < 10; ++j) {
            var enemy = this.Aliens.get(offset + j);
            enemy.setX(48*(j + 1) + 300/2 -2);
            enemy.setY(40*6);
            enemy.reset();
        }

        isStageBuild = true;
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
            var enemy = new YellowShip(this._applet, 32*(i + 2) + 300 -2, 40);
            enemy.setup(applet);
            Aliens.add(enemy);
        }

        // initialize red ships
        for(int i = 0; i < 6; i++){
            var enemy = new RedAlien(this._applet, 48*(i + 2) + 300 - 100 -2, 40*2);
            enemy.setup(applet);
            Aliens.add(enemy);
        }
        // initialize purple ships
        for(int i = 0; i < 8; i++){
            var enemy = new PurpleAlien(this._applet, 48*(i + 2) + 300/2 -2, 40*3);
            enemy.setup(applet);
            Aliens.add(enemy);
        }

        // initialize green ships
        for(int j =0; j < 10; ++j) {
            var enemy = new GreenAlien(this._applet, 48*(j + 1) + 300/2 -2, 40*4);
            enemy.setup(applet);
            Aliens.add(enemy);
        }
        for(int j =0; j < 10; ++j) {
            var enemy = new GreenAlien(this._applet,48*(j + 1) + 300/2 -2, 40*5);
            enemy.setup(applet);
            Aliens.add(enemy);
        }
        for(int j =0; j < 10; ++j) {
            var enemy = new GreenAlien(this._applet,48*(j + 1) + 300/2 -2, 40*6);
            enemy.setup(applet);
            Aliens.add(enemy);
        }

        isStageBuild = true;
    }
}
