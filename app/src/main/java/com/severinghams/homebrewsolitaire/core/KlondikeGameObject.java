package com.severinghams.homebrewsolitaire.core;

import com.severinghams.homebrewsolitaire.core.enums.EnumRank;
import com.severinghams.homebrewsolitaire.core.enums.EnumStackType;
import com.severinghams.homebrewsolitaire.core.enums.EnumStackingRank;
import com.severinghams.homebrewsolitaire.core.enums.EnumStackingSuit;

import java.util.Collections;

public class KlondikeGameObject extends BaseSingleDeckGameObject {
    public boolean cardsFaceDown = true;
    public final CardStackObject[] tableau = new CardStackObject[7];
    public final CardStackObject[] foundation = new CardStackObject[4];
    public final CardStackObject stock = new CardStackObject(0,1, EnumStackType.StockStack, false);
    public final CardStackObject waste = new CardStackObject(1,1, EnumStackType.SpreadStackH, EnumRank.RankA, EnumStackingRank.AceToKing, EnumStackingSuit.SameSuit, false, false, false, true);


    public KlondikeGameObject(long seed) {
        super(seed);
        for (int i = 0; i < tableau.length; i++) {
            tableau[i] = new CardStackObject(i, 1, EnumStackType.SpreadStackV, EnumRank.RankK, EnumStackingRank.KingToAce, EnumStackingSuit.AltColours, false, true, true, true);
        }
        for (int i = 0; i < 4; i++) {
            foundation[i] = new CardStackObject(i+3, 0, EnumStackType.StraightStack, EnumRank.RankA, EnumStackingRank.AceToKing, EnumStackingSuit.SameSuit, false, true, true, true);
        }
        for (int j = 0; j < 6; j++) {
            for (int i = 6; i >= j; i--) {
                tableau[i].setupAddCard(dealer.get(0));
                dealer.remove(0);
            }
        }
        for (int i = 0; i < dealer.size(); i++) {
            stock.setupAddCard(dealer.get(0));
            dealer.remove(0);
        }
    }
}
