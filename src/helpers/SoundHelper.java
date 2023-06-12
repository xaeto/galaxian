package helpers;

import processing.core.PApplet;
import processing.sound.SoundFile;

public class SoundHelper {
    public static void playShootSound(PApplet applet){
        var sound = new SoundFile(applet, "./assets/sounds/enemy_fire.mp3", true);
        sound.play();
    }

    public static void loopBackground(PApplet applet){
        var sound = new SoundFile(applet, "./assets/sounds/background.mp3", true);
        sound.amp(0.3f);
        sound.loop();
    }

    public static void playExplosion(PApplet applet){
        var sound = new SoundFile(applet, "./assets/sounds/enemy_explosion.mp3", true);
        sound.amp(0.05f);
        sound.play();
    }

    public static void playJumpSound(PApplet applet){
        var sound = new SoundFile(applet, "./assets/sounds/enemy_jumping.mp3", true);
        sound.play();
    }
}
