import java.util.ArrayList;

public class Player {

    public int playerNum;
    public int[] hand;
    public int numCards;
    ArrayList<Card> handList = new ArrayList<Card>();
    public int score;
    public int nAces;

    public Player(int n) {
        playerNum = n;
    }

    public void showHand() {
        for (int i = 0; i < handList.size(); i++) {
            handList.get(i).printInfo();
        }
        System.out.println("score is: " + score);
    }

    public void hit(Card c) {
        handList.add(c);
        score += c.cardScore;
        if (c.cardNum == 12) {
            nAces++;
        }
    }

    public int calculateScore() {
        int s = 0;
        for (int c = 0; c < handList.size(); c++) {
            s += handList.get(c).cardScore;
        }
        return s;
    }
}
