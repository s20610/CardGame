package CardGame;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.TimerTask;

public class Board extends JFrame {

    private List<Card> cards;
    private Card selectedCard;
    private Card card1;
    private Card card2;
    private Timer t;
    private int rows,columns;
    private String iconFileName;
    private List<Integer> cardIDvalues;
    private int movesCounter = 0;
    private java.util.Timer gameLengthTimer = new java.util.Timer();
    private int gameLengthCounter = 0;
    private TimerTask gameLengthCounting;
    private int gameScore;
    public Board(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        int pairs=(rows*columns)/2;
        List<Card> cardsList = new ArrayList<Card>();
        List<ImageIcon> cardIcons = new ArrayList<ImageIcon>();
        cardIDvalues = new ArrayList<Integer>();

        gameLengthCounting = new TimerTask() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                        gameLengthCounter++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        for (int i = 0; i < pairs; i++){
            cardIDvalues.add(i);
            cardIDvalues.add(i);
        }
        Collections.shuffle(cardIDvalues);
        for (int i = 0;i<pairs; i++){
            iconFileName = i+".png";
            //Tutaj skalowane sa ikony zeby nie robic tego recznie
            ImageIcon baseIcon = new ImageIcon(getClass().getResource(iconFileName));
            Image baseImg = baseIcon.getImage();
            Image resizedImg = baseImg.getScaledInstance(600/(columns+2), 600/(rows+2),  java.awt.Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImg);
            cardIcons.add(new ImageIcon(resizedImg));
        }
        Collections.shuffle(cardIcons);

        for (int val : cardIDvalues){
            //kazdej karcie nadawana jest ikona i id, po ktorym potem sa parowane
            Card c = new Card();
            c.setId(val);
            c.setCardIcon(cardIcons.get(val));
            c.addActionListener(ae -> {
                selectedCard = c;
                doTurn();
            });
            cardsList.add(c);
        }
        this.cards = cardsList;

        t = new Timer(800, ae -> checkCards());
        t.setRepeats(false);

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(rows, columns));
        for (Card c : cards){
            pane.add(c);
        }
        setTitle("Memory Game");
        gameLengthTimer.schedule(gameLengthCounting,1000);
    }

    public void doTurn(){
        if (card1 == null && card2 == null){
            //kazda karta ma przypisana ikone ktora zostaje ustawiona dopiero po kliknieciu
            card1 = selectedCard;
            card1.setIcon(card1.getCardIcon());
        }

        if (card1 != null && card1 != selectedCard && card2 == null){
            card2 = selectedCard;
            card2.setIcon(card2.getCardIcon());
            t.start();
        }
    }
    //ta metoda sprawdza czy wybrane karty sa takie same
    public void checkCards() {
        if (card1.getId() == card2.getId()){
            // jezeli tak to wylacza przyciski
            card1.setEnabled(false);
            card2.setEnabled(false);
            card1.setMatched(true);
            card2.setMatched(true);
            if (this.isGameWon()){
                //jezeli gracz wygrywa gre to timer zatrzymuje sie, obliczany jest wynik i uruchamia sie metoda zawierajaca okno wpisywania nazwy gracza
                gameLengthTimer.cancel();
                gameScore =(rows*columns*1000)/(gameLengthCounter+movesCounter);
                gameWinDialogue();
                setVisible(false);
            }
        }
        else{
            //jezeli karty nie sa takie same to ikonki znikaja
            card1.setIcon(null);
            card2.setIcon(null);
        }
        card1 = null;
        card2 = null;
        movesCounter++;
    }

    public boolean isGameWon(){
        //petla sprawdzajaca czy wszystkie karty zostaly juz sparowane
        for(Card c: this.cards){
            if (!c.getMatched()){
                return false;
            }
        }
        return true;
    }

    public void gameWinDialogue(){
        JTextField nameInput;
        JFrame f = new JFrame();
        JDialog gameWon = new JDialog(f, "WINNER", true);
        nameInput = new JTextField("name here");
        JButton ok = new JButton("ok");
        ok.addActionListener(e -> {
            String name = nameInput.getText();
          f.setVisible(false);
            try {
                Player player = new Player(name,gameScore);
                Highscores h = new Highscores();
                h.addToHighscores(player);
                h.saveHighscores();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        gameWon.add(nameInput, BorderLayout.CENTER);
        gameWon.add(ok,BorderLayout.EAST);
        gameWon.setSize(150, 60);
        gameWon.setLocation(450,250);
        gameWon.setVisible(true);
    }
}
