package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import processing.core.PApplet;
import spritelib.*;

import java.util.Timer;
import java.util.TimerTask;

public class YellowShip extends Alien {
    public YellowShip(PApplet applet, int x, int y) {
        super(applet, x, y, TextureConstants.YellowEnemyWidth, TextureConstants.YellowEnemyHeight);
    }

    public void setup(PApplet applet){
        var seqSprite = new SequencedSprite(TextureConstants.YellowEnemyWidth, TextureConstants.YellowEnemyHeight, 1000, ANCHORTYPE.TOP_LEFT);
        var img = TextureHelper.loadSpriteMap(applet);
        seqSprite.addFrames(applet, img, TextureConstants.GridGap, TextureConstants.YellowEnemyOffsetY, 2);

        var dieSequence = new Sequence("die");
        dieSequence.addRange(0, 1);
        seqSprite.addSequence(
                dieSequence
        );
        var idleSequence = new Sequence("idle");
        idleSequence.addRange(0, 1);
        seqSprite.addSequence(
                idleSequence
        );
        var attackSequence = new Sequence("attacking");
        attackSequence.addRange(0, 1);
        seqSprite.addSequence(
                attackSequence
        );
        seqSprite.gotoSequence("idle");
        sprite = seqSprite;
    }

    @Override
    public void startAttack(PApplet applet, Player player){
        this.shootTimer = new Timer();
        var task = new TimerTask() {
            @Override
            public void run() {
                var projectile = new Projectile(
                        applet,
                        getX(),
                        getY(),
                        10,
                        ProjectileSource.Enemy
                );

                var projectileTwo = new Projectile(
                        applet,
                        getX(),
                        getY(),
                        10,
                        ProjectileSource.Enemy
                );
                projectile.setup(applet);

                var dest = player.getPosition().copy();
                dest.x += applet.random(1);
                projectile.setDestination(dest);
                projectiles.add(projectile);

                var destTwo = player.getPosition().copy();
                destTwo.x += applet.random(1);
                projectileTwo.setDestination(destTwo);
                projectiles.add(projectileTwo);
            }
        };
        shootTimer.scheduleAtFixedRate(task, 500, 1000);
    }
}
