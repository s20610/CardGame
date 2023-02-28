package CardGame;

import javax.swing.*;
import java.awt.*;

public class RankingColorRenderer extends DefaultListCellRenderer {
    //rozszerza renderera do JListy żeby zmieniać kolory na pierwszych 3 miejscach
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (index == 0) {
            c.setBackground(Color.yellow);
        }
            if (index == 1) {
            c.setBackground(Color.GRAY);
            }
            if(index == 2){
                c.setBackground(new Color(222,184,135));
            }
            return c;
        }
    }
