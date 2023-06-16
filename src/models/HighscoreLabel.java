package models;

import constants.TextureConstants;
import models.ui_elements.UILabel;
import models.ui_elements.UILabelColor;
import processing.core.PApplet;

public class HighscoreLabel {
    private final PApplet _applet;
    private final float x;
    private final float y;
    private final float z;
    private UILabel _highscoreLabel;
    private UILabel _scoreLabel;

    public HighscoreLabel(PApplet applet, float x, float y, float z) {
        this._applet = applet;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // The `drawComponent()` method is calling the `drawComponent()` method of the `_scoreLabel` and
    // `_highscoreLabel` objects, which are instances of the `UILabel` class. This is likely used to
    // display the high score label and score on the screen.
    public void drawComponent(){
        this._scoreLabel.drawComponent();
        this._highscoreLabel.drawComponent();
    }

    /**
     * This function builds a UI component consisting of a high score label and a score label.
    */
    public void buildComponent(){
        String highscore_label = "HIGH SCORE";
        UILabel label = new UILabel(
                this._applet,
                16,
                16,
                0
        );
        label.setText(highscore_label, UILabelColor.Red, 1);
        this._highscoreLabel = label;

        int current_score = GameState.Highscore;
        int score_length = (int)String.valueOf(current_score).chars().count();
        UILabel score = new UILabel(
                this._applet,
                label.getX() - score_length* TextureConstants.TextHeight/2 + highscore_label.length()*8,
                label.getY() + TextureConstants.TextHeight + 2*TextureConstants.GridGap,
                0
        );
        score.setText(String.valueOf(current_score), UILabelColor.Green, 1);
        this._scoreLabel = score;
    }
}
