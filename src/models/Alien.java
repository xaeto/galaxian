package models;

import processing.core.PApplet;
import processing.core.PVector;
import spritelib.Point;

import java.util.Timer;
import java.util.TimerTask;

import static processing.core.PApplet.degrees;

public class Alien extends Enemy {
    protected boolean partOfConvoy = true;
    protected Timer shootTimer;

    public Alien(PApplet applet, float x, float y, int width, int height) {
        super(applet, x, y, width, height);
    }

    @Override
    public void draw(PApplet applet){
        sprite.draw(applet, new Point(this.getX(), this.getY()));
        if(!this.isVisible() && this.shootTimer != null){
            shootTimer.purge();
            shootTimer.cancel();
        }
        super.draw(applet);
    }

    /**
     * The function returns a boolean value indicating whether the object is part of a convoy or not.
     *
     * @return A boolean value indicating whether the object is part of a convoy or not.
     */
    public boolean isInConvoy(){
        return this.partOfConvoy;
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
    public void startAttack(PApplet applet, Player player){
        this.shootTimer = new Timer();
        var task = new TimerTask() {
            @Override
            public void run() {
                var projectile = new Projectile(
                        applet,
                        getX(),
                        getY(),
                        8,
                        ProjectileSource.Enemy
                );

                projectile.setDestination(player.getPosition().copy());
                projectiles.add(projectile);
                projectile.setup(applet);
            }
        };
        shootTimer.scheduleAtFixedRate(task, 500, 1000);
    }
}
