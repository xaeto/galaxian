package models;

import constants.TextureConstants;
import helpers.TextureHelper;
import processing.core.PApplet;
import spritelib.*;

import java.util.ArrayList;

public class UILabel extends UIComponent {
    private String _text;
    private UILabelColor _color;
    private int _padding;
    private ArrayList<UILabelSymbol> symbols = new ArrayList<>();

    public UILabel(PApplet applet, float x, float y, float z) {
        super(applet, x, y, z);
    }

    @Override
    public void drawComponent(){
        for(var symbol: this.symbols) {
            symbol.draw(this._applet);
        }
    }

    @Override
    public void buildComponent(){
        this.symbols.clear();

        var sprites = TextureHelper.loadSpriteMap(this._applet);
        int offset_y;
        int offset_next_y;
        switch (this._color) {
            case Red -> {
                offset_y = TextureConstants.RedTextOffsetY;
                offset_next_y = TextureConstants.RedTextOffsetNextY;
            }
            case Green -> {
                offset_y = TextureConstants.GreenTextOffsetY;
                offset_next_y = TextureConstants.GreenTextOffsetNextY;
            }
            case White -> {
                offset_y = TextureConstants.WhiteTextOffsetY;
                offset_next_y = TextureConstants.WhiteTextOffsetNextY;
            }
            default -> {
                offset_y = TextureConstants.WhiteTextOffsetY;
                offset_next_y = TextureConstants.WhiteTextOffsetNextY;
            }
        }

        for(int i = 0; i < this._text.length(); ++i) {
            char current_char = this._text.charAt(i);
            int index_offset_factor = 0;

            int c = this._text.charAt(i);
            if(Character.isDigit(current_char)){
                index_offset_factor = current_char - 48;
            } else if(Character.isAlphabetic(current_char)) {
                index_offset_factor = current_char - 65 + 10;
            }

            MultiSprite sprite = new MultiSprite(TextureConstants.TextWidth, TextureConstants.TextHeight, ANCHORTYPE.TOP_LEFT);
            int next_x = (TextureConstants.TextWidth + TextureConstants.OffsetX)*index_offset_factor;
            if(i >= 0)
                next_x += TextureConstants.OffsetX;
            sprite.addFrames(_applet, sprites, next_x, offset_y, 1);

            var point = new Point(
                    (int)this.getX() + (i*TextureConstants.TextWidth) + this._padding*i,
                    (int)this.getY()
            );
            var symbol = new UILabelSymbol(
                    sprite,
                    point
            );
            this.symbols.add(symbol);
        }
    }

    public void setText(String text, UILabelColor color, int padding) {
        this._color = color;
        this._text = text;
        this._padding = padding;
        this.buildComponent();
    }
}
