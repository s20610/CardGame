package CardGame;
    import javax.swing.*;

public class Card extends JButton{
        //klasa odpowiadajaca za same karty
        //sa matchowane po id,a nie ikonach bo tak jest latwiej
        private int id;
        private boolean matched = false;
        private ImageIcon cardIcon;

        public void setId(int id){
            this.id = id;
        }

        public int getId(){
            return this.id;
        }


        public void setMatched(boolean matched){
            this.matched = matched;
        }

        public boolean getMatched(){
            return this.matched;
        }

    public void setCardIcon(ImageIcon cardIcon) {
        this.cardIcon = cardIcon;
    }

    public ImageIcon getCardIcon() {
        return cardIcon;
    }
}
