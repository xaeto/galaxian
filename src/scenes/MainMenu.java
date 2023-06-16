package scenes;

import constants.TextureConstants;
import models.GameState;
import models.ui_elements.*;
import processing.core.PApplet;

public class MainMenu extends Scene {
  private UIListMenu _listMenu;

  public MainMenu(PApplet applet, int width, int height) {
    super(applet, applet.createImage(width, height, 1));
    this.buildScene();
  }

  /** This function increases the player count by advancing the selector in a list menu. */
  public void increasePlayerCount() {
    _listMenu.advanceSelector();
  }

  /** This function decreased the player count by advancing the selector in a list menu. */
  public void decreasePlayerCount() {
    _listMenu.decreaseSelector();
  }

  /**
   * The `initializeGame()` method is returning the current index of the `_listMenu` UIListMenu
   * object incremented by 1. This is used to determine the number of players selected in the main
   * menu scene.
   */
  public int initializeGame() {
    return _listMenu.getCurrentIndex() + 1;
  }

  /**
   * This function builds a UI list menu with two options for selecting the number of players in a
   * game.
   */
  private void buildPlayerSelection() {
    // _listMenu
    _listMenu = new UIListMenu(_applet, _applet.width / 2, _applet.height / 2, 0);
    var _listMenu_selector =
        new UIListMenuSelector(
            this._applet,
            _listMenu.getX() - 64,
            _listMenu.getY() + TextureConstants.TextHeight / 2,
            0);
    _listMenu.setSelector(_listMenu_selector);
    _listMenu_selector.buildComponent();

    String player_one_text = "1 Player";
    var player_one =
        new UIListElement(
            _applet,
            0,
            _listMenu.getX() - player_one_text.length() * TextureConstants.TextHeight / 2,
            _listMenu.getY(),
            0);
    player_one.setText(player_one_text, UILabelColor.Red, 1);
    _listMenu.addUIListElement(player_one);

    String player_two_text = "2 Players";
    var player_two =
        new UIListElement(
            _applet,
            1,
            _listMenu.getX() - player_one_text.length() * TextureConstants.TextHeight / 2,
            _listMenu.getY() + TextureConstants.GridGap + TextureConstants.TextHeight,
            0);

    player_two.setText(player_two_text, UILabelColor.Red, 1);
    _listMenu.addUIListElement(player_two);
    _listMenu.buildComponent();
    RegisterComponent(_listMenu);
  }

  /** This function builds and displays a high score label and score value. */
  private void buildHighScore() {
    String highscore_label = "HIGH SCORE";
    UILabel label =
        new UILabel(
            this._applet,
            _applet.width / 2 - highscore_label.length() * TextureConstants.TextHeight / 2,
            TextureConstants.TextHeight * 2,
            0);
    label.setText(highscore_label, UILabelColor.Red, 1);

    int current_score = GameState.Highscore;
    int score_length = (int) String.valueOf(current_score).chars().count();
    UILabel score =
        new UILabel(
            this._applet,
            _applet.width / 2 - score_length * TextureConstants.TextHeight / 2,
            label.getY() + TextureConstants.TextHeight + 2 * TextureConstants.GridGap,
            0);
    score.setText(String.valueOf(current_score), UILabelColor.Green, 1);
    RegisterComponent(score);
    RegisterComponent(label);
  }

  private void buildStageScore() {
    if (GameState.Stage == 0) return;
    String stageClearText = "STAGES CLEARED";
    UILabel label =
        new UILabel(
            this._applet,
            _applet.width / 2 - stageClearText.length() * TextureConstants.TextHeight / 2,
            TextureConstants.TextHeight * 2 + 48,
            0);
    label.setText(stageClearText, UILabelColor.Red, 1);

    int current_score = GameState.Stage;
    int score_length = (int) String.valueOf(current_score).chars().count();
    UILabel score =
        new UILabel(
            this._applet,
            _applet.width / 2
                - score_length * TextureConstants.TextHeight / 2
                + stageClearText.length() * 8
                + TextureConstants.GridGap,
            TextureConstants.TextHeight * 2 + 48 + 30,
            0);
    score.setText(String.valueOf(current_score), UILabelColor.Green, 1);
    RegisterComponent(score);
    RegisterComponent(label);
  }

  /**
   * This is overriding the `buildScene()` method from the parent class `Scene`. It first calls the
   * `buildPlayerSelection()` and `buildHighScore()` methods to build the UI components for the main
   * menu scene, and then calls the `buildScene()` method from the parent class to register all the
   * UI components to be drawn.
   */
  @Override
  public void buildScene() {
    buildPlayerSelection();
    buildHighScore();
    buildStageScore();
    super.buildScene();
  }

  /**
   * This function overrides the drawScene method and calls the drawScene method of the superclass.
   */
  @Override
  public void drawScene() {
    super.drawScene();
  }
}
