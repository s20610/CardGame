package CardGame;

import javax.swing.*;
import java.awt.*;

public class gridSizeDialogue extends JFrame {
    JComboBox<String> chooseSize;

    public gridSizeDialogue() {
        JDialog gridSize = new JDialog(this, "Grid Size", true);
        String[] sizes = {"2x2","3x4", "4x4", "4x5"};
        chooseSize = new JComboBox<>(sizes);
        JButton ok = new JButton("Ok");
        ok.addActionListener(e -> {
            setVisible(false);
            String selectedItem= (String) chooseSize.getSelectedItem();
            Board b = null;
            switch (selectedItem) {
                case "2x2":
                    b = new Board(2,2);
                    break;
                case "3x4":
                    b = new Board(3,4);
                    break;
                case "4x4":
                    b = new Board(4,4);
                    break;
                case "4x5":
                    b = new Board(4,5);
                    break;
            }
            b.setPreferredSize(new Dimension(600,600));
            b.setLocation(400,200);
            b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            b.pack();
            b.setVisible(true);
        });
        gridSize.add(chooseSize, BorderLayout.CENTER);
        gridSize.add(ok, BorderLayout.EAST);
        gridSize.setSize(100, 60);
        gridSize.setLocation(450,250);
        gridSize.setVisible(true);
    }
}
