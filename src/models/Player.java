package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import processing.core.PApplet;
import processing.sound.SoundFile;
import spritelib.*;

import java.util.Timer;
import java.util.TimerTask;

public class Player extends GameObject {
    public Player(PApplet applet, float x, float y) {
        super(applet, x, 550, 48, 48);
        this.Health = 300;
    }

    /**
     * Sets up a sequenced sprite with different frames and sequences for a game character.
    * 
    * @param applet The PApplet object that is used to load and display the sprite images.
    */
    public void setup(PApplet applet){
        var seqSprite = new SequencedSprite(TextureConstants.PlayerWidth, TextureConstants.PlayerHeight, 30, ANCHORTYPE.TOP_LEFT);
        var img = TextureHelper.loadSpriteMap(applet);
        seqSprite.addFrames(
                applet,
                img,
                TextureConstants.GridGap, TextureConstants.PlayerOffsetY, 2
        );
        seqSprite.addFrames(applet, img, TextureConstants.RedEnemyOffsetX, TextureConstants.RedEnemyOffsetY, 2);
        var idleSequence = new Sequence("idle");
        idleSequence.addFrame(1);
        seqSprite.addSequence(idleSequence);

        var dieSequence = new Sequence("die");
        dieSequence.addRange(2, 5);
        seqSprite.addSequence(
                dieSequence
        );

        this.sprite = seqSprite;
    }

    /**
     * This function draws a sprite on a PApplet and changes its sequence based on the health of the
     * object.
     * 
     * @param applet The PApplet object that is used for drawing the sprite. It is a parameter of the
     * draw() method, which is called by the PApplet's draw() method in the main game loop.
     */
    @Override
    public void draw(PApplet applet) {
        sprite.draw(applet, new Point(this.getX(), this.getY()));
        if(this.Health > 0){
            ((SequencedSprite)sprite).gotoSequence("idle");
        }
        super.draw(applet);
    }

    /**
     * This function removes all projectiles from a list.
     */

    /**
     * The function sets the velocity of an object to move left at a speed of 4 units per frame.
     */
    public void moveLeft(){
        this.velocity.x = -4;
    }

    // The `moveRight()` method is setting the `x` component of the `velocity` vector of the `Player`
    // object to a positive value of 4, which will cause the player to move to the right when its
    // `update()` method is called.
    public void moveRight(){
        this.velocity.x = 4;
    }

    /**
     * This function creates a new projectile object and adds it to a list of projectiles if there are no
     * visible projectiles already, and plays a shooting sound.
     * 
     * @param applet The PApplet object that is used to draw and interact with the sketch.
     */
    @Override
    public Projectile shoot(PApplet applet){
        canShoot = false;

        float dx = (this.getX() + 14);
        float dy =  (this.getY()+7.0f/2);
        var projectile = new Projectile(applet, dx, dy, 10, ProjectileSource.Player);
        projectile.setup(applet);

        SoundFile shootSound = new SoundFile(this._applet, "./assets/sounds/enemy_fire.mp3", true);
        shootSound.play();

        var t = new Timer();
        var resetTask = new TimerTask(){
            @Override
            public void run() {
                canShoot = true;
            }
        };
        t.schedule(resetTask, 600);

        return projectile;
    }
}
