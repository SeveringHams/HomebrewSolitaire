package com.severinghams.homebrewsolitaire.core;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;

import com.severinghams.homebrewsolitaire.core.enums.EnumRank;
import com.severinghams.homebrewsolitaire.core.enums.EnumStackType;
import com.severinghams.homebrewsolitaire.core.enums.EnumStackingRank;
import com.severinghams.homebrewsolitaire.core.enums.EnumStackingSuit;

public class KlondikeGameObject extends BaseSingleDeckGameObject {
    public boolean cardsFaceDown = true;
    public final CardStackObject[] tableau = new CardStackObject[7];
    public final CardStackObject[] foundation = new CardStackObject[4];
    public final CardStackObject stock = new CardStackObject(0,1, EnumStackType.StockStack, false);
    public final CardStackObject waste = new CardStackObject(1,1, EnumStackType.SpreadStackH, EnumRank.RankA, EnumStackingRank.AceToKing, EnumStackingSuit.SameSuit, false, false, false, true);


    public KlondikeGameObject(long seed, Context context) {
        super(seed, context);
        for (int i = 0; i < tableau.length; i++) {
            tableau[i] = new CardStackObject(i, 1, EnumStackType.SpreadStackV, EnumRank.RankK, EnumStackingRank.KingToAce, EnumStackingSuit.AltColours, false, true, true, true);
            System.out.println(tableau[i].getPos()[0]+" "+tableau[i].getPos()[1]);
        }
        for (int i = 0; i < 4; i++) {
            foundation[i] = new CardStackObject(i+3, 0, EnumStackType.SpreadStackV, EnumRank.RankA, EnumStackingRank.AceToKing, EnumStackingSuit.SameSuit, false, true, true, true);
        }
        for (int j = 0; j < 7; j++) {
            for (int i = 6; i >= j; i--) {
                System.out.print(i+" ");
                tableau[i].setupAddCard(dealer.get(0));
                dealer.remove(0);
            }
            System.out.println();
        }
        dealer.trimToSize();
        for (int i = 0; i < dealer.size(); i++) {
            stock.setupAddCard(dealer.get(0));
            dealer.remove(0);
        }
        dealer.trimToSize();
    }

    private void drawTableau(Canvas canvas) {
        for (CardStackObject cardStackObject : tableau) {
            cardStackObject.updateStack();
            cardStackObject.drawStack(canvas);
        }
    }
    private void drawFoundation(Canvas canvas) {
        for (CardStackObject cardStackObject : foundation) {
            cardStackObject.updateStack();
            cardStackObject.drawStack(canvas);
        }
    }
    private void drawStockAndWaste(Canvas canvas) {
        stock.updateStack();
        waste.updateStack();
    }
    public void drawGame(Canvas canvas) {
        drawFoundation(canvas);
        drawTableau(canvas);
        drawStockAndWaste(canvas);
    }
}
