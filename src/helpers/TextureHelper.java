package helpers;

import processing.core.PApplet;
import processing.core.PImage;

public class TextureHelper {
    private static PImage Sprites;
    public static PImage loadSpriteMap(PApplet applet){
        if (Sprites == null) {
            var img = applet.loadImage("./assets/sprites_normal_x2.png");
            Sprites = img;
            return Sprites;
        }
        return Sprites;
    }
}
