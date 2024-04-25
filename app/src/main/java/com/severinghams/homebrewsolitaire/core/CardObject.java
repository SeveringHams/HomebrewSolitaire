package com.severinghams.homebrewsolitaire.core;


import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.severinghams.homebrewsolitaire.R;
import com.severinghams.homebrewsolitaire.core.enums.EnumColour;
import com.severinghams.homebrewsolitaire.core.enums.EnumRank;
import com.severinghams.homebrewsolitaire.core.enums.EnumSuit;

import java.util.ResourceBundle;

public class CardObject {
    public boolean isFaceDown = false;
    public final EnumRank rank;
    public final EnumSuit suit;
    public final EnumColour colour;
    public int verticalPos;
    public int horizontalPos;


    public final Drawable drawable;
    public final Drawable drawableBack;
    @SuppressLint("UseCompatLoadingForDrawables")
    public CardObject (EnumRank rank, EnumSuit suit, boolean isFaceDown, Drawable drawFace, Drawable drawBack) {
        this.rank = rank;
        this.suit = suit;
        this.colour = this.suit.colour;
        this.isFaceDown = isFaceDown;
        drawable = drawFace;
        drawableBack = drawBack;
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    public CardObject (EnumRank rank, EnumSuit suit, Drawable drawFace, Drawable drawBack) {
        this.rank = rank;
        this.suit = suit;
        this.colour = this.suit.colour;
        drawable = drawFace;
        drawableBack = drawBack;
    }

    public void drawCard(Canvas canvas) {
        int[] pos = {horizontalPos,verticalPos};
        int[] canSize = {canvas.getWidth()/7, canvas.getHeight()/20};
        //System.out.println(horizontalPos+" "+verticalPos);
        Rect rectangle = new Rect(pos[0]*canSize[0],pos[1]*canSize[1],(pos[0]+1)*canSize[0],(pos[1]+1)*canSize[1]);

        if (isFaceDown) {
            drawableBack.setBounds(rectangle);
            drawableBack.draw(canvas);
        } else {
            drawable.setBounds(rectangle);
            drawable.draw(canvas);
        }
    }

}
