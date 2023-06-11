package scenes;

import models.Star;
import models.ui_elements.UILabel;
import models.ui_elements.UILabelColor;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class IntroScene extends Scene {
    private UILabel galaxianLabel;
    private UILabel missionLabel;
    private UILabel enterLabel;
    public IntroScene(PApplet applet, int width, int height) {
        super(applet, applet.createImage(width, height, 0));
    }

    @Override
    public void drawScene(){
        animate();
        super.drawScene();
    }

    @Override
    public void buildScene(){
        String galaxianLabelText = "WE ARE THE GALAXIANS";
        this.galaxianLabel = new UILabel(
                this._applet,
                this._applet.width/2 - galaxianLabelText.length()*8,
                this._applet.height + 100,
                0
        );
        this.galaxianLabel.setText(galaxianLabelText, UILabelColor.Red, 1);

        String missionLabel = "DESTROY ALL ALIENS";
        this.missionLabel = new UILabel(
                this._applet,
                this._applet.width/2 - missionLabel.length()*8,
                this._applet.height + 100 + 24,
                0
        );
        this.missionLabel.setText(missionLabel, UILabelColor.Red, 1);

        RegisterComponent(this.galaxianLabel);
        RegisterComponent(this.missionLabel);

        String enterText = "PRESS ENTER";
        this.enterLabel = new UILabel(
                this._applet,
                this._applet.width/2 - enterText.length()*8,
                this._applet.height + 100 + 24 + 36,
                0
        );
        this.enterLabel.setText(enterText, UILabelColor.White, 1);
        RegisterComponent(this.enterLabel);
        super.buildScene();
    }

    private void animate(){
        if(this.galaxianLabel.getY() > this._applet.height/3){
            this.galaxianLabel.setY(this.galaxianLabel.getY() - 2);
            this.galaxianLabel.buildComponent();
        }
        if(this.missionLabel.getY() > this._applet.height/3 + 24){
            this.missionLabel.setY(this.missionLabel.getY() - 2);
            this.missionLabel.buildComponent();
        }
        if(this.enterLabel.getY() > this._applet.height/3 + 24 + 36){
            this.enterLabel.setY(this.enterLabel.getY() - 2);
            this.enterLabel.buildComponent();
        }
    }
}
