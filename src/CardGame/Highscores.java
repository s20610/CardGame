package CardGame;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;


public class Highscores extends JFrame {
    private Player[] scores;
    private JList<Player> ranking;
    private JLabel text;

    public Highscores() throws IOException, ClassNotFoundException {
        scores = new Player[30];
        //scores = readHighscores();
        //Arrays.sort(scores, new playerComparator());
        ranking = new JList<>(scores);
        RankingColorRenderer renderer = new RankingColorRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        ranking.setCellRenderer(renderer);
        text = new JLabel("HIGHSCORES", SwingConstants.CENTER);
        text.setVerticalAlignment(SwingConstants.CENTER);
        add(text, BorderLayout.NORTH);
        add(ranking, BorderLayout.CENTER);
        setLocation(450, 250);
        setSize(200, 400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

public void addToHighscores(Player a){
        for(int i=0;i<scores.length;i++){
            if(scores[i] == null){
                scores[i] = a;
                break;
            }
        }
}
    public void saveHighscores() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("scores.txt"));
        oos.writeObject(scores);
        oos.close();
    }

    public Player[] readHighscores() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("scores.txt"));
        return (Player[])ois.readObject();
    }
}
