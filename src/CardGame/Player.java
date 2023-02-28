package CardGame;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int score;
    public Player(String name,int score){
    this.name = name;
    this.score = score;
    }

    @Override
    public String toString() {
        return name+" "+score;
    }

    public int getScore() {
        return score;
    }

    public int compareTo(Player o) {
        return o.getScore()-getScore();
    }
}
