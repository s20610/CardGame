package CardGame;

import java.util.Comparator;

public class playerComparator implements Comparator<Player> {

    public int compare(Player o1, Player o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return 1;
        }
        if (o2 == null) {
            return -1;
        }
        return o1.compareTo(o2);
    }
}

