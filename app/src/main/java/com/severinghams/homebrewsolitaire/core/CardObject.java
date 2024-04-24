package com.severinghams.homebrewsolitaire.core;

import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import com.severinghams.homebrewsolitaire.R;
import com.severinghams.homebrewsolitaire.core.enums.EnumColour;
import com.severinghams.homebrewsolitaire.core.enums.EnumRank;
import com.severinghams.homebrewsolitaire.core.enums.EnumSuit;

public class CardObject {
    public boolean isFaceDown = false;
    public final EnumRank rank;
    public final EnumSuit suit;
    public final EnumColour colour;
    public int verticalPos;
    public int horizontalPos;
    public final int[][] cardFaces =
            {
                    {R.drawable.card_s1,R.drawable.card_s2,R.drawable.card_s3,R.drawable.card_s4,R.drawable.card_s5,R.drawable.card_s6,R.drawable.card_s7,R.drawable.card_s8,R.drawable.card_s9,R.drawable.card_s10,R.drawable.card_s11,R.drawable.card_s12,R.drawable.card_s13},
                    {R.drawable.card_c1,R.drawable.card_c2,R.drawable.card_c3,R.drawable.card_c4,R.drawable.card_c5,R.drawable.card_c6,R.drawable.card_c7,R.drawable.card_c8,R.drawable.card_c9,R.drawable.card_c10,R.drawable.card_c11,R.drawable.card_c12,R.drawable.card_c13},
                    {R.drawable.card_d1,R.drawable.card_d2,R.drawable.card_d3,R.drawable.card_d4,R.drawable.card_d5,R.drawable.card_d6,R.drawable.card_d7,R.drawable.card_d8,R.drawable.card_d9,R.drawable.card_d10,R.drawable.card_d11,R.drawable.card_d12,R.drawable.card_d13},
                    {R.drawable.card_h1,R.drawable.card_h2,R.drawable.card_h3,R.drawable.card_h4,R.drawable.card_h5,R.drawable.card_h6,R.drawable.card_h7,R.drawable.card_h8,R.drawable.card_h9,R.drawable.card_h10,R.drawable.card_h11,R.drawable.card_h12,R.drawable.card_h13}
            };
    public final int cardFace;
    public CardObject (EnumRank rank, EnumSuit suit, boolean isFaceDown) {
        this.rank = rank;
        this.suit = suit;
        this.colour = this.suit.colour;
        this.isFaceDown = isFaceDown;
        this.cardFace = cardFaces[suit.value][rank.value-1];
    }
    public CardObject (EnumRank rank, EnumSuit suit) {
        this.rank = rank;
        this.suit = suit;
        this.colour = this.suit.colour;
        this.cardFace = cardFaces[suit.value][rank.value-1];
    }

}
