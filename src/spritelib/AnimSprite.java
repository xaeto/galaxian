package spritelib;

import processing.core.PApplet;

/**
 * @author Kai Hinkelmann, kai.hinkelmann@fh-kiel.de
 * The type Anim sprite.
 * spritelib.AnimSprite cycles through the sprites in the baseclass automatically
 */
public class AnimSprite extends MultiSprite
{
    private final int framerate;         // the framerate of the sprite: 1 = 1000 frames/sec, 100 = 10 frames/sec etc.
    private float nextFrameAt = 0;   // stores the millis-value for the next frameflip

    /**
     * Instantiates a new Anim sprite. Calls the constr. of the baseclass
     *
     * @param width     the width
     * @param height    the height
     * @param framerate the framerate
     */
    public AnimSprite( int width, int height, int framerate)
    {
        super( width, height );
        this.framerate = framerate;
    }

    /**
     * Instantiates a new Anim sprite. Calls the constr. of the baseclass
     *
     * @param width     the width
     * @param height    the height
     * @param framerate the framerate
     * @param anchor    the anchor defines where the sprite is drawn relative to pos-parameter passed in spritelib.Sprite.draw()
     */
    public AnimSprite( int width, int height, int framerate, ANCHORTYPE anchor )
    {
        super( width, height, anchor );
        this.framerate = framerate;
    }

    @Override
    public void draw( PApplet applet, Point newPos )
    {
        super.draw( applet,newPos );
        if ( nextFrameAt < applet.millis() )    // is the next frame due?
        {
            nextFrame();
            nextFrameAt = applet.millis() + framerate*100;    // the next frame lies "framerate millis" in the future
        }
    }

    /**
     * Reset anim counter.
     */
    public void resetAnimCounter()
    {
        nextFrameAt = 0;
    }
}
