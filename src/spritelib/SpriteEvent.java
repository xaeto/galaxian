package spritelib;

abstract interface SpriteEvent
{
    abstract void event( Sprite sprite, int frame, EVENTTYPE eventtype);
}
