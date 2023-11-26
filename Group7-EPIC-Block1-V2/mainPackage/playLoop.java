package mainPackage;

import java.util.Scanner;
public class playLoop {
    public static void main(String[] args) {

    }

    private static int[] gameplayLoop(String user_ID, Scanner scan, card[] cards_array, int score, int wins, int losses) {
        for (int i = 0; i < cards_array.length; i++) {
            // show mainPackage.DB_Handler.card
            // enter 1, 2 ,3 ,4
            // check if correct index (i.e index +1)
            // if correct, add 1 to score, and add win to scores_db and change score_db by 1
            // if incorrect, add loss to scores_db and change score_db by -1

            // show mainPackage.DB_Handler.card
            System.out.println(cards_array[i].Question_content);
            for (int answer = 0; answer < cards_array[i].Question_answers_arr.length; answer++) {
                System.out.println(answer + 1 + ": \t" + cards_array[i].Question_answers_arr[answer]);
            }

            // enter 1, 2 ,3 ,4
            System.out.print("Enter your answer: ");
            int user_answer = scan.nextInt();

            // check if correct index (i.e index +1)
            if (user_answer == cards_array[i].Question_correct_answer + 1){
                // if correct, add 1 to score, and add win to scores_db and change score_db by 1
                DB_ScoreInteract.addWin(user_ID, cards_array[i].card_id);
                score++; // local score += 1
                wins++; // local wins += 1


                System.out.println("\nCorrect!");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            else {
                // if incorrect, add loss to scores_db and change score_db by -1
                DB_ScoreInteract.addLoss(user_ID, cards_array[i].card_id);
                score--; // local score -= 1
                losses++; // local losses += 1

                System.out.println("\nIncorrect!");
                // wait 3 seconds
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
        return new int[]{score, wins, losses};
    }

    private static void processFinishResults(int[] results, String user_ID){
        int score = results[0];
        int wins = results[1];
        int losses = results[2];

        // add mainPackage.history record
        DB_PlayHistory.addHistory(user_ID, score, wins, losses);

        // print round results
        System.out.println("Round score: " + score);
        System.out.println("Round wins: " + wins);
        System.out.println("Round losses: " + losses);

        int total_rounds = wins + losses;
        float win_percentage = (float) wins / total_rounds;
        System.out.println("Round win percentage: " + win_percentage * 100 + "%");
    }

    public static void shuffle_array(card[] cards_array){ //https://www.digitalocean.com/community/tutorials/shuffle-array-java#2-shuffle-array-using-random-class
        // shuffle array
        for (int i = 0; i < cards_array.length; i++) {
            int randomIndexToSwap = (int) (Math.random() * cards_array.length);
            card temp = cards_array[randomIndexToSwap];
            cards_array[randomIndexToSwap] = cards_array[i];
            cards_array[i] = temp;
        }
    }
    public static void random_play(String user_ID){
        Scanner scan = new Scanner(System.in);
        // take all cards from database, create array of objects, shuffle array, and then play through the array, remoiving cards as they are played

        card[] cards_array = DB_CardInteract.returnAllCards();
        shuffle_array(cards_array);


        int round_score = 0;
        int round_wins = 0;
        int round_losses = 0;

        int[] results = gameplayLoop(user_ID, scan, cards_array, round_score, round_wins, round_losses);


        processFinishResults(results, user_ID);
    }

    public static void increasingDifficulty_play(String user_ID){
        Scanner scan = new Scanner(System.in);
        // take all cards from database, create array of objects, shuffle array, and then play through the array, remoiving cards as they are played

        card[] cards_array = DB_CardInteract.allCardsIncreasingDifficulty();
        int round_score = 0;
        int round_wins = 0;
        int round_losses = 0;

        // gameplay loop
        int[] results = gameplayLoop(user_ID, scan, cards_array, round_score, round_wins, round_losses);


        processFinishResults(results, user_ID);
    }
    public static void increasingScore_play(String user_ID){
        Scanner scan = new Scanner(System.in);
        // take all cards from database, create array of objects, shuffle array, and then play through the array, remoiving cards as they are played

        card[] cards_array = DB_CardInteract.allCardsIncreasingScore(user_ID);
        int round_score = 0;
        int round_wins = 0;
        int round_losses = 0;

        int[] results = gameplayLoop(user_ID, scan, cards_array, round_score, round_wins, round_losses);


        processFinishResults(results, user_ID);
    }

}
