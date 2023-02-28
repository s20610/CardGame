package CardGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class mainMenu extends JFrame {
    JButton newGame, highscores, exit;
    JLabel title, title2, iconsBy;
    Font f1 = new Font(Font.DIALOG, Font.BOLD, 48);
    Font f2 = new Font(Font.DIALOG, Font.BOLD, 28);
    Font f3 = new Font(Font.DIALOG, Font.PLAIN, 20);
    //action listenery wymienione na lambdy bo IDE samo je proponuje i wyglądają ładniej
    public mainMenu() {
        setTitle("Memory Game by s20610");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // Remainder sprawia,że każdy dodany element jest jako ostatni w swoim wierszu
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        title = new JLabel("Memory Game");
        title.setFont(f1);
        title2 = new JLabel("by s20610");
        title2.setFont(f2);
        newGame = new JButton("New Game");
        newGame.setFont(f3);
        newGame.addActionListener(e -> {
            gridSizeDialogue g = new gridSizeDialogue();
        });
        highscores = new JButton("Highscores");
        highscores.setFont(f3);
        highscores.addActionListener(e -> {
            try {
                Highscores h = new Highscores();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        exit = new JButton("Exit");
        exit.setFont(f3);
        exit.addActionListener(e -> System.exit(0));
        add(title, gbc);
        add(title2, gbc);
        add(newGame, gbc);
        add(highscores, gbc);
        add(exit, gbc);
        iconsBy = new JLabel("Icons by: wichaiwi");
        iconsBy.setFont(f3);
        add(iconsBy, gbc);
        setSize(600, 600);
        setLocation(400,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
