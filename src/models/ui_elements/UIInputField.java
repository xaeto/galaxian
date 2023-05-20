package models.ui_elements;

import processing.core.PApplet;

// Might need this later
public class UIInputField extends UIComponent {
    private StringBuilder _inputBuilder = new StringBuilder();

    public UIInputField(PApplet applet, float x, float y, float z) {
        super(applet, x, y, z);
    }

    public void pushCharacter(char c) {
        _inputBuilder.append(c);
    }

    public void popLastCharacter() {
        _inputBuilder.deleteCharAt(_inputBuilder.length() -1);
    }
}
