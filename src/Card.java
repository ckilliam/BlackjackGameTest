// IF THIS MESSAGE APPEARS ON GITHUB, THEN BRYAN RECEIVES AN A
public class Card {

    public String suit;
    public int suitNum;
    public String cardName;
    public int cardNum;
    public int cardScore;
    public boolean isDealt = false;

    public Card(int pSuitNum, int pCardNum) {
        suitNum = pSuitNum;
        cardNum = pCardNum;
        suit = getSuit(suitNum);
        cardName = getCardName(cardNum);

    }

    public String getSuit(int suitNum) {
        String s = null;
        if (suitNum == 0) {
            s = "spades";
        } else if (suitNum == 1) {
            s = "diamonds";
        } else if (suitNum == 2) {
            s = "clubs";
        } else if (suitNum == 3) {
            s = "hearts";
        } else {
            System.out.println("Houston we have a problem");
        } // assign suit string
        return s;
    }

    public String getCardName(int cardNum) {
        String c = null;
        if (cardNum == 0) {
            c = "two";
            cardScore = 2;
        } else if (cardNum == 1) {
            c = "three";
            cardScore = 3;
        } else if (cardNum == 2) {
            c = "four";
            cardScore = 4;
        } else if (cardNum == 3) {
            c = "five";
            cardScore = 5;
        } else if (cardNum == 4) {
            c = "six";
            cardScore = 6;
        } else if (cardNum == 5) {
            c = "seven";
            cardScore = 7;
        } else if (cardNum == 6) {
            c = "eight";
            cardScore = 8;
        } else if (cardNum == 7) {
            c = "nine";
            cardScore = 9;
        } else if (cardNum == 8) {
            c = "ten";
            cardScore = 10;
        } else if (cardNum == 9) {
            c = "jack";
            cardScore = 10;
        } else if (cardNum == 10) {
            c = "queen";
            cardScore = 10;
        } else if (cardNum == 11) {
            c = "king";
            cardScore = 10;
        } else if (cardNum == 12) {
            c = "ace";
            cardScore = 11;
        } // assign card name and assign scores

        return c;
    }

    public void printInfo() {
        System.out.println(cardName + " of " + suit);
    }

}
