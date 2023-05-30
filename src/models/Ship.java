package models;

import processing.core.PApplet;
import spritelib.MultiSprite;
import spritelib.Point;

import java.util.OptionalInt;
import java.util.OptionalLong;

public class Ship extends Enemy {
    public Ship(float x, float y, int width, int height) {
        super(x, y, width, height);
    }
}
