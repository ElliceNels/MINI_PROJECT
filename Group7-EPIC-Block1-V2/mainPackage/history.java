package mainPackage;

import java.util.Arrays;

public class history {
    String user_ID;
    int score_of_round;
    int wins;
    int losses;
    String date;

    public history(String user_ID, int score_of_round, int wins, int losses, String date) {
        this.user_ID = user_ID;
        this.score_of_round = score_of_round;
        this.wins = wins;
        this.losses = losses;
        this.date = date;
    }

    @Override
    public String toString() {
        return "mainPackage.history{" +
                "user_ID='" + user_ID + '\'' +
                ", score_of_round=" + score_of_round +
                ", wins=" + wins +
                ", losses=" + losses +
                ", date='" + date + '\'' +
                '}';
    }

    public String getUser_ID() {
        return user_ID;
    }

    public int getScore_of_round() {
        return score_of_round;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public String getDate() {
        return date;
    }
}
