package models;

import scenes.Scene;
import models.Player;

public class GameState {
    public static Scene CurrentScene;
    public static Player PlayerOne;
    public static Player PlayerTwo;
    public static boolean Paused = false;
    public static int Highscore = 0;
}
