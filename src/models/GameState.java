package models;

import scenes.Scene;
import models.Player;

public class GameState {

    // Declaring a public static variable named "CurrentScene" of type "Scene". This variable can be
    // accessed and modified from any part of the program and it is used to keep track of the current scene
    // in the game.
    public static Scene CurrentScene;
    public static Player PlayerOne;
    public static Player PlayerTwo;

    public static boolean Paused = false;
    // Declaring a public static integer variable named "Highscore" and initializing it with a value of 0.
    // This variable can be accessed and modified from any part of the program and it is used to keep track
    // of the highest score achieved in the game.
    public static int Highscore = 0;
}
