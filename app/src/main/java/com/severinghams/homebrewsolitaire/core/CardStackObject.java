package com.severinghams.homebrewsolitaire.core;


import com.severinghams.homebrewsolitaire.core.enums.EnumRank;
import com.severinghams.homebrewsolitaire.core.enums.EnumStackType;
import com.severinghams.homebrewsolitaire.core.enums.EnumStackingRank;
import com.severinghams.homebrewsolitaire.core.enums.EnumStackingSuit;
import com.severinghams.homebrewsolitaire.core.enums.EnumSuit;

import java.util.ArrayList;
import java.util.Collections;


public class CardStackObject {
    public final boolean singleCardOnly;
    public final boolean canFillEmpty;
    public final boolean canStack;
    public final boolean canRemoveFromStack;
    public final EnumRank baseRank;
    public final EnumStackingRank rankStacking;
    public final EnumStackType stackType;
    public final boolean rankRollover;
    private static boolean ignoreRules = false;
    public final EnumStackingSuit suitStacking;
    private static final ArrayList<CardObject> cardStackList = new ArrayList<>(1);
    private static int positionH;
    private static int positionV;

    // specify position, base rank, rank pattern, suit stacking, and rank rollover. use this to create a tableau stack, waste stack, stock, or foundation stack.
    public CardStackObject (int posH, int posV, EnumStackType stackType, EnumRank baseRank, EnumStackingRank rankStacking, EnumStackingSuit suitStacking, boolean rankRollover, boolean canFillEmpty, boolean canStack, boolean canRemoveFromStack) {
        //Position
        positionH = posH;
        positionV = posV;
        //Base rank
        this.baseRank = baseRank;
        //Stacking Pattern Rules
        this.rankStacking = rankStacking;
        this.suitStacking = suitStacking;
        this.stackType = stackType;
        this.rankRollover = rankRollover;
        //can place cards on empty stack
        this.canFillEmpty = canFillEmpty;
        this.canStack = canStack;
        this.singleCardOnly = false;
        this.canRemoveFromStack = canRemoveFromStack;
    }
    public CardStackObject (int posH, int posV, EnumStackType stackType, boolean canRemoveFromStack) {
        //Position
        positionH = posH;
        positionV = posV;
        //Base rank
        this.baseRank = null;
        //Stacking Pattern Rules
        this.rankStacking = null;
        this.suitStacking = null;
        this.stackType = stackType;
        this.rankRollover = false;
        //can place cards on empty stack
        this.canFillEmpty = false;
        this.canStack = false;
        this.singleCardOnly = false;
        this.canRemoveFromStack = canRemoveFromStack;
    }
    // specify position. use for reserve slots.
    public CardStackObject (int posH, int posV) {
        positionH = posH;
        positionV = posV;
        this.baseRank = null;
        //Stacking Pattern Rules
        this.rankStacking = null;
        this.suitStacking = null;
        this.stackType = EnumStackType.StraightStack;
        this.rankRollover = false;
        //can place cards on empty stack
        this.canFillEmpty = true;
        this.canStack = true;
        this.singleCardOnly = true;
        this.canRemoveFromStack = true;
    }
    public static CardObject getTopCard() {
        if (cardStackList.isEmpty()) {
            return null;
        }
        return cardStackList.get(cardStackList.size()-1);
    }
    public ArrayList<CardObject> getCardStackList() {
        return cardStackList;
    }
    public int[] getPos() {
        return new int[]{positionH, positionV};
    }
    public void setPos(int posH, int posV) {
        positionH = posH;
        positionV = posV;
    }
    public boolean canPlaceOnTop(CardObject card) {
        //if stack allows additional cards
        if (ignoreRules) {
            return true;
        }
        if (!canStack) {
            return false;
        }
        //if stack is empty
        if (cardStackList.isEmpty()) {
            //if empty stack can be filled
            if (canFillEmpty) {
                //if req. base rank is any rank
                if (baseRank == null) {
                    return true;
                }
                //if the current card equals the required rank
                return card.rank.equals(baseRank);
            } else {
                return false;
            }
            //if the stack allows additional cards and the stack is not empty
        } else {
            if (this.singleCardOnly) {
                return false;
            } else {
                if (this.rankStacking.isValid(getTopCard().rank,card.rank,rankRollover)&&this.suitStacking.isValid(getTopCard().suit,card.suit)) {
                    return true;
                }
            }
        }
        System.err.println("ERROR: Method canPlaceOnTop(CardObject) in class CardStackObject has\r\n" +
                "defaulted to return false! Check your code!");
        return false;
    }
    public boolean placeOnTop(ArrayList<CardObject> hand) {
        if (canPlaceOnTop(hand.get(0))) {
            cardStackList.addAll(hand);
            return true;
        }
        return false;
    }
    public boolean canGrabStack(int stackIndex) {
        int i = stackIndex;
        boolean canGrab = true;
        if (!stackType.equals(EnumStackType.SpreadStackV)) {
            return stackIndex == cardStackList.size() - 1;
        }
        if (ignoreRules) {
            return true;
        }
        if (cardStackList.get(stackIndex).isFaceDown) {
            return false;
        }
        while (i + 1 < cardStackList.size()) {
            if (!(this.rankStacking.isValid(cardStackList.get(i).rank,cardStackList.get(i+1).rank,rankRollover)&&this.suitStacking.isValid(cardStackList.get(i).suit,cardStackList.get(i+1).suit))) {
                canGrab = false;
            }
            i++;
        }
        return canGrab;
    }
    public ArrayList<CardObject> grabStack(int stackIndex) {
        ArrayList<CardObject> toHand = new ArrayList<>(1);
        if (canGrabStack(stackIndex)) {
            for (int i = stackIndex; i < cardStackList.size(); i++) {
                toHand.add(cardStackList.get(stackIndex));
                cardStackList.remove(stackIndex);
            }
            if (getTopCard()!=null) {
                getTopCard().isFaceDown = false;
            }
            return toHand;
        }
        return null;
    }
    public void updateStack() {
        if (getTopCard()!=null) {
            getTopCard().isFaceDown = false;
        }
    }
    public static void setIgnoreRules(boolean ignoreRule) {
        ignoreRules = ignoreRule;
    }
    public void setupAddCard(CardObject card) {
        cardStackList.add(card);
    }
}
