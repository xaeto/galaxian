package models;

import processing.core.PApplet;
import spritelib.Point;
import spritelib.Sprite;

public class UILabelSymbol {
    private Sprite _sprite;
    private Point _position;

    public UILabelSymbol(Sprite sprite, Point position){
        this._position = position;
        this._sprite = sprite;
    }

    public void draw(PApplet applet) {
        System.out.println(_position.getX());
        _sprite.draw(applet, this._position);
    }
}
