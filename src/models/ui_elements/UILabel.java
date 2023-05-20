package models.ui_elements;

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

    public String getText(){
        return this._text;
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
        int offset_y = 0;
        int offset_next_y = 0;

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
        }

        for(int i = 0; i < this._text.length(); ++i) {
            char current_char = this._text.charAt(i);
            int index_offset_factor = 0;

            int character_offset_y = 0;

            MultiSprite sprite = new MultiSprite(TextureConstants.TextWidth, TextureConstants.TextHeight, ANCHORTYPE.TOP_LEFT);
            boolean is_whitespace = Character.isSpaceChar(current_char);
            if(is_whitespace) {
                character_offset_y = offset_next_y - TextureConstants.GridGap;
                index_offset_factor = 17;
            } else if(Character.isDigit(current_char)){
                index_offset_factor = current_char - 48;
                character_offset_y = offset_y;
            } else if(Character.isAlphabetic(current_char)) {
                index_offset_factor = current_char - 65 + 10;
                if(index_offset_factor > 18) {
                    character_offset_y = offset_next_y - TextureConstants.GridGap;
                    index_offset_factor -= 19;
                } else {
                    character_offset_y = offset_y;
                }
            }

            int next_x = (TextureConstants.TextWidth + TextureConstants.GridGap)*index_offset_factor;
            if(i >= 0)
                next_x += TextureConstants.GridGap;

            if(is_whitespace) {
                sprite.addFrames(_applet, sprites, 412, 140, 1);
            } else {
                sprite.addFrames(_applet, sprites, next_x, character_offset_y, 1);
            }

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
        this._text = text.toUpperCase();
        this._padding = padding;
        this.buildComponent();
    }
}
