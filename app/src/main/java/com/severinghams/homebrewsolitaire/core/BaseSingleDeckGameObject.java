package com.severinghams.homebrewsolitaire.core;

import com.severinghams.homebrewsolitaire.core.enums.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BaseSingleDeckGameObject {
    public long gameSeed = 0;
    public final ArrayList<CardObject> dealer;
    public ArrayList<CardObject> hand = new ArrayList<>(1);
    private boolean ignoreRules = false;
    public boolean cardsFaceDown = false;
    public BaseSingleDeckGameObject(long seed) {
        this.dealer = randomizer(this.cardsFaceDown, seed);
    }
    public ArrayList<CardObject> randomizer(boolean cardsFaceDown, long seed) {
        EnumRank[] arrayRank = {EnumRank.RankA,EnumRank.Rank2,EnumRank.Rank3,EnumRank.Rank4,EnumRank.Rank5,EnumRank.Rank6,EnumRank.Rank7,EnumRank.Rank8,EnumRank.Rank9,EnumRank.Rank10,EnumRank.RankJ,EnumRank.RankQ,EnumRank.RankK};
        EnumSuit[] arraySuit = {EnumSuit.Spades,EnumSuit.Clubs,EnumSuit.Hearts,EnumSuit.Diamonds};
        ArrayList<CardObject> cards = new ArrayList<>(52);
        for (int i = 0; i < 52; i++) {
            cards.add(new CardObject(arrayRank[i%13],arraySuit[i/13],cardsFaceDown));
        }
        if (seed == 0) {
            Random rand = new Random();
            this.gameSeed = rand.nextLong();
            Collections.shuffle(cards, new Random(this.gameSeed));
        } else {
            this.gameSeed = seed;
            Collections.shuffle(cards, new Random(this.gameSeed));
        }
        Collections.shuffle(cards,new Random(seed));
        return cards;
    }

    public ArrayList<CardObject> setFromHand() {
        ArrayList<CardObject> toStack = hand;
        hand = new ArrayList<>(1);
        return toStack;
    }
}