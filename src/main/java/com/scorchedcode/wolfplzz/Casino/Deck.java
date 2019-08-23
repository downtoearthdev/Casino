package com.scorchedcode.wolfplzz.Casino;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Integer> deck = new ArrayList<>();

    public Deck() {
        refreshAndShuffleDeck();
    }

    public void refreshAndShuffleDeck() {
        deck.clear();
        for(int x = 1; x < 53; x++)
            deck.add(x);
        Collections.shuffle(deck);
    }

    public String drawCard() {
        Integer card = deck.get(0);
        deck.remove(0);
        return formatCard(card);
    }

    private String formatCard(Integer card) {
        // 1 14 27 40
        //11 24 37 50
        String formattedCard = "";
        if(card % 13 == 1)
            formattedCard+="Ace ";
        else if(card % 13 == 11)
            formattedCard+="Jack ";
        else if(card % 13 == 12)
            formattedCard+="Queen ";
        else if(card % 13 == 0)
            formattedCard+="King ";
        else
            formattedCard+=String.valueOf(card%13) + " ";
        formattedCard+="of ";
        if(card == 13 || card/13 == 0)
            formattedCard+="Spades";
        else if(card == 26 || card/13 == 1)
            formattedCard+="Hearts";
        else if(card == 39 || card/13 == 2)
            formattedCard+="Diamonds";
        else if( card == 52 || card/13 == 3)
            formattedCard+="Clubs";
        return formattedCard;
    }
}
