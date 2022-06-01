//Graphics Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
//import java.awt.Font;
import java.io.IOException;
import java.awt.FontMetrics;

public class Casino implements Runnable, KeyListener {
    final int WIDTH = 90;
    final int HEIGHT = 70;
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    public char key;
    public int keyCode;

    public Card[] deck;
    public Player[] players;
    public int currentCard = 0;
    public int currentTurn = 0;

    public static void main(String[] args) {
        System.out.println("program is running");
        Casino game = new Casino();
        new Thread(game).start();
    }

    public Casino() {
        setUpGraphics();
        restart();
    }

    public void run() {
        while (true) {
            render();
            pause(10);
        }
    }

    public void restart() {
        makeDeck();
        makePlayers(5);
        shuffle();
        deal();
//        while (currentTurn < players.length - 1) {
            System.out.println("\nit's player " + currentTurn + "'s turn");
//            System.out.println("Player " + currentTurn + "'s hand:");
            players[currentTurn].showHand();
//            takeTurn();
//            currentTurn++;
//        } // player turns
//        dealerPlays();
    }

    public void makeDeck() {
        deck = new Card[52];

        for (int suit = 0; suit < 4; suit++) {
            for (int card = 0; card < 13; card++) {
                deck[card + suit * 13] = new Card(suit, card);
            }
        }
//        for (int i = 0; i < deck.length; i++) {
//            deck[i].printInfo();
//        }
    }

    public void makePlayers(int nPlayers) {
        players = new Player[nPlayers];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(i);
        }
    }

    public void shuffle() {
        for (int i = 0; i < 5000; i++) {
            int r = (int) (Math.random() * 52);
            int s = (int) (Math.random() * 52);
//        System.out.println("pre-shuffle");
//        deck[r].printInfo();
//        deck[s].printInfo();

            if (r != s) {
                Card temp = deck[r];
                deck[r] = deck[s];
                deck[s] = temp;
            }
//        System.out.println("post-shuffle");
//        deck[r].printInfo();
//        deck[s].printInfo();
        }

//        System.out.println("**********shuffled deck:**********");
//        for (int i = 0; i < deck.length; i++) {
//            deck[i].printInfo();
//        }
    }

    public void deal() {
        for (int i = 0; i < 2; i++) {
            for (int p = 0; p < players.length; p++) {
//                players[p].handList.add(deck[p + i * players.length]);
//                deck[p + i * players.length].isDealt = true;
//                System.out.println(currentCard);
                players[p].hit(deck[currentCard]);
                currentCard++;
            }
        }
        printHands();
    }

    public void printHands() {
        System.out.println("\n********** Player hands **********");
        for (int p = 0; p < players.length; p++) {
            System.out.println("player " + p + " hand is:");
            players[p].showHand();
            System.out.println("player " + p + " score is " + players[p].score + "\n");
        }
    }

    public void takeTurn() {
        boolean turnOver = false;
        if (checkBlackjack()) {
            turnOver = true;
        } else if (checkBust()) {
            turnOver = true;
        }
//        while (!turnOver) {
//            System.out.print("");
            if (keyCode == 32) { // hit, space bar
                System.out.println("\nplayer " + currentTurn + " hits");
                players[currentTurn].hit(deck[currentCard]);
                currentCard++;
                keyCode = 0;
                if (checkBlackjack()) {
                    turnOver = true;
                } else if (checkBust()) {
                    turnOver = true;
                }
                players[currentTurn].showHand();
//                System.exit(0);
            } else if (keyCode == 83) { // stand, s key
//            player.stand();
                turnOver = true;
                keyCode = 0;
            }
//        }
    }

    public boolean checkBlackjack() {
        boolean turnOver = false;
        if (players[currentTurn].score == 21) {
            System.out.println("BLACKJACK!");
            turnOver = true;
            currentTurn++;

            System.out.println("\nit's player " + currentTurn + "'s turn");
            //       System.out.println("Player " + currentTurn + "'s hand:");
            players[currentTurn].showHand();
        }
        return turnOver;
    }

    public boolean checkBust() {
        boolean turnOver = false;
        if (players[currentTurn].score > 21) {
//            System.out.println("number of aces available: " + players[currentTurn].nAces);
            if (players[currentTurn].nAces > 0) {
                players[currentTurn].score -= 10;
                players[currentTurn].nAces--;
            }// ace handling
            else {
                System.out.println("BUSTED");
                turnOver = true;
                currentTurn++;

                System.out.println("\nit's player " + currentTurn + "'s turn");
                //       System.out.println("Player " + currentTurn + "'s hand:");
                players[currentTurn].showHand();
//                currentTurn++;
            }
        }
        return turnOver;
    }

    public void dealerPlays() {
        System.out.println("\ndealer's turn");
    }

    public void render() {

    }

    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

//        System.out.println(keyCode + " pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        key = e.getKeyChar();
        keyCode = e.getKeyCode();

        takeTurn();

        /*if(keyCode==32){ // space, player hits
//            System.out.println("\nit's player " + currentTurn + "'s turn");
            //       System.out.println("Player " + currentTurn + "'s hand:");
//            players[currentTurn].showHand();
              takeTurn();
            //        currentTurn++;
        }
        if(keyCode== 83){
            System.out.println("Stand");
            currentTurn++;

            System.out.println("\nit's player " + currentTurn + "'s turn");
            //       System.out.println("Player " + currentTurn + "'s hand:");
            players[currentTurn].showHand();
        }*/

    }

    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();

        canvas.addKeyListener(this);

        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

}