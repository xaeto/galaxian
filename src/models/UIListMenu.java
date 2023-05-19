package models;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UIListMenu extends UIComponent {
    private ArrayList<UIListElement> _listElements = new ArrayList<>();
    private int currentIndex = 0;
    private UIListMenuSelector _selector;

    public UIListMenu(PApplet applet, UIListMenuSelector selector, float x, float y, float z) {
        super(applet, x, y, z);
        this._selector = selector;
    }

    public void addUIListElement(UIListElement listElement){
        this._listElements.add(listElement);
    }

    public void advanceSelector(){
        var sel = _selector;
        if(sel.getY() == this.getY()*_listElements.size()){
            this.currentIndex = 0;
            sel.setY(0);
        } else {
            this.currentIndex += 1;
            sel.setY(this.getY() + 16);
        }
    }

    @Override
    public void drawComponent(){
        for (UIListElement element: _listElements) {
            element.drawComponent();
        }
        _selector.drawComponent();
    }
}
